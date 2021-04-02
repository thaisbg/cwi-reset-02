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
import br.com.cwi.resetflix.entity.DiretorEntity;
import br.com.cwi.resetflix.entity.FilmeEntity;
import br.com.cwi.resetflix.exception.BadRequestException;
import br.com.cwi.resetflix.exception.NotFoundException;
import br.com.cwi.resetflix.exception.UsuarioDesocupadoException;
import br.com.cwi.resetflix.mapper.ConsultarDetalhesFilmeResponseMapper;
import br.com.cwi.resetflix.mapper.FilmeEntityMapper;
import br.com.cwi.resetflix.mapper.FilmeResponseMapper;
import br.com.cwi.resetflix.repository.AtoresRepository;
import br.com.cwi.resetflix.repository.DiretoresRepository;
import br.com.cwi.resetflix.repository.FilmeRepository;
import br.com.cwi.resetflix.repository.UsuarioRepository;
import br.com.cwi.resetflix.request.CriarFilmeRequest;
import br.com.cwi.resetflix.response.ConsultarDetalhesFilmeResponse;
import br.com.cwi.resetflix.response.FilmeResponse;

@Service
public class FilmesService {

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private AtoresRepository atoresRepository;

    @Autowired
    private DiretoresRepository diretoresRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FilmeResponseMapper filmeResponseMapper;

    @Autowired
    private FilmeEntityMapper filmeEntityMapper;

    @Autowired
    private ConsultarDetalhesFilmeResponseMapper consultarDetalhesFilmeResponseMapper;

    public List<FilmeResponse> getFilmes(final Genero genero) {

        List<FilmeEntity> filmes = filmeRepository.getFilmes();

        if (genero != null) {
            filmes = filmes.stream().filter(filme -> genero.equals(filme.getGenero())).collect(Collectors.toList());
        }
        return filmeResponseMapper.mapear(filmes);
    }

    public Long criarFilme(final CriarFilmeRequest request) {

        if (request == null || isEmpty(request.getNome())) {
            throw new BadRequestException("Dados inválidos para cadastro de filme");
        }

        final FilmeEntity filmeSalvar = filmeEntityMapper.mapear(request);
        return filmeRepository.criarFilme(filmeSalvar);
    }

    public ConsultarDetalhesFilmeResponse consultarDetalhesFilme(final Long id) {
        final FilmeEntity filmeSalvo = filmeRepository.acharFilmePorId(id);

        if (filmeSalvo == null) {
            throw new NotFoundException("Filme não encontrado");
        }

        final DiretorEntity diretor = diretoresRepository.acharDiretorPorId(filmeSalvo.getIdDiretor());
        final List<AtorEntity> atoresFilme = atoresRepository.acharAtoresFilme(id);
        return consultarDetalhesFilmeResponseMapper.mapear(filmeSalvo, diretor, atoresFilme);
    }

    public List<FilmeResponse> obterFilmesRecomendados() {
        final List<Long> idsFilmesAssistidos = usuarioRepository.getIdsFilmesAssistidos();
        final Genero generoMaisAssistido = obterGeneroMaisAssistido(idsFilmesAssistidos);
        List<FilmeEntity> filmesRecomendados = filmeRepository.acharFilmesDeGeneroExcetoAssistidos(generoMaisAssistido, idsFilmesAssistidos);

        if(filmesRecomendados.isEmpty()){
            throw new UsuarioDesocupadoException("Po meu, ai fica dificil");
        }

        return filmeResponseMapper.mapear(filmesRecomendados);
    }

    private Genero obterGeneroMaisAssistido(final List<Long> idsFilmesAssistidos) {

        final Map<Genero, Integer> generoOcorrencias = new HashMap<>();

        final List<FilmeEntity> filmes = filmeRepository.acharFilmesPorIds(idsFilmesAssistidos);

        filmes.stream()
            .map(filme -> filme.getGenero())
            .forEach(genero -> {
                if (generoOcorrencias.containsKey(genero)) {
                    generoOcorrencias.put(genero, generoOcorrencias.get(genero) + 1);
                } else {
                    generoOcorrencias.put(genero, 0);
                }
            });

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
