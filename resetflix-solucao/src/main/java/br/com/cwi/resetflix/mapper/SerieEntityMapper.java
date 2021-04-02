package br.com.cwi.resetflix.mapper;

import org.springframework.stereotype.Component;

import br.com.cwi.resetflix.entity.SerieEntity;
import br.com.cwi.resetflix.request.CriarSerieRequest;

@Component
public class SerieEntityMapper {

    public SerieEntity mapear(final CriarSerieRequest request) {
        return new SerieEntity(request.getNome(), request.getGenero(),
            request.getIdsAtores(), request.getTemporadas(), request.getEpisodios());
    }
}
