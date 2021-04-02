package br.com.cwi.resetflix.repository;

import br.com.cwi.resetflix.entity.FilmeEntity;
import br.com.cwi.resetflix.entity.SerieEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UsuarioRepository {

    private Set<FilmeEntity> filmesAssistidos = new HashSet<>();
    private Set<SerieEntity> seriesAssistidas = new HashSet<>();

    public void registrarFilmeNoHistorico(Long id) {
        List<FilmeEntity> listaFilmes = new ArrayList<>();
        for(FilmeEntity filmeEntity : listaFilmes) {
            if (filmeEntity.getId().equals(id)) {
                filmesAssistidos.add(filmeEntity);
            }
        }
    }

    public Set<FilmeEntity> consultaHistorico() {
        return filmesAssistidos;
    }
}
