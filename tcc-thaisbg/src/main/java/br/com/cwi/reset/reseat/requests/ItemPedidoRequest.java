package br.com.cwi.reset.reseat.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter @NoArgsConstructor
public class ItemPedidoRequest {

    private Long idProduto;
    private Integer quantidade;
}
