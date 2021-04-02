package br.com.cwi.reset.reseat.dominio;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Getter @Setter @NoArgsConstructor
public class Endereco {

    public static final String REGEX_CEP = "[0-9]{5}-[0-9]{3}";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Pattern(regexp = REGEX_CEP, message = "O CEP deve ser inserido no padrão 99999-999")
    private String cep;

    @NotBlank
    private String logradouro;

    @NotBlank
    private String numero;

    private String complemento;

    @NotBlank
    private String bairro;

    @NotBlank
    private String cidade;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Estado estado;

}
