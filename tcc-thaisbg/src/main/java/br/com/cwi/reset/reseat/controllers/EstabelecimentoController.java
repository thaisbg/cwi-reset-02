package br.com.cwi.reset.reseat.controllers;

import br.com.cwi.reset.reseat.dominio.Endereco;
import br.com.cwi.reset.reseat.dominio.Estabelecimento;
import br.com.cwi.reset.reseat.services.EstabelecimentoServices.CadastrarEnderecoEstabelecimentoService;
import br.com.cwi.reset.reseat.services.EstabelecimentoServices.CadastrarEstabelecimentoService;
import br.com.cwi.reset.reseat.services.EstabelecimentoServices.ConsultasEstabelecimentosService;
import br.com.cwi.reset.reseat.services.EstabelecimentoServices.RemoverEnderecoEstabelecimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/estabelecimentos")

public class EstabelecimentoController {

    @Autowired
    private CadastrarEstabelecimentoService cadastrarEstabelecimentoService;

    @Autowired
    private ConsultasEstabelecimentosService consultasEstabelecimentosService;

    @Autowired
    private CadastrarEnderecoEstabelecimentoService cadastrarEnderecoEstabelecimentoService;

    @Autowired
    private RemoverEnderecoEstabelecimentoService removerEnderecoEstabelecimentoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estabelecimento cadastrarEstabelecimento (@RequestBody @Valid Estabelecimento estabelecimento) {
        return cadastrarEstabelecimentoService.cadastrarEstabelecimento(estabelecimento);
    }

    @GetMapping
    public Page<Estabelecimento> listarEstabelecimentos (
            @RequestParam(defaultValue = "0") Integer pagina,
            @RequestParam(defaultValue = "50") Integer tamanho,
            @RequestParam(defaultValue = "nomeFantasia") String ordenacao) {
        return consultasEstabelecimentosService.listarEstabelecimentos(pagina, tamanho, ordenacao);
    }

    @GetMapping("/{id}")
    public Estabelecimento buscarEstabelecimentoPorId(@PathVariable("id") Long id) {
        return consultasEstabelecimentosService.buscarEstabelecimentoPorId(id);
    }

    @PostMapping("/{id}/enderecos")
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarEndereco(@PathVariable("id") Long id, @RequestBody @Valid Endereco endereco) {
        cadastrarEnderecoEstabelecimentoService.cadastrarEndereco(id, endereco);
    }

    @DeleteMapping("/{id}/enderecos/{idEndereco}")
    public void removerEndereco(@PathVariable("id") Long id, @PathVariable("idEndereco") Long idEndereco) {
        removerEnderecoEstabelecimentoService.removerEndereco(id, idEndereco);
    }

}
