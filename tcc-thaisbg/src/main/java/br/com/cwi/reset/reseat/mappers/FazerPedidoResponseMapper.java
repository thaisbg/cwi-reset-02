package br.com.cwi.reset.reseat.mappers;

import br.com.cwi.reset.reseat.dominio.Pedido;
import br.com.cwi.reset.reseat.responses.FazerPedidoResponse;
import org.springframework.stereotype.Component;

@Component
public class FazerPedidoResponseMapper {

    public FazerPedidoResponse mapear(Pedido pedido) {
        FazerPedidoResponse response = new FazerPedidoResponse();
        response.setId(pedido.getId());
        response.setEnderecoEntrega(pedido.getEnderecoEntrega());
        response.setValorTotal(pedido.getValorTotal());
        response.setStatus(pedido.getStatus().getDescricao());

        return response;
    }

}
