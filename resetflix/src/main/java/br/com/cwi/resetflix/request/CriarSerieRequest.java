package br.com.cwi.resetflix.request;

import br.com.cwi.resetflix.domain.Genero;

import java.util.List;

public class CriarSerieRequest {

    private String nome;
    private Genero genero;
    private Integer quantidadeDeTemporadas;
    private Integer quantidadeDeEpisodios;
    private List<Long> idsAtores;

    public CriarSerieRequest(String nome, Genero genero, Integer quantidadeDeTemporadas, Integer quantidadeDeEpisodios, List<Long> idsAtores) {
        this.nome = nome;
        this.genero = genero;
        this.quantidadeDeTemporadas = quantidadeDeTemporadas;
        this.quantidadeDeEpisodios = quantidadeDeEpisodios;
        this.idsAtores = idsAtores;
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

    public Integer getQuantidadeDeTemporadas() {
        return quantidadeDeTemporadas;
    }

    public void setQuantidadeDeTemporadas(Integer quantidadeDeTemporadas) {
        this.quantidadeDeTemporadas = quantidadeDeTemporadas;
    }

    public Integer getQuantidadeDeEpisodios() {
        return quantidadeDeEpisodios;
    }

    public void setQuantidadeDeEpisodios(Integer quantidadeDeEpisodios) {
        this.quantidadeDeEpisodios = quantidadeDeEpisodios;
    }

    public List<Long> getIdsAtores() {
        return idsAtores;
    }

    public void setIdsAtores(List<Long> idsAtores) {
        this.idsAtores = idsAtores;
    }
}
