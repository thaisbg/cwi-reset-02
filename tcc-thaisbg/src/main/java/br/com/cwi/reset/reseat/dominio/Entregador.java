package br.com.cwi.reset.reseat.dominio;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Getter @Setter @NoArgsConstructor
public class Entregador {

    public static final String REGEX_PLACA = "([A-Z]{3}\\s[0-9][0-9A-Z][0-9]{2})";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @CPF
    private String cpf;

    @NotBlank
    private String nome;

    @NotBlank
    private String telefone;

    @NotBlank
    @Pattern(regexp = REGEX_PLACA, message = "A placa deve ser corresponder ao padrão AAA 9999 ou AAA 9A99.")
    private String placaVeiculo;

    private Boolean disponivel = true;

}
