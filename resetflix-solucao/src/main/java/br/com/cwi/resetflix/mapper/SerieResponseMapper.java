package br.com.cwi.resetflix.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.cwi.resetflix.entity.SerieEntity;
import br.com.cwi.resetflix.response.FilmeResponse;
import br.com.cwi.resetflix.response.SerieResponse;

@Component
public class SerieResponseMapper {

    public List<SerieResponse> mapear(final List<SerieEntity> seriesAtor) {
        List<SerieResponse> responses = new ArrayList<>();

        for(SerieEntity serieEntity : seriesAtor){
            responses.add(new SerieResponse(serieEntity.getId(),
                serieEntity.getNome(), serieEntity.getGenero()));
        }

        return responses;
    }
}
