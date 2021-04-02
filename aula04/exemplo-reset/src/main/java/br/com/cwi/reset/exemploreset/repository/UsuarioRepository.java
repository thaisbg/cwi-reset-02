package br.com.cwi.reset.exemploreset.repository;

import br.com.cwi.reset.exemploreset.domain.Usuario;
import br.com.cwi.reset.exemploreset.exception.NotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepository {

    private static List<Usuario> usuarios = new ArrayList<>();
    private static Long nextId = 1l;

    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    public Usuario salvarUsuario(Usuario usuario){
        usuario.setId(String.valueOf(nextId));
        nextId++;
        usuarios.add(usuario);
        return usuario;
    }

    public void deletarUsuario(String idDeletar){
        Usuario deletar = null;
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(idDeletar)) {
                deletar = usuario;
            }
        }
        if (deletar == null) {
            throw new NotFoundException();
        }
        usuarios.remove(deletar);

    }
}
