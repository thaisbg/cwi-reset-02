package br.com.cwi.resetflix.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.cwi.resetflix.domain.Genero;
import br.com.cwi.resetflix.entity.SerieEntity;

@Repository
public class SerieRepository {

    static List<SerieEntity> series = new ArrayList<>();
    static Long contadorIds = 1l;

    public List<SerieEntity> getSeries() {
        return series;
    }

    public List<SerieEntity> acharSeriesAtor(final Long id) {

        final List<SerieEntity> series = new ArrayList<>();

        for (final SerieEntity serie : SerieRepository.series) {
            if (serie.getIdsAtores().contains(id)) {
                series.add(serie);
            }
        }

        return series;
    }

    public Long criarSerie(final SerieEntity serieSalvar) {
        if (serieSalvar.getId() == null) {
            serieSalvar.setId(contadorIds);
            contadorIds++;
        }

        series.add(serieSalvar);

        return serieSalvar.getId();
    }

    public SerieEntity acharSeriePorId(final Long id) {

        for (final SerieEntity serieEntity : series) {
            if (serieEntity.getId().equals(id)) {
                return serieEntity;
            }
        }

        return null;
    }

    public List<SerieEntity> acharSeriesPorIds(final List<Long> ids) {

        final List<SerieEntity> seriesContidas = new ArrayList<>();

        for (final SerieEntity serieEntity : series) {
            if (ids.contains(serieEntity.getId())) {
                seriesContidas.add(serieEntity);
            }
        }

        return seriesContidas;
    }

    public List<SerieEntity> acharSeriesDeGeneroExcetoAssistidas(final Genero generoMaisAssistido,
        final List<Long> idsSeriesAssistidas) {

        final List<SerieEntity> list = new ArrayList<>();
        for (final SerieEntity serie : series) {
            if (serie.getGenero().equals(generoMaisAssistido)) {
                if (!idsSeriesAssistidas.contains(serie.getId())) {
                    list.add(serie);
                }
            }
        }
        return list;
    }


}
