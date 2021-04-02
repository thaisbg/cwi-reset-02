package br.com.cwi.reset.reseat.mappers;

import br.com.cwi.reset.reseat.dominio.Estabelecimento;
import br.com.cwi.reset.reseat.dominio.Produto;
import br.com.cwi.reset.reseat.repositories.EstabelecimentoRepository;
import br.com.cwi.reset.reseat.requests.CadastrarProdutoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CadastrarProdutoMapper {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    public Produto mapear(CadastrarProdutoRequest request) {
        Estabelecimento estabelecimento = estabelecimentoRepository.findById(request.getIdEstabelecimento()).orElse(null);

        Produto produtoSalvar = new Produto();
        produtoSalvar.setCategoria(request.getCategoria());
        produtoSalvar.setEstabelecimento(estabelecimento);
        produtoSalvar.setDescricao(request.getDescricao());
        produtoSalvar.setTitulo(request.getTitulo());
        produtoSalvar.setValor(request.getValor());
        produtoSalvar.setTempoPreparo(request.getTempoPreparo());
        produtoSalvar.setUrlFoto(request.getUrlFoto());

        return produtoSalvar;
    }
}
