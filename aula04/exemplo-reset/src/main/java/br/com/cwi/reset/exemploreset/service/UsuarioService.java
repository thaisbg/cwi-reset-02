package br.com.cwi.reset.exemploreset.service;

import br.com.cwi.reset.exemploreset.domain.Usuario;
import br.com.cwi.reset.exemploreset.exception.BadRequestException;
import br.com.cwi.reset.exemploreset.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> listarUsuarios() {
        return repository.listarUsuarios();
    }

    public Usuario cadastrarUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new BadRequestException();
        }

        if (usuario.getId() != null) {
            throw new BadRequestException();
        }

        return repository.salvarUsuario(usuario);
    }

    public void deletarUsuario(String idDeletar) {
        repository.deletarUsuario(idDeletar);
    }
}
