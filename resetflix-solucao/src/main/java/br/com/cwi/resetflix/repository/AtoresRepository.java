package br.com.cwi.resetflix.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.cwi.resetflix.entity.AtorEntity;

@Repository
public class AtoresRepository {

    static List<AtorEntity> atores = new ArrayList<>();
    static Long contadorIds = 1l;

    public List<AtorEntity> getAtores() {
        return atores;
    }

    public Long criarAtor(final AtorEntity atorSalvar) {
        if (atorSalvar.getId() == null) {
            atorSalvar.setId(contadorIds);
            contadorIds++;
        }

        atores.add(atorSalvar);

        return atorSalvar.getId();
    }

    public AtorEntity acharAtorPorId(final Long id) {

        for (final AtorEntity atorEntity : atores) {
            if (atorEntity.getId().equals(id)) {
                return atorEntity;
            }
        }

        return null;
    }

    public List<AtorEntity> acharAtoresFilme(final Long idFilme) {
        final List<AtorEntity> list = new ArrayList<>();

        for (final AtorEntity ator : atores) {
            if (ator.getIdsFilmes().contains(idFilme)) {
                list.add(ator);
            }
        }

        return list;
    }

    public List<AtorEntity> acharAtoresSeries(final List<Long> idsAtores) {
        final List<AtorEntity> list = new ArrayList<>();

        for (final AtorEntity ator : atores) {
            if (idsAtores.contains(ator.getId())) {
                list.add(ator);
            }
        }

        return list;
    }
}
