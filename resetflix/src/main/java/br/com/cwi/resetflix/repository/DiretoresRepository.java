package br.com.cwi.resetflix.repository;

import br.com.cwi.resetflix.entity.DiretorEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DiretoresRepository {
    static List<DiretorEntity> diretores = new ArrayList<>();
    static Long contadorIds = 1l;

    public List<DiretorEntity> getDiretores(){
        return diretores;
    }

    // armazena uma instância de DiretorEntity na lista de diretores, atribuindo um id automático por meio de um contador e retorna o id do diretor criado
    public Long criarDiretor(final DiretorEntity diretorSalvar) {
        if (diretorSalvar.getId() == null) {
            diretorSalvar.setId(contadorIds);
            contadorIds++;
        }
        diretores.add(diretorSalvar);
        return diretorSalvar.getId();
    }

    public DiretorEntity acharDiretorPorId(final Long id) {
        for (DiretorEntity diretorEntity : diretores) {
            if(diretorEntity.getId().equals(id)) {
                return diretorEntity;
            }
        }
        return null;
    }

    public DiretorEntity acharDiretorPorFilme(final Long id) {
        for (DiretorEntity diretorEntity : diretores) {
            if(diretorEntity.getIdsFilmes().contains(id)) {
                return diretorEntity;
            }
        }
        return null;
    }
}
