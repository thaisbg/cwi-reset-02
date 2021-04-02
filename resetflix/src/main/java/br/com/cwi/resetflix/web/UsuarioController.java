package br.com.cwi.resetflix.web;

import br.com.cwi.resetflix.domain.Genero;
import br.com.cwi.resetflix.entity.FilmeEntity;
import br.com.cwi.resetflix.response.FilmesResponse;
import br.com.cwi.resetflix.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;


    @GetMapping
    public Set<FilmeEntity> getFilmes() {
        return usuarioService.consultarHistorico();
    }
}
