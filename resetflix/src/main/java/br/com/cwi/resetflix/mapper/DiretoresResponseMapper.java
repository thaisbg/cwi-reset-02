package br.com.cwi.resetflix.mapper;

import br.com.cwi.resetflix.entity.DiretorEntity;
import br.com.cwi.resetflix.response.DiretoresResponse;

import java.util.ArrayList;
import java.util.List;

public class DiretoresResponseMapper {
    public List<DiretoresResponse> mapear(List<DiretorEntity> diretores) {
        List<DiretoresResponse> diretoresResponse = new ArrayList();

        for(DiretorEntity diretorEntity : diretores) {
            DiretoresResponse diretorResponse = new DiretoresResponse(diretorEntity.getId(), diretorEntity.getNome());

            diretoresResponse.add(diretorResponse);

        }

        return diretoresResponse;
    }
}
