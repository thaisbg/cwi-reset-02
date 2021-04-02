package br.com.cwi.reset.reseat.services.EstabelecimentoServices;

import br.com.cwi.reset.reseat.dominio.Estabelecimento;
import br.com.cwi.reset.reseat.exceptions.EstabelecimentoNaoEncontradoException;
import br.com.cwi.reset.reseat.repositories.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ConsultasEstabelecimentosService {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    public Page<Estabelecimento> listarEstabelecimentos(Integer pagina, Integer tamanho, String ordenacao){
        Pageable paginacao = PageRequest.of(pagina, tamanho, Sort.by(ordenacao).ascending());
        return estabelecimentoRepository.findAll(paginacao);
    }

    public Estabelecimento buscarEstabelecimentoPorId(Long id) {
        return estabelecimentoRepository.findById(id)
                .orElseThrow(() -> new EstabelecimentoNaoEncontradoException("Estabelecimento não encontrado."));
    }
}
