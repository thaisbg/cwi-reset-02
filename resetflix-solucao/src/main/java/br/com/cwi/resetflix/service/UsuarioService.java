package br.com.cwi.resetflix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cwi.resetflix.entity.FilmeEntity;
import br.com.cwi.resetflix.entity.SerieEntity;
import br.com.cwi.resetflix.exception.BadRequestException;
import br.com.cwi.resetflix.exception.NotFoundException;
import br.com.cwi.resetflix.repository.FilmeRepository;
import br.com.cwi.resetflix.repository.SerieRepository;
import br.com.cwi.resetflix.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private SerieRepository serieRepository;

    public void assistirFilme(final Long idFilme) {

        final FilmeEntity filme = filmeRepository.acharFilmePorId(idFilme);

        if (filme == null) {
            throw new NotFoundException("Filme inválido informado");
        }

        usuarioRepository.registrarFilmeAssistido(idFilme);
    }

    public void assistirSerie(final Long idSerie, final Integer temporada, final Integer episodio) {

        final SerieEntity serie = serieRepository.acharSeriePorId(idSerie);

        if (serie == null) {
            throw new NotFoundException("Série inválida informada");
        }

        if (temporada > serie.getTemporadas() || episodio > serie.getEpisodios()) {
            throw new BadRequestException("Episódio ou Temporada inválidos");
        }

        usuarioRepository.registrarSerieAssistida(idSerie);
    }
}
