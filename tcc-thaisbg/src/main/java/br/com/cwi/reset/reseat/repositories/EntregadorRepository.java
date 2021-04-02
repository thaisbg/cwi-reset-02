package br.com.cwi.reset.reseat.repositories;

import br.com.cwi.reset.reseat.dominio.Entregador;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntregadorRepository extends PagingAndSortingRepository<Entregador, Long> {

    boolean existsByCpf(String cpf);

    List<Entregador> findAllByDisponivelTrue();
}
