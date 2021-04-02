package br.com.cwi.resetflix.mapper;

import org.springframework.stereotype.Component;

import br.com.cwi.resetflix.entity.AtorEntity;
import br.com.cwi.resetflix.request.CriarAtorRequest;

@Component
public class AtorEntityMapper {

    public AtorEntity mapear(final CriarAtorRequest request) {
        return new AtorEntity(request.getNome(), request.getIdFilmes());
    }
}
