package br.com.cwi.reset.reseat.controllers;

import br.com.cwi.reset.reseat.dominio.Endereco;
import br.com.cwi.reset.reseat.dominio.Usuario;
import br.com.cwi.reset.reseat.requests.AlterarUsuarioRequest;
import br.com.cwi.reset.reseat.services.UsuarioServices.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private CadastrarUsuarioService cadastrarUsuarioService;

    @Autowired
    private ConsultasUsuariosService consultasUsuariosService;

    @Autowired
    private CadastrarEnderecoUsuarioService cadastrarEnderecoUsuarioService;

    @Autowired
    private RemoverEnderecoUsuarioService removerEnderecoUsuarioService;

    @Autowired
    private AlterarUsuarioService alterarUsuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario cadastrarUsuario(@RequestBody @Valid Usuario usuario) {
        return cadastrarUsuarioService.cadastrarUsuario(usuario);
    }

    @GetMapping
    public Page<Usuario> listarUsuarios(
            @RequestParam(defaultValue = "0") Integer pagina,
            @RequestParam(defaultValue = "50") Integer tamanho,
            @RequestParam(defaultValue = "nome") String ordenacao) {
        return consultasUsuariosService.listarUsuarios(pagina, tamanho, ordenacao);
    }

    @GetMapping("/{id}")
    public Usuario buscarUsuarioPorId(@PathVariable("id") Long id) {
        return consultasUsuariosService.buscarUsuarioPorId(id);
    }

    @PostMapping("/{id}/enderecos")
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarEndereco(@PathVariable("id") Long id, @RequestBody @Valid Endereco endereco) {
        cadastrarEnderecoUsuarioService.cadastrarEndereco(id, endereco);
    }

    @DeleteMapping("/{id}/enderecos/{idEndereco}")
    public void removerEndereco(@PathVariable("id") Long id, @PathVariable("idEndereco") Long idEndereco) {
        removerEnderecoUsuarioService.removerEndereco(id, idEndereco);
    }

    @PutMapping("/{id}")
    public Usuario alterarUsuario(@PathVariable("id") Long id, @RequestBody @Valid AlterarUsuarioRequest request) {
        return alterarUsuarioService.alterarUsuario(id, request);
    }
}
