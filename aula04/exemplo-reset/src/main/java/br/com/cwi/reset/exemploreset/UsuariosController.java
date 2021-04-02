package br.com.cwi.reset.exemploreset;

import br.com.cwi.reset.exemploreset.domain.Usuario;
import br.com.cwi.reset.exemploreset.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")

public class UsuariosController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @PostMapping
    public Usuario cadastrarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.cadastrarUsuario(usuario);
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable("id") String idDeletar){
        usuarioService.deletarUsuario(idDeletar);
    }
}
