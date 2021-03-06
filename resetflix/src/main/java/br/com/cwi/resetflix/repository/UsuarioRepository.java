package br.com.cwi.resetflix.repository;

import br.com.cwi.resetflix.entity.FilmeEntity;
import br.com.cwi.resetflix.entity.SerieEntity;

import java.util.HashSet;
import java.util.Set;

public class UsuarioRepository {

    private Set<FilmeEntity> filmesAssistidos = new HashSet<>();
    private Set<SerieEntity> seriesAssistidas = new HashSet<>();


}
