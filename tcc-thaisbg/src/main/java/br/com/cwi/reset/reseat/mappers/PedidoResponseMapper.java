package br.com.cwi.reset.reseat.mappers;

import br.com.cwi.reset.reseat.dominio.ItemPedido;
import br.com.cwi.reset.reseat.dominio.Pedido;
import br.com.cwi.reset.reseat.dominio.StatusPedido;
import br.com.cwi.reset.reseat.responses.ItemPedidoResponse;
import br.com.cwi.reset.reseat.responses.PedidoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PedidoResponseMapper {

    @Autowired
    private EntregadorResponseMapper entregadorResponseMapper;

    public PedidoResponse mapear(Pedido pedido) {
        PedidoResponse response = new PedidoResponse();

        response.setSolicitante(pedido.getSolicitante().getNome());
        response.setEnderecoEntrega(pedido.getEnderecoEntrega());
        response.setEstabelecimento(pedido.getEstabelecimento().getNomeFantasia());
        response.setItensPedido(mapearPedidos(pedido));
        response.setValorTotal(pedido.getValorTotal());
        response.setStatus(pedido.getStatus().getDescricao());

        if ((pedido.getStatus().equals(StatusPedido.SAIU_PARA_ENTREGA)) || (pedido.getStatus().equals(StatusPedido.ENTREGUE))) {
            response.setEntregador(entregadorResponseMapper.mapear(pedido.getEntregador()));
        } else {
            response.setEntregador(null);
        }

        return response;
    }

    private List<ItemPedidoResponse> mapearPedidos(Pedido pedido) {
        List<ItemPedido> listaPedidos = pedido.getItensPedido();
        List<ItemPedidoResponse> listaPedidosResponse = new ArrayList<>();

        for (ItemPedido item : listaPedidos) {
            ItemPedidoResponse itemResponse = new ItemPedidoResponse();
            itemResponse.setProduto(item.getProduto().getTitulo());
            itemResponse.setQuantidade(item.getQuantidade());
            listaPedidosResponse.add(itemResponse);
        }
        return listaPedidosResponse;
    }
}
