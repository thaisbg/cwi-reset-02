package br.com.cwi.resetflix.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.cwi.resetflix.domain.Genero;
import br.com.cwi.resetflix.entity.FilmeEntity;

@Repository
public class FilmeRepository {

    static List<FilmeEntity> filmes = new ArrayList<>();
    static Long contadorIds = 1l;

    public List<FilmeEntity> getFilmes() {
        return filmes;
    }

    public List<FilmeEntity> acharFilmesAtor(final Long id) {

        final List<FilmeEntity> filmes = new ArrayList<>();

        for (final FilmeEntity filme : FilmeRepository.filmes) {
            if (filme.getIdsAtores().contains(id)) {
                filmes.add(filme);
            }
        }

        return filmes;
    }

    public List<FilmeEntity> acharFilmesDiretor(final Long id) {

        final List<FilmeEntity> filmes = new ArrayList<>();

        for (final FilmeEntity filme : FilmeRepository.filmes) {
            if (filme.getIdDiretor().equals(id)) {
                filmes.add(filme);
            }
        }

        return filmes;
    }

    public Long criarFilme(final FilmeEntity filmeSalvar) {
        if (filmeSalvar.getId() == null) {
            filmeSalvar.setId(contadorIds);
            contadorIds++;
        }

        filmes.add(filmeSalvar);

        return filmeSalvar.getId();
    }

    public FilmeEntity acharFilmePorId(final Long id) {

        for (final FilmeEntity filmeEntity : filmes) {
            if (filmeEntity.getId().equals(id)) {
                return filmeEntity;
            }
        }

        return null;
    }

    public List<FilmeEntity> acharFilmesPorIds(final List<Long> ids) {

        final List<FilmeEntity> filmesContidos = new ArrayList<>();

        for (final FilmeEntity filmeEntity : filmes) {
            if (ids.contains(filmeEntity.getId())) {
                filmesContidos.add(filmeEntity);
            }
        }

        return filmesContidos;
    }


    public List<FilmeEntity> acharFilmesDeGeneroExcetoAssistidos(final Genero generoMaisAssistido,
        final List<Long> idsFilmesAssistidos) {

        final List<FilmeEntity> list = new ArrayList<>();
        for (final FilmeEntity filme : filmes) {
            if (filme.getGenero().equals(generoMaisAssistido)) {
                if (!idsFilmesAssistidos.contains(filme.getId())) {
                    list.add(filme);
                }
            }
        }
        return list;
    }
}
