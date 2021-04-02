package br.com.cwi.reset.reseat.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter @NoArgsConstructor
public class ItemPedidoResponse {

    private String produto;
    private Integer quantidade;

}
