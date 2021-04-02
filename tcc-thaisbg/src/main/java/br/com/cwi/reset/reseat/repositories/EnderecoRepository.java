package br.com.cwi.reset.reseat.repositories;

import br.com.cwi.reset.reseat.dominio.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
