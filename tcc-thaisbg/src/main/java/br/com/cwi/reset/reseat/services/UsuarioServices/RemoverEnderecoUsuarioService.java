package br.com.cwi.reset.reseat.services.UsuarioServices;

import br.com.cwi.reset.reseat.dominio.Endereco;
import br.com.cwi.reset.reseat.dominio.Usuario;
import br.com.cwi.reset.reseat.exceptions.EnderecoNaoEncontradoException;
import br.com.cwi.reset.reseat.exceptions.UsuarioNaoEncontradoException;
import br.com.cwi.reset.reseat.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemoverEnderecoUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void removerEndereco(Long id, Long idEndereco) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usário não encontrado."));
        List<Endereco> listaEnderecos = usuario.getEnderecos();
        Endereco enderecoDeletar = null;

        for (Endereco endereco : listaEnderecos) {
            if(endereco.getId().equals(idEndereco)) {
                enderecoDeletar = endereco;
            }
        }

        if(enderecoDeletar == null) {
            throw new EnderecoNaoEncontradoException("Endereço não encontrado entre os endereços do usuário informado.");
        }

        listaEnderecos.remove(enderecoDeletar);
        usuarioRepository.save(usuario);
    }


}
