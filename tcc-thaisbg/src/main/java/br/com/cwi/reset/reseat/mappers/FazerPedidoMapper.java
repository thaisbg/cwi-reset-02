package br.com.cwi.reset.reseat.mappers;

import br.com.cwi.reset.reseat.dominio.*;
import br.com.cwi.reset.reseat.exceptions.EstabelecimentoNaoEncontradoException;
import br.com.cwi.reset.reseat.exceptions.ProdutoNaoEncontradoException;
import br.com.cwi.reset.reseat.exceptions.UsuarioNaoEncontradoException;
import br.com.cwi.reset.reseat.repositories.EstabelecimentoRepository;
import br.com.cwi.reset.reseat.repositories.ProdutoRepository;
import br.com.cwi.reset.reseat.repositories.UsuarioRepository;
import br.com.cwi.reset.reseat.requests.FazerPedidoRequest;
import br.com.cwi.reset.reseat.requests.ItemPedidoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class FazerPedidoMapper {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public Pedido mapear(FazerPedidoRequest request) {
        Estabelecimento estabelecimento = estabelecimentoRepository.findById(request.getIdEstabelecimento()).orElseThrow(() -> new EstabelecimentoNaoEncontradoException("Estabelecimento não encontrado"));
        Usuario usuario = usuarioRepository.findById(request.getIdUsuarioSolicitante()).orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado."));

        Pedido pedidoSalvar = new Pedido();
        pedidoSalvar.setEstabelecimento(estabelecimento);
        pedidoSalvar.setSolicitante(usuario);
        pedidoSalvar.setEnderecoEntrega(mapearEnderecoEntrega(request, usuario));
        pedidoSalvar.setItensPedido(mapearPedidos(request));
        pedidoSalvar.setFormaPagamento(request.getFormaPagamento());

        return pedidoSalvar;
    }

    public Endereco mapearEnderecoEntrega(FazerPedidoRequest request, Usuario usuario) {
        List<Endereco> enderecosUsuario = usuario.getEnderecos();
        Endereco enderecoEntrega = null;

        for (Endereco endereco : enderecosUsuario) {
            if (endereco.getId().equals(request.getIdEnderecoEntrega())) {
                enderecoEntrega = endereco;
            }
        }
        return enderecoEntrega;
    }

    public List<ItemPedido> mapearPedidos(FazerPedidoRequest request) {
        List<ItemPedidoRequest> listaPedidosRequest = request.getItens();
        List<ItemPedido> listaPedidos = new ArrayList<>();

        for (ItemPedidoRequest item : listaPedidosRequest) {
            Produto produto = produtoRepository.findById(item.getIdProduto()).orElseThrow(() -> new ProdutoNaoEncontradoException("Não foi encontrado nenhum produto com o ID informado."));
            ItemPedido pedido = new ItemPedido();
            pedido.setProduto(produto);
            pedido.setQuantidade(item.getQuantidade());
            listaPedidos.add(pedido);
        }
        return listaPedidos;
    }
}
