package br.com.cwi.resetflix.mapper;

import br.com.cwi.resetflix.entity.AtorEntity;
import br.com.cwi.resetflix.entity.SerieEntity;
import br.com.cwi.resetflix.response.AtoresResponse;
import br.com.cwi.resetflix.response.ConsultarDetalhesSerieResponse;

import java.util.List;

public class ConsultarDetalhesSerieResponseMapper {

    public ConsultarDetalhesSerieResponse mapear(final SerieEntity serieSalva, final List<AtorEntity> atores) {
        List<AtoresResponse> atoresResponse = new AtoresResponseMapper().mapear(atores);
        return new ConsultarDetalhesSerieResponse(serieSalva.getId(), serieSalva.getNome(), serieSalva.getGenero(), serieSalva.getQuantidadeDeTemporadas(), serieSalva.getQuantidadeDeEpisodios(), atoresResponse);
    }
}
