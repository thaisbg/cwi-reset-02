package br.com.cwi.resetflix.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.cwi.resetflix.entity.AtorEntity;
import br.com.cwi.resetflix.entity.DiretorEntity;
import br.com.cwi.resetflix.entity.FilmeEntity;
import br.com.cwi.resetflix.response.AtoresResponse;
import br.com.cwi.resetflix.response.ConsultarDetalhesFilmeResponse;
import br.com.cwi.resetflix.response.DiretoresResponse;

@Component
public class ConsultarDetalhesFilmeResponseMapper {

    public ConsultarDetalhesFilmeResponse mapear(final FilmeEntity filmeSalvo, final DiretorEntity diretorEntity,
        final List<AtorEntity> atoresFilme) {

        final List<AtoresResponse> atores = new ArrayList<>();

        for (final AtorEntity ator : atoresFilme) {
            final AtoresResponse response = new AtoresResponse(ator.getId(), ator.getNome());
            atores.add(response);
        }

        return new ConsultarDetalhesFilmeResponse(
            filmeSalvo.getId(),
            filmeSalvo.getNome(),
            filmeSalvo.getGenero(),
            new DiretoresResponse(diretorEntity.getId(), diretorEntity.getNome()),
            atores);
    }
}
