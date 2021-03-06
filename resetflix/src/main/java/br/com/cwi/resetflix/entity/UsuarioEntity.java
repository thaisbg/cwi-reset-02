package br.com.cwi.resetflix.entity;

import br.com.cwi.resetflix.response.FilmesResponse;
import br.com.cwi.resetflix.response.SeriesResponse;

import java.util.*;

public class UsuarioEntity {

    private String username = "usuario_teste";
    private String senha = "senha123";


    public UsuarioEntity() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
