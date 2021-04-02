package br.com.cwi.reset.reseat.responses;

import br.com.cwi.reset.reseat.dominio.Endereco;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Getter @Setter @NoArgsConstructor
public class FazerPedidoResponse {

    private Long id;
    private Integer tempoEstimado;
    private Endereco enderecoEntrega;
    private BigDecimal valorTotal;
    private String status;

}
