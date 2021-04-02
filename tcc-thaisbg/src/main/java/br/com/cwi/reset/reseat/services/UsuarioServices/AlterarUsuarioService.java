package br.com.cwi.reset.reseat.services.UsuarioServices;

import br.com.cwi.reset.reseat.dominio.Usuario;
import br.com.cwi.reset.reseat.exceptions.UsuarioComMesmoEmailException;
import br.com.cwi.reset.reseat.mappers.AlterarUsuarioMapper;
import br.com.cwi.reset.reseat.repositories.UsuarioRepository;
import br.com.cwi.reset.reseat.requests.AlterarUsuarioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlterarUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AlterarUsuarioMapper alterarUsuarioMapper;

    public Usuario alterarUsuario(Long id, AlterarUsuarioRequest request) {
        Usuario usuarioAlterado = alterarUsuarioMapper.mapear(id, request);
        validaAlteracao(usuarioAlterado);
        usuarioRepository.save(usuarioAlterado);
        return usuarioAlterado;
    }

    public void validaAlteracao(Usuario usuario) {
        Iterable<Usuario> usuarios = usuarioRepository.findAll();

        for (Usuario usuarioTesteEmail : usuarios) {
            if ((!usuarioTesteEmail.getId().equals(usuario.getId())) && (usuarioTesteEmail.getEmail().equals(usuario.getEmail()))) {
                throw new UsuarioComMesmoEmailException("Já existe uma conta cadastrada com este e-mail.");
            }
        }
    }

}
