package br.com.cwi.reset.reseat.services.UsuarioServices;

import br.com.cwi.reset.reseat.dominio.Usuario;
import br.com.cwi.reset.reseat.exceptions.UsuarioComMesmoCpfException;
import br.com.cwi.reset.reseat.exceptions.UsuarioComMesmoEmailException;
import br.com.cwi.reset.reseat.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastrarUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario cadastrarUsuario(Usuario usuario) {
        validarUsuario(usuario);
        return usuarioRepository.save(usuario);
    }

    private void validarUsuario(Usuario usuario) {
        if (usuarioRepository.existsByCpf(usuario.getCpf())) {
            throw new UsuarioComMesmoCpfException("Já existe uma conta cadastrada com este CPF. " +
                    "Acesse a conta já existente ou tente novamente com outro CPF.");
        }
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new UsuarioComMesmoEmailException("Já existe uma conta cadastrada com este e-mail. " +
                    "Acesse a conta já existente ou tente novamente com outro e-mail.");
        }
    }
}
