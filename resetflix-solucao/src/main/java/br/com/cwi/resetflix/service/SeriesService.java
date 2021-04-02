package br.com.cwi.resetflix.service;

import static org.apache.logging.log4j.util.Strings.isEmpty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cwi.resetflix.domain.Genero;
import br.com.cwi.resetflix.entity.AtorEntity;
import br.com.cwi.resetflix.entity.SerieEntity;
import br.com.cwi.resetflix.exception.BadRequestException;
import br.com.cwi.resetflix.exception.NotFoundException;
import br.com.cwi.resetflix.exception.UsuarioDesocupadoException;
import br.com.cwi.resetflix.mapper.ConsultarDetalhesSerieResponseMapper;
import br.com.cwi.resetflix.mapper.SerieEntityMapper;
import br.com.cwi.resetflix.mapper.SerieResponseMapper;
import br.com.cwi.resetflix.repository.AtoresRepository;
import br.com.cwi.resetflix.repository.SerieRepository;
import br.com.cwi.resetflix.repository.UsuarioRepository;
import br.com.cwi.resetflix.request.CriarSerieRequest;
import br.com.cwi.resetflix.response.ConsultarDetalhesSerieResponse;
import br.com.cwi.resetflix.response.SerieResponse;

@Service
public class SeriesService {

    @Autowired
    private SerieRepository serieRepository;

    @Autowired
    private AtoresRepository atoresRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SerieResponseMapper serieResponseMapper;

    @Autowired
    private SerieEntityMapper serieEntityMapper;

    @Autowired
    private ConsultarDetalhesSerieResponseMapper consultarDetalhesSerieResponseMapper;

    public List<SerieResponse> getSeries(final Genero genero) {

        List<SerieEntity> series = serieRepository.getSeries();

        if (genero != null) {
            series = series.stream().filter(filme -> genero.equals(filme.getGenero())).collect(Collectors.toList());
        }

        return serieResponseMapper.mapear(series);
    }

    public Long criarSerie(final CriarSerieRequest request) {

        if (request == null || isEmpty(request.getNome())) {
            throw new BadRequestException("Dados inválidos para cadastro de série");
        }

        final SerieEntity serieSalvar = serieEntityMapper.mapear(request);
        return serieRepository.criarSerie(serieSalvar);
    }

    public ConsultarDetalhesSerieResponse consultarDetalhesSerie(final Long id) {
        final SerieEntity serieSalva = serieRepository.acharSeriePorId(id);

        if (serieSalva == null) {
            throw new NotFoundException("Série não encontrada");
        }

        final List<AtorEntity> atoresFilme = atoresRepository.acharAtoresSeries(serieSalva.getIdsAtores());
        return consultarDetalhesSerieResponseMapper.mapear(serieSalva, atoresFilme);
    }

    public List<SerieResponse> obterSeriesRecomendadas() {

        final List<Long> idsSeriesAssistidas = usuarioRepository.getIdsSeriesAssistidas();

        final Genero generoMaisAssistido = obterGeneroMaisAssistido(idsSeriesAssistidas);

        final List<SerieEntity> seriesRecomendadas = serieRepository
            .acharSeriesDeGeneroExcetoAssistidas(generoMaisAssistido, idsSeriesAssistidas);

        if (seriesRecomendadas.isEmpty()) {
            throw new UsuarioDesocupadoException("Po meu, ai fica dificil");
        }

        return serieResponseMapper.mapear(seriesRecomendadas);
    }

    private Genero obterGeneroMaisAssistido(final List<Long> idsSeriesAssistidas) {

        final Map<Genero, Integer> generoOcorrencias = new HashMap<>();

        final List<SerieEntity> series = serieRepository.acharSeriesPorIds(idsSeriesAssistidas);

        for (SerieEntity serie : series) {

            Genero genero = serie.getGenero();
            if (generoOcorrencias.containsKey(genero)) {
                generoOcorrencias.put(genero, generoOcorrencias.get(genero) + 1);
            } else {
                generoOcorrencias.put(genero, 0);
            }
        }

        int contagemMaxima = -1;
        Genero maisAssistido = null;

        for (final Entry<Genero, Integer> val : generoOcorrencias.entrySet()) {
            if (contagemMaxima < val.getValue()) {
                maisAssistido = val.getKey();
                contagemMaxima = val.getValue();
            }
        }

        return maisAssistido;
    }
}
