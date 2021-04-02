package br.com.cwi.reset.reseat.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter @NoArgsConstructor
public class EntregadorResponse {

    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String placaVeiculo;

}
