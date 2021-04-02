package br.com.cwi.reset.reseat.services.EntregadorServices;

import br.com.cwi.reset.reseat.dominio.Entregador;
import br.com.cwi.reset.reseat.repositories.EntregadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ConsultasEntregadoresService {

    @Autowired
    private EntregadorRepository entregadorRepository;

    public Page<Entregador> listarEntregadores(Integer pagina, Integer tamanho, String ordenacao) {
        Pageable paginacao = PageRequest.of(pagina, tamanho, Sort.by(ordenacao).ascending());
        return entregadorRepository.findAll(paginacao);
    }
}
