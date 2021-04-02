package br.com.cwi.reset.reseat.services.PedidoServices;

import br.com.cwi.reset.reseat.dominio.ItemPedido;
import br.com.cwi.reset.reseat.dominio.Pedido;
import br.com.cwi.reset.reseat.dominio.StatusPedido;
import br.com.cwi.reset.reseat.exceptions.PedidoNaoEncontradoException;
import br.com.cwi.reset.reseat.mappers.PedidoResponseMapper;
import br.com.cwi.reset.reseat.repositories.PedidoRepository;
import br.com.cwi.reset.reseat.responses.PedidoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsultasPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoResponseMapper pedidoResponseMapper;

    public PedidoResponse buscarPedidoPorId(Long id){
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNaoEncontradoException("Pedido não encontrado."));
        PedidoResponse response = pedidoResponseMapper.mapear(pedido);
        response.setHorarioPrevisaoEntrega(calcularTempoDeEntrega(id));

        return response;
    }

    private LocalDateTime calcularTempoDeEntrega(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElse(null);
        List<ItemPedido> listaPedidos = pedido.getItensPedido();
        Integer tempoPrevisto = 0;

        if (!pedido.getStatus().equals(StatusPedido.ENTREGUE)) {
            for (ItemPedido item : listaPedidos) {
                tempoPrevisto += item.getProduto().getTempoPreparo() * item.getQuantidade();
            }
        }
        LocalDateTime horarioPrevisto = pedido.getHorarioSolicitacao().plusMinutes(tempoPrevisto);
        return horarioPrevisto;
    }


}
