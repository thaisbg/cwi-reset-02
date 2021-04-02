package br.com.cwi.reset.reseat.services.PedidoServices;

import br.com.cwi.reset.reseat.dominio.Pedido;
import br.com.cwi.reset.reseat.dominio.StatusPedido;
import br.com.cwi.reset.reseat.exceptions.PedidoNaoEncontradoException;
import br.com.cwi.reset.reseat.exceptions.PedidoNaoPodeSerFinalizadoException;
import br.com.cwi.reset.reseat.repositories.EntregadorRepository;
import br.com.cwi.reset.reseat.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FinalizarPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EntregadorRepository entregadorRepository;

    public void finalizarPedido(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNaoEncontradoException("Pedido não encontrado."));

        if(!pedido.getStatus().equals(StatusPedido.SAIU_PARA_ENTREGA)) {
            throw new PedidoNaoPodeSerFinalizadoException("Este pedido não pode ser finalizado. Possíveis causas: o pedido já pode estar finalizado, pode não ter saído para entrega ou pode ter sido cancelado.");
        }

        pedido.setStatus(StatusPedido.ENTREGUE);
        pedido.setHorarioEntrega(LocalDateTime.now());
        pedido.getEntregador().setDisponivel(true);

        pedidoRepository.save(pedido);
        entregadorRepository.save(pedido.getEntregador());
    }

}
