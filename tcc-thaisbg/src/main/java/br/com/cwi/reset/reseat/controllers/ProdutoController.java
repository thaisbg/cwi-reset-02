package br.com.cwi.reset.reseat.controllers;

import br.com.cwi.reset.reseat.requests.CadastrarProdutoRequest;
import br.com.cwi.reset.reseat.responses.ProdutoResponse;
import br.com.cwi.reset.reseat.services.ProdutoServices.CadastrarProdutoService;
import br.com.cwi.reset.reseat.services.ProdutoServices.ConsultasProdutosService;
import br.com.cwi.reset.reseat.services.ProdutoServices.RemoverProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private CadastrarProdutoService cadastrarProdutoService;

    @Autowired
    private ConsultasProdutosService consultasProdutosService;

    @Autowired
    private RemoverProdutoService removerProdutoService;

    @Autowired
    private ProdutoResponse produtoResponse;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoResponse cadastrarProduto(@RequestBody @Valid CadastrarProdutoRequest request) {
        return cadastrarProdutoService.cadastrarProduto(request);
    }

    @GetMapping
    public Page<ProdutoResponse> listarProdutos(
            @RequestParam(defaultValue = "0") Integer pagina,
            @RequestParam(defaultValue = "50") Integer tamanho,
            @RequestParam(defaultValue = "descricao") String ordenacao) {
        return consultasProdutosService.listarProdutos(pagina, tamanho, ordenacao);
    }

    @DeleteMapping("/{id}")
    public void removerProduto(@PathVariable("id") Long id) {
        removerProdutoService.removerProduto(id);
    }
}
