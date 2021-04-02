package br.com.cwi.reset.reseat.dominio;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class Estabelecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String cnpj;

    @NotBlank
    private String nomeFantasia;

    @NotBlank
    private String razaoSocial;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_estabelecimento")
    private List<HorarioFuncionamento> horariosFuncionamento;

    @NotNull
    @ElementCollection(targetClass = FormaPagamento.class)
    @JoinTable(name = "FORMAS_PAGAMENTO", joinColumns = @JoinColumn(name = "cnpj"))
    @Enumerated(EnumType.STRING)
    private List<FormaPagamento> formasPagamento;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_estabelecimento")
    private List<Endereco> enderecos;

}
