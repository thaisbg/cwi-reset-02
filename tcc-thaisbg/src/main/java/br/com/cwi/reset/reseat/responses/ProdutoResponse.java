package br.com.cwi.reset.reseat.responses;

import br.com.cwi.reset.reseat.dominio.Categoria;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Getter @Setter @NoArgsConstructor
public class ProdutoResponse {

    private Long id;
    private String titulo;
    private String descricao;
    private BigDecimal valor;
    private String estabelecimento;
    private Categoria categoria;
    private Integer tempoPreparo;
    private String urlFoto;

}
