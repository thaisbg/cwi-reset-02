package br.com.cwi.reset.reseat.services.PedidoServices;

import br.com.cwi.reset.reseat.dominio.*;
import br.com.cwi.reset.reseat.exceptions.*;
import br.com.cwi.reset.reseat.mappers.FazerPedidoMapper;
import br.com.cwi.reset.reseat.mappers.FazerPedidoResponseMapper;
import br.com.cwi.reset.reseat.repositories.EnderecoRepository;
import br.com.cwi.reset.reseat.repositories.EstabelecimentoRepository;
import br.com.cwi.reset.reseat.repositories.PedidoRepository;
import br.com.cwi.reset.reseat.repositories.UsuarioRepository;
import br.com.cwi.reset.reseat.requests.FazerPedidoRequest;
import br.com.cwi.reset.reseat.responses.FazerPedidoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class FazerPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private FazerPedidoRequest fazerPedidoRequest;

    @Autowired
    private FazerPedidoMapper fazerPedidoMapper;

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private FazerPedidoResponse fazerPedidoResponse;

    @Autowired
    private FazerPedidoResponseMapper fazerPedidoResponseMapper;

    public FazerPedidoResponse fazerPedido(FazerPedidoRequest request) {
        Pedido pedidoSalvar = fazerPedidoMapper.mapear(request);

        validarUsuarioEendereco(request);
        validarEstabelecimento(request);
        validarHorarioPedido(request);
        validarEstabelecimentoTemProduto(request);
        validarQuantidadeItens(request);

        pedidoSalvar.setHorarioSolicitacao(LocalDateTime.now());
        pedidoSalvar.setStatus(StatusPedido.EM_PREPARO);
        pedidoSalvar.setValorTotal(calcularValorTotal(request));
        pedidoRepository.save(pedidoSalvar);

        FazerPedidoResponse pedidoResponse = fazerPedidoResponseMapper.mapear(pedidoSalvar);
        pedidoResponse.setTempoEstimado(calcularTempoDeEntrega(request));

        return pedidoResponse;
    }

    // ----------------------------- VALIDAÇÕES -----------------------------

    private void validarUsuarioEendereco(FazerPedidoRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getIdUsuarioSolicitante())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Não foi encontrado nenhum usuário com o ID informado"));

        if (usuario.getEnderecos().isEmpty()) {
            throw new UsuarioSemEnderecosCadastradosException("O usuário ainda não tem nenhum endereço cadastrado.");
        }

        Endereco endereco = fazerPedidoMapper.mapearEnderecoEntrega(request, usuario);
        if (endereco == null) {
            throw new EnderecoNaoEncontradoException("O endereço não pertence ao usuário informado.");
        }
    }

    private Estabelecimento validarEstabelecimento(FazerPedidoRequest request) {
        return estabelecimentoRepository.findById(request.getIdEstabelecimento())
                .orElseThrow(() -> new EstabelecimentoNaoEncontradoException("Não foi encontrado nenhum estabelecimento com o ID informado."));
    }

    private void validarEstabelecimentoTemProduto(FazerPedidoRequest request) {
        List<ItemPedido> itensPedido = fazerPedidoMapper.mapearPedidos(request);
        Estabelecimento estabelecimento = validarEstabelecimento(request);

        for (ItemPedido item : itensPedido) {
            if (!item.getProduto().getEstabelecimento().equals(estabelecimento)) {
                throw new EstabelecimentoInvalidoException("Seu pedido contém produtos que não correspondem ao estabelecimento informado.");
            }
        }
    }

    private void validarQuantidadeItens(FazerPedidoRequest request) {
        List<ItemPedido> itensPedido = fazerPedidoMapper.mapearPedidos(request );

        for (ItemPedido item : itensPedido) {
            if (item.getQuantidade() > 5) {
                throw new QuantidadeInvalidaException("Você só pode pedir até 5 unidades de cada produto.");
            }
        }
    }

    private void validarHorarioPedido(FazerPedidoRequest request) {
        LocalTime horarioAtual = LocalTime.now();
        DayOfWeek diaSemanaHoje = LocalDateTime.now().getDayOfWeek();
        List<HorarioFuncionamento> horariosFuncionamento = validarEstabelecimento(request).getHorariosFuncionamento();
        boolean estabelecimentoAbre = false;

        for (HorarioFuncionamento horario : horariosFuncionamento) {

            boolean diaValido = (horario.getDiaSemana().equals(diaSemanaHoje));
            boolean horarioValido = horarioAtual.isAfter(horario.getHorarioAbertura())
                    && horarioAtual.isBefore(horario.getHorarioFechamento());

            if ((diaValido) && (horarioValido)) {
                estabelecimentoAbre = true;
            }
        }

        if (!estabelecimentoAbre) {
            throw new EstabelecimentoFechadoException("O estabelecimento encontra-se fechado no momento.");
        }

    }

    // ----------------------------- CÁLCULOS -----------------------------


    private Integer calcularTempoDeEntrega(FazerPedidoRequest request) {
        List<ItemPedido> listaPedidos = fazerPedidoMapper.mapearPedidos(request);
        Integer tempoPrevisto = 0;

        for (ItemPedido item : listaPedidos) {
            tempoPrevisto += item.getProduto().getTempoPreparo() * item.getQuantidade();
        }
        return tempoPrevisto;
    }

    private BigDecimal calcularValorTotal(FazerPedidoRequest request) {
        List<ItemPedido> listaPedidos = fazerPedidoMapper.mapearPedidos(request);
        BigDecimal valorTotal = BigDecimal.ZERO;

        for (ItemPedido item : listaPedidos) {
            valorTotal = valorTotal.add((item.getProduto().getValor().multiply(BigDecimal.valueOf(item.getQuantidade()))));
        }
        return valorTotal;
    }

}
