package br.com.cwi.resetflix.mapper;

import java.util.ArrayList;
import java.util.List;

import br.com.cwi.resetflix.entity.FilmeEntity;
import br.com.cwi.resetflix.response.FilmesResponse;

public class FilmesResponseMapper {

    public List<FilmesResponse> mapear(final List<FilmeEntity> filmes) {
        List<FilmesResponse> filmesResponses = new ArrayList<>();

        for(FilmeEntity filmeEntity : filmes) {
            FilmesResponse filmeResponse = new FilmesResponse(filmeEntity.getId(), filmeEntity.getNome(), filmeEntity.getGenero());
            filmesResponses.add(filmeResponse);
        }

        return filmesResponses;
    }

    public List<FilmesResponse> mapearFilmesAtor(final List<FilmeEntity> filmesAtor) {
        List<FilmesResponse> filmesAtorResponse = new ArrayList<>();
        for(FilmeEntity filmeEntity : filmesAtor){
            filmesAtorResponse.add(new FilmesResponse(filmeEntity.getId(),
                    filmeEntity.getNome(), filmeEntity.getGenero()));
        }
        return filmesAtorResponse;
    }

    public List<FilmesResponse> mapearFilmesDiretor(final List<FilmeEntity> filmesDiretor) {
        List<FilmesResponse> filmesDiretorResponse = new ArrayList<>();

        for(FilmeEntity filmeEntity : filmesDiretor){
            filmesDiretorResponse.add(new FilmesResponse(filmeEntity.getId(),
                    filmeEntity.getNome(), filmeEntity.getGenero()));
        }
        return filmesDiretorResponse;
    }
}
