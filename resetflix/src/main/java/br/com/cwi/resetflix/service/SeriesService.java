package br.com.cwi.resetflix.service;

import br.com.cwi.resetflix.entity.AtorEntity;
import br.com.cwi.resetflix.entity.SerieEntity;
import br.com.cwi.resetflix.exception.BadRequestException;
import br.com.cwi.resetflix.exception.NotFoundException;
import br.com.cwi.resetflix.mapper.ConsultarDetalhesSerieResponseMapper;
import br.com.cwi.resetflix.mapper.SerieEntityMapper;
import br.com.cwi.resetflix.mapper.SeriesResponseMapper;
import br.com.cwi.resetflix.repository.AtoresRepository;
import br.com.cwi.resetflix.repository.SeriesRepository;
import br.com.cwi.resetflix.request.CriarSerieRequest;
import br.com.cwi.resetflix.response.ConsultarDetalhesSerieResponse;
import br.com.cwi.resetflix.response.SeriesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeriesService {

    @Autowired
    private SeriesRepository seriesRepository;

    @Autowired
    private AtoresRepository atoresRepository;

    static SeriesResponseMapper MAPPER_RESPONSE = new SeriesResponseMapper();
    static SerieEntityMapper MAPPER_ENTITY = new SerieEntityMapper();
    static ConsultarDetalhesSerieResponseMapper MAPPER_DETALHES_SERIE = new ConsultarDetalhesSerieResponseMapper();

    public List<SeriesResponse> getSeries() {
        final List<SerieEntity> series = seriesRepository.getSeries();
        return MAPPER_RESPONSE.mapear(series);
    }

    public Long criarSerie(final CriarSerieRequest request) {
        if (request.getNome() == null) {
            throw new BadRequestException("Você precisa cadastrar um nome para a série.");
        } else {
            SerieEntity serieSalvar = MAPPER_ENTITY.mapear(request);
            return seriesRepository.criarSerie(serieSalvar);
        }
    }

    public ConsultarDetalhesSerieResponse consultarDetalhesSerie(final Long id) {
        SerieEntity serieSalva = seriesRepository.acharSeriePorId(id);
        if (serieSalva == null) {
            throw new NotFoundException("Série não encontrada.");
        } else {
            List<AtorEntity> atores = atoresRepository.acharAtoresPorSerie(id);
            return MAPPER_DETALHES_SERIE.mapear(serieSalva, atores);
        }
    }
}
