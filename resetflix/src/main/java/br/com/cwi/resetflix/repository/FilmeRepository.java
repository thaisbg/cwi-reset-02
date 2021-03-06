package br.com.cwi.resetflix.repository;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.cwi.resetflix.exception.NotFoundException;
import br.com.cwi.resetflix.response.FilmesResponse;
import org.springframework.stereotype.Repository;

import br.com.cwi.resetflix.domain.Genero;
import br.com.cwi.resetflix.entity.FilmeEntity;

@Repository
public class FilmeRepository {

    static List<FilmeEntity> filmes = new ArrayList<>();
    static Long contadorIds = 1l;

    public List<FilmeEntity> getFilmes(){
        return filmes;
    }

    public Long criarFilme(final FilmeEntity filmeSalvar) {
        if(filmeSalvar.getId() == null) {
            filmeSalvar.setId(contadorIds);
            contadorIds++;
        }
        filmes.add(filmeSalvar);
        return filmeSalvar.getId();
    }

    public List<FilmeEntity> acharFilmesAtor(final Long id) {
        List<FilmeEntity> filmesAtor = new ArrayList<>();

        for(FilmeEntity filmeEntity : filmes) {
            if (filmeEntity.getIdsAtores().contains(id)) {
                filmesAtor.add(filmeEntity);
            }
        }
        return filmesAtor;
    }

    public List<FilmeEntity> acharFilmesDiretor(final Long id) {
        List<FilmeEntity> filmesDiretor = new ArrayList<>();

        for(FilmeEntity filmeEntity : filmes) {
            if (filmeEntity.getIdDiretor().equals(id)) {
                filmesDiretor.add(filmeEntity);
            }
        }
        return filmesDiretor;
    }

    public FilmeEntity acharFilmePorId(final Long id) {

        for(FilmeEntity filmeEntity : filmes) {
            if(filmeEntity.getId().equals(id)) {
                return filmeEntity;
            }
        }
        return null;
    }

}
