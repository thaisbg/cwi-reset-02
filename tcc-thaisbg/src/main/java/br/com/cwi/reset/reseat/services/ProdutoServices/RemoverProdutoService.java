package br.com.cwi.reset.reseat.services.ProdutoServices;

import br.com.cwi.reset.reseat.dominio.Produto;
import br.com.cwi.reset.reseat.exceptions.ProdutoNaoEncontradoException;
import br.com.cwi.reset.reseat.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoverProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public void removerProduto(Long id) {
        Produto produtoRemover = produtoRepository.findById(id).orElseThrow(() -> new ProdutoNaoEncontradoException("Não foi encontrado nenhum produto com o ID informado."));
        produtoRemover.setProdutoAtivo(false);
        produtoRepository.save(produtoRemover);
    }
}
