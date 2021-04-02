package br.com.cwi.reset.reseat.mappers;

import br.com.cwi.reset.reseat.dominio.Produto;
import br.com.cwi.reset.reseat.responses.ProdutoResponse;
import org.springframework.stereotype.Component;

@Component
public class ProdutoResponseMapper {

    public ProdutoResponse mapear(Produto produto) {
        ProdutoResponse response = new ProdutoResponse();

        response.setId(produto.getId());
        response.setTitulo(produto.getTitulo());
        response.setDescricao(produto.getDescricao());
        response.setValor(produto.getValor());
        response.setEstabelecimento(produto.getEstabelecimento().getCnpj());
        response.setCategoria(produto.getCategoria());
        response.setTempoPreparo(produto.getTempoPreparo());
        response.setUrlFoto(produto.getUrlFoto());

        return response;
    }



}
