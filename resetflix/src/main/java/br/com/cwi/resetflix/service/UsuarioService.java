package br.com.cwi.resetflix.service;

import br.com.cwi.resetflix.entity.FilmeEntity;
import br.com.cwi.resetflix.repository.FilmeRepository;
import br.com.cwi.resetflix.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Long assistirFilme(Long id) {
        FilmeEntity filmeParaAssistir = filmeRepository.acharFilmePorId(id);
        List<FilmeEntity> filmesAssistidos = new ArrayList<>();
        filmesAssistidos.add(filmeParaAssistir);
        return filmeParaAssistir.getId();
    }

}
