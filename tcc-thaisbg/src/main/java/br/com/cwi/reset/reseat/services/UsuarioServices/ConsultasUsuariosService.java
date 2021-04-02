package br.com.cwi.reset.reseat.services.UsuarioServices;

import br.com.cwi.reset.reseat.dominio.Usuario;
import br.com.cwi.reset.reseat.exceptions.UsuarioNaoEncontradoException;
import br.com.cwi.reset.reseat.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ConsultasUsuariosService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<Usuario> listarUsuarios(Integer pagina, Integer tamanho, String ordenacao) {
        Pageable paginacao = PageRequest.of(pagina, tamanho, Sort.by(ordenacao).ascending());
        return usuarioRepository.findAll(paginacao);
    }

    public Usuario buscarUsuarioPorId(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado."));
    }
}
