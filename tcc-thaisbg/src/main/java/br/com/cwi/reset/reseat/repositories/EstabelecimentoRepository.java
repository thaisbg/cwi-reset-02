package br.com.cwi.reset.reseat.repositories;

import br.com.cwi.reset.reseat.dominio.Estabelecimento;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstabelecimentoRepository extends PagingAndSortingRepository<Estabelecimento, Long> {

    boolean existsByCnpj(String cnpj);

}
