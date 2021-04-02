package br.com.cwi.reset.reseat.repositories;

import br.com.cwi.reset.reseat.dominio.Usuario;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long> {

    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
    Optional<Usuario> findById(Long id);
}
