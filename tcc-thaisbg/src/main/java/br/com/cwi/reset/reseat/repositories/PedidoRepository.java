package br.com.cwi.reset.reseat.repositories;

import br.com.cwi.reset.reseat.dominio.Pedido;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends PagingAndSortingRepository<Pedido, Long> {
}
