package br.com.cwi.reset.reseat.controllers;

import br.com.cwi.reset.reseat.dominio.Entregador;
import br.com.cwi.reset.reseat.services.EntregadorServices.CadastrarEntregadorService;
import br.com.cwi.reset.reseat.services.EntregadorServices.ConsultasEntregadoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/entregadores")
public class EntregadorController {

    @Autowired
    private CadastrarEntregadorService cadastrarEntregadorService;

    @Autowired
    private ConsultasEntregadoresService consultasEntregadoresService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entregador cadastrarEntregador(@RequestBody @Valid Entregador entregador){
        return cadastrarEntregadorService.cadastrarEntregador(entregador);
    }

    @GetMapping
    public Page<Entregador> listarEntregadores(
            @RequestParam(defaultValue = "0") Integer pagina,
            @RequestParam(defaultValue = "50") Integer tamanho,
            @RequestParam(defaultValue = "nome") String ordenacao) {
        return consultasEntregadoresService.listarEntregadores(pagina, tamanho, ordenacao);
    }
}
