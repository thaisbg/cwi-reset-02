package br.com.cwi.reset.reseat.services.ProdutoServices;

import br.com.cwi.reset.reseat.dominio.Produto;
import br.com.cwi.reset.reseat.exceptions.EstabelecimentoNaoEncontradoException;
import br.com.cwi.reset.reseat.exceptions.ValorInvalidoException;
import br.com.cwi.reset.reseat.mappers.CadastrarProdutoMapper;
import br.com.cwi.reset.reseat.mappers.ProdutoResponseMapper;
import br.com.cwi.reset.reseat.repositories.EstabelecimentoRepository;
import br.com.cwi.reset.reseat.repositories.ProdutoRepository;
import br.com.cwi.reset.reseat.requests.CadastrarProdutoRequest;
import br.com.cwi.reset.reseat.responses.ProdutoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CadastrarProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    private CadastrarProdutoMapper cadastrarProdutoMapper;

    @Autowired
    private ProdutoResponseMapper produtoResponseMapper;

    public ProdutoResponse cadastrarProduto(CadastrarProdutoRequest request) {
        validarProduto(request);
        Produto produtoSalvar = cadastrarProdutoMapper.mapear(request);
        produtoRepository.save(produtoSalvar);
        return produtoResponseMapper.mapear(produtoSalvar);
    }

    private void validarProduto(CadastrarProdutoRequest request) {
        if (request.getValor().compareTo(BigDecimal.ZERO) < 0) {
            throw new ValorInvalidoException("O valor do produto não pode ser inferior a zero.");
        }

        if (!estabelecimentoRepository.existsById(request.getIdEstabelecimento())) {
            throw new EstabelecimentoNaoEncontradoException("Não foi encontrado nenhum estabelecimento com o ID informado.");
        }

        if (request.getTempoPreparo() == null) {
            request.setTempoPreparo(30);
        }
    }


}
