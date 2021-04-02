package br.com.cwi.resetflix.mapper;

import org.springframework.stereotype.Component;

import br.com.cwi.resetflix.entity.AtorEntity;
import br.com.cwi.resetflix.entity.DiretorEntity;
import br.com.cwi.resetflix.request.CriarAtorRequest;
import br.com.cwi.resetflix.request.CriarDiretorRequest;

@Component
public class DiretorEntityMapper {

    public DiretorEntity mapear(final CriarDiretorRequest request) {
        return new DiretorEntity(request.getNome(), request.getIdFilmes());
    }
}
