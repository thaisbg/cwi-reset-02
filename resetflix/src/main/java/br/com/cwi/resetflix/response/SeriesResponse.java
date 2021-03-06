package br.com.cwi.resetflix.response;

import br.com.cwi.resetflix.domain.Genero;

public class SeriesResponse {

    private Long id;
    private String nome;
    private Genero genero;

    public SeriesResponse(Long id, String nome, Genero genero) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
}