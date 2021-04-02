package br.com.cwi.reset.reseat.responses;

import br.com.cwi.reset.reseat.dominio.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Getter @Setter @NoArgsConstructor
public class PedidoResponse {

    private String solicitante;
    private Endereco enderecoEntrega;
    private String estabelecimento; //NOME FANTASIA, NÃO O ESTABELECIMENTO INTEIRO!
    private List<ItemPedidoResponse> itensPedido;
    private BigDecimal valorTotal;
    private String status;
    private EntregadorResponse entregador;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime horarioPrevisaoEntrega;

}
