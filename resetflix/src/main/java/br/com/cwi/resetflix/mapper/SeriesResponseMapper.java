package br.com.cwi.resetflix.mapper;

import br.com.cwi.resetflix.entity.SerieEntity;
import br.com.cwi.resetflix.response.SeriesResponse;

import java.util.ArrayList;
import java.util.List;

public class SeriesResponseMapper {

    public List<SeriesResponse> mapear(final List<SerieEntity> series) {
        List<SeriesResponse> seriesResponses = new ArrayList<>();

        for (SerieEntity serieEntity : series) {
            SeriesResponse serieResponse = new SeriesResponse(serieEntity.getId(), serieEntity.getNome(), serieEntity.getGenero());
            seriesResponses.add(serieResponse);
        }

        return seriesResponses;
    }

    public List<SeriesResponse> mapearSeriesAtor(final List<SerieEntity> seriesAtor) {
        List<SeriesResponse> seriesAtorResponse = new ArrayList<>();

        for(SerieEntity serieEntity : seriesAtor) {
            seriesAtorResponse.add(new SeriesResponse(serieEntity.getId(), serieEntity.getNome(), serieEntity.getGenero()));
        }
        return seriesAtorResponse;
    }
}
