package br.com.cwi.resetflix.repository;


import br.com.cwi.resetflix.entity.SerieEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SeriesRepository {

    static List<SerieEntity> series = new ArrayList<>();
    static Long contadorIds = 1l;

    public List<SerieEntity> getSeries(){
        return series;
    }

    public Long criarSerie(final SerieEntity serieSalvar) {
        if(serieSalvar.getId() == null) {
            serieSalvar.setId(contadorIds);
            contadorIds++;
        }
        series.add(serieSalvar);
        return serieSalvar.getId();
    }

    public List<SerieEntity> acharSeriesAtor(final Long id) {
        List<SerieEntity> seriesAtor = new ArrayList<>();

        for(SerieEntity serieEntity : series) {
            if (serieEntity.getIdsAtores().contains(id)) {
                seriesAtor.add(serieEntity);
            }
        }
        return seriesAtor;
    }

    public SerieEntity acharSeriePorId(final Long id) {

        for(SerieEntity serieEntity : series) {
            if(serieEntity.getId().equals(id)) {
                return serieEntity;
            }
        }
        return null;
    }
}
