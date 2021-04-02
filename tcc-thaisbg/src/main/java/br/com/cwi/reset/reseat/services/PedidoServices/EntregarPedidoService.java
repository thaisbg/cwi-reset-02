package br.com.cwi.reset.reseat.services.PedidoServices;

import br.com.cwi.reset.reseat.dominio.Entregador;
import br.com.cwi.reset.reseat.dominio.Pedido;
import br.com.cwi.reset.reseat.dominio.StatusPedido;
import br.com.cwi.reset.reseat.exceptions.EntregadoresIndisponiveisException;
import br.com.cwi.reset.reseat.exceptions.PedidoNaoDisponivelParaEntregaException;
import br.com.cwi.reset.reseat.exceptions.PedidoNaoEncontradoException;
import br.com.cwi.reset.reseat.mappers.EntregadorResponseMapper;
import br.com.cwi.reset.reseat.repositories.EntregadorRepository;
import br.com.cwi.reset.reseat.repositories.PedidoRepository;
import br.com.cwi.reset.reseat.responses.EntregadorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class EntregarPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EntregadorRepository entregadorRepository;

    @Autowired
    private EntregadorResponseMapper entregadorResponseMapper;

    public EntregadorResponse entregarPedido(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNaoEncontradoException("Pedido não encontrado."));

        if (!pedido.getStatus().equals(StatusPedido.EM_PREPARO)) {
            throw new PedidoNaoDisponivelParaEntregaException("Este produto não está disponível para entrega. É possível que o produto já tenha saído para entrega, ou que tenha sido cancelado.");
        }

        pedido.setHorarioSaiuParaEntrega(LocalDateTime.now());
        pedido.setStatus(StatusPedido.SAIU_PARA_ENTREGA);
        pedido.setEntregador(escolherEntregador());
        pedidoRepository.save(pedido);

        return entregadorResponseMapper.mapear(pedido.getEntregador());

    }

    public Entregador escolherEntregador() {
        List<Entregador> entregadores = entregadorRepository.findAllByDisponivelTrue();

        if (entregadores.isEmpty()) {
            throw new EntregadoresIndisponiveisException("Não há nenhum entregador disponível no momento. Por favor, tente novamente mais tarde.");
        }

        Random entregadorAleatorio = new Random();

        int index = entregadorAleatorio.nextInt(entregadores.size());
        Entregador entregador = entregadores.get(index);

        entregador.setDisponivel(false);
        entregadorRepository.save(entregador);

        return entregador;
    }
}
