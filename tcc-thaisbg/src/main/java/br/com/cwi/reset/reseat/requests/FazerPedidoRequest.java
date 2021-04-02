package br.com.cwi.reset.reseat.requests;

import br.com.cwi.reset.reseat.dominio.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter @Setter @NoArgsConstructor
public class FazerPedidoRequest {

    private Long idUsuarioSolicitante;
    private Long idEnderecoEntrega;
    private Long idEstabelecimento;
    private List<ItemPedidoRequest> itens;
    private FormaPagamento formaPagamento;

}
