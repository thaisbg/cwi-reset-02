package br.com.cwi.resetflix.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.cwi.resetflix.entity.AtorEntity;
import br.com.cwi.resetflix.entity.SerieEntity;
import br.com.cwi.resetflix.response.AtoresResponse;
import br.com.cwi.resetflix.response.ConsultarDetalhesSerieResponse;

@Component
public class ConsultarDetalhesSerieResponseMapper {

    public ConsultarDetalhesSerieResponse mapear(final SerieEntity serieSalva,
        final List<AtorEntity> atoresSerie) {

        final List<AtoresResponse> atores = new ArrayList<>();

        for (final AtorEntity ator : atoresSerie) {
            final AtoresResponse response = new AtoresResponse(ator.getId(), ator.getNome());
            atores.add(response);
        }

        return new ConsultarDetalhesSerieResponse(
            serieSalva.getId(),
            serieSalva.getNome(),
            serieSalva.getGenero(),
            atores,
            serieSalva.getTemporadas(),
            serieSalva.getEpisodios());
    }
}
