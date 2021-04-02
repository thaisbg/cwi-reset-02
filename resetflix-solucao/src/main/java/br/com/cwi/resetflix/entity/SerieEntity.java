package br.com.cwi.resetflix.entity;

import java.util.List;

import br.com.cwi.resetflix.domain.Genero;

public class SerieEntity {

    private Long id;
    private String nome;
    private Genero genero;
    private List<Long> idsAtores;
    private Integer temporadas;
    private Integer episodios;

    public SerieEntity(final Long id, final String nome, final Genero genero, final List<Long> idsAtores,
        final Integer temporadas,
        final Integer episodios) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.idsAtores = idsAtores;
        this.temporadas = temporadas;
        this.episodios = episodios;
    }

    public SerieEntity(final String nome, final Genero genero, final List<Long> idsAtores, final Integer temporadas,
        final Integer episodios) {
        this.nome = nome;
        this.genero = genero;
        this.idsAtores = idsAtores;
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

    public List<Long> getIdsAtores() {
        return idsAtores;
    }

    public void setIdsAtores(final List<Long> idsAtores) {
        this.idsAtores = idsAtores;
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
