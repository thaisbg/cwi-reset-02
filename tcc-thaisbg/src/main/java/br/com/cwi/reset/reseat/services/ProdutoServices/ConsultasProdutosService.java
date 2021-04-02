package br.com.cwi.reset.reseat.services.ProdutoServices;

import br.com.cwi.reset.reseat.dominio.Produto;
import br.com.cwi.reset.reseat.mappers.ProdutoResponseMapper;
import br.com.cwi.reset.reseat.repositories.ProdutoRepository;
import br.com.cwi.reset.reseat.responses.ProdutoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultasProdutosService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoResponseMapper produtoResponseMapper;

    @Autowired
    private ProdutoResponse produtoResponse;

    public Page<ProdutoResponse> listarProdutos(Integer pagina, Integer tamanho, String ordenacao) {

        Pageable paginacao = PageRequest.of(pagina, tamanho, Sort.by(ordenacao).ascending());
        Page<Produto> produtosPaginados = produtoRepository.findAll(paginacao);

        List<ProdutoResponse> listaProdutosResponse = new ArrayList<>();

        for (Produto produto : produtosPaginados) {
            ProdutoResponse produtoMapear = produtoResponseMapper.mapear(produto);
            if (produto.isProdutoAtivo()) {
                listaProdutosResponse.add(produtoMapear);
            }
        }

        Page<ProdutoResponse> produtosPaginadosResponse = new PageImpl<ProdutoResponse>(listaProdutosResponse);

        return produtosPaginadosResponse;
    }
}
