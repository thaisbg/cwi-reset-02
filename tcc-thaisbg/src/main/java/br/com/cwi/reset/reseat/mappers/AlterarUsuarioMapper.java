package br.com.cwi.reset.reseat.mappers;

import br.com.cwi.reset.reseat.dominio.Usuario;
import br.com.cwi.reset.reseat.exceptions.UsuarioNaoEncontradoException;
import br.com.cwi.reset.reseat.repositories.UsuarioRepository;
import br.com.cwi.reset.reseat.requests.AlterarUsuarioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlterarUsuarioMapper {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario mapear (Long id, AlterarUsuarioRequest request) {
        Usuario usuarioAlterar = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException("O usuário não foi encontrado."));
        usuarioAlterar.setNome(request.getNome());
        usuarioAlterar.setEmail(request.getEmail());
        usuarioAlterar.setSenha(request.getSenha());
        usuarioAlterar.setEnderecos(request.getEnderecos());
        return usuarioAlterar;

    }

}
