package br.com.cwi.reset.reseat.repositories;

import br.com.cwi.reset.reseat.dominio.Produto;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Long> {
}
