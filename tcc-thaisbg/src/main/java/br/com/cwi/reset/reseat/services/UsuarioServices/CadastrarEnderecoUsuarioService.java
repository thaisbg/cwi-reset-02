package br.com.cwi.reset.reseat.services.UsuarioServices;

import br.com.cwi.reset.reseat.dominio.Endereco;
import br.com.cwi.reset.reseat.dominio.Usuario;
import br.com.cwi.reset.reseat.exceptions.UsuarioNaoEncontradoException;
import br.com.cwi.reset.reseat.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastrarEnderecoUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    public void cadastrarEndereco(Long id, Endereco endereco) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado. Digite um ID válido."));
        Endereco enderecoSalvar = endereco;
        List<Endereco> listaEnderecos = usuario.getEnderecos();

        listaEnderecos.add(enderecoSalvar);
        usuario.setEnderecos(listaEnderecos);
        usuarioRepository.save(usuario);
    }

}
