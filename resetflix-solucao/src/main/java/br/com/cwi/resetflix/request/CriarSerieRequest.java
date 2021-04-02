package br.com.cwi.resetflix.request;

import java.util.List;

import br.com.cwi.resetflix.domain.Genero;

public class CriarSerieRequest {

    private String nome;
    private List<Long> idsAtores;
    private Genero genero;
    private Integer temporadas;
    private Integer episodios;

    public CriarSerieRequest(final String nome, final List<Long> idsAtores, final Genero genero,
        final Integer temporadas,
        final Integer episodios) {
        this.nome = nome;
        this.idsAtores = idsAtores;
        this.genero = genero;
        this.temporadas = temporadas;
        this.episodios = episodios;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public List<Long> getIdsAtores() {
        return idsAtores;
    }

    public void setIdsAtores(final List<Long> idsAtores) {
        this.idsAtores = idsAtores;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(final Genero genero) {
        this.genero = genero;
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
