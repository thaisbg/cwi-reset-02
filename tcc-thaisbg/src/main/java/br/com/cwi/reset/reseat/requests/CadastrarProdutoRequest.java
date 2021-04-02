package br.com.cwi.reset.reseat.requests;

import br.com.cwi.reset.reseat.dominio.Categoria;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Component
@Getter @Setter @NoArgsConstructor
public class CadastrarProdutoRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;

    @NotNull
    private BigDecimal valor;

    private String urlFoto;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    private Integer tempoPreparo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_estabelecimento")
    private Long idEstabelecimento;

}
