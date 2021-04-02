package br.com.cwi.resetflix.mapper;

import org.springframework.stereotype.Component;

import br.com.cwi.resetflix.entity.FilmeEntity;
import br.com.cwi.resetflix.request.CriarFilmeRequest;

@Component
public class FilmeEntityMapper {

    public FilmeEntity mapear(final CriarFilmeRequest request) {
        return new FilmeEntity(request.getNome(), request.getGenero(),
            request.getIdDiretor(), request.getIdsAtores());
    }
}
