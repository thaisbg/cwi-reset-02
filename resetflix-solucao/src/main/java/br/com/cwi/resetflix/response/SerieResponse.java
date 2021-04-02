package br.com.cwi.resetflix.response;

import br.com.cwi.resetflix.domain.Genero;

public class SerieResponse {

    private Long id;
    private String nome;
    private Genero genero;

    public SerieResponse(final Long id, final String nome, final Genero genero) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(final Genero genero) {
        this.genero = genero;
    }
}