package br.com.cwi.reset.reseat.services.PedidoServices;

import br.com.cwi.reset.reseat.dominio.Pedido;
import br.com.cwi.reset.reseat.dominio.StatusPedido;
import br.com.cwi.reset.reseat.exceptions.PedidoNaoEncontradoException;
import br.com.cwi.reset.reseat.exceptions.PedidoNaoPodeSerCanceladoException;
import br.com.cwi.reset.reseat.exceptions.PrazoDeCancelamentoExpiradoException;
import br.com.cwi.reset.reseat.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CancelarPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public void cancelarPedido(Long id) {
        Pedido pedidoCancelar = pedidoRepository.findById(id).orElseThrow(() -> new PedidoNaoEncontradoException("Não foi encontrado nenhum pedido com o ID informado."));

        if (!pedidoCancelar.getStatus().equals(StatusPedido.EM_PREPARO)) {
            throw new PedidoNaoPodeSerCanceladoException("O pedido não pode ser cancelado. Só é possível cancelar um pedido se ele ainda estiver em preparo.");
        }

        if((pedidoCancelar.getHorarioSolicitacao().plusMinutes(10)).isBefore(LocalDateTime.now())) {
            throw new PrazoDeCancelamentoExpiradoException("Só é possível cancelar pedidos em até 10 minutos a partir do horário do pedido.");
        }

        pedidoCancelar.setStatus(StatusPedido.CANCELADO);
        pedidoCancelar.setHorarioCancelamento(LocalDateTime.now());
        pedidoRepository.save(pedidoCancelar);
    }
}
