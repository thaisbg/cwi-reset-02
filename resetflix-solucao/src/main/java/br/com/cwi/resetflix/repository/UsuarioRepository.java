package br.com.cwi.resetflix.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepository {

    static List<Long> idsFilmesAssistidos = new ArrayList<>();
    static List<Long> idsSeriesAssistidas = new ArrayList<>();

    public void registrarSerieAssistida(final Long idSerie) {
        idsSeriesAssistidas.add(idSerie);
    }

    public void registrarFilmeAssistido(final Long idFilme) {
        idsFilmesAssistidos.add(idFilme);
    }

    public List<Long> getIdsFilmesAssistidos() {
        return idsFilmesAssistidos;
    }

    public List<Long> getIdsSeriesAssistidas() {
        return idsSeriesAssistidas;
    }
}
