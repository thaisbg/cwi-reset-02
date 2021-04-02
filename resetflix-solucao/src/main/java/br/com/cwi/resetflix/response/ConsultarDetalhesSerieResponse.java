package br.com.cwi.resetflix.response;

import java.util.List;

import br.com.cwi.resetflix.domain.Genero;

public class ConsultarDetalhesSerieResponse {

    private Long id;
    private String nome;
    private Genero genero;
    private List<AtoresResponse> atores;
    private Integer temporadas;
    private Integer episodios;

    public ConsultarDetalhesSerieResponse(final Long id, final String nome, final Genero genero,
        final List<AtoresResponse> atores, final Integer temporadas, final Integer episodios) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.atores = atores;
        this.temporadas = temporadas;
        this.episodios = episodios;
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

    public List<AtoresResponse> getAtores() {
        return atores;
    }

    public void setAtores(final List<AtoresResponse> atores) {
        this.atores = atores;
    }

    public Integer getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(final Integer temporadas) {
        this.temporadas = temporadas;
    }

    public Integer getEpisodios() {
        return episodios;
    }

    public void setEpisodios(final Integer episodios) {
        this.episodios = episodios;
    }
}
