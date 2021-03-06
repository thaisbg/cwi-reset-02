package br.com.cwi.resetflix.response;

import java.util.List;

import br.com.cwi.resetflix.domain.Genero;

public class ConsultarDetalhesSerieResponse {

    private Long id;
    private String nome;
    private Genero genero;
    private Integer quantidadeDeTemporadas;
    private Integer quantidadeDeEpisodios;
    private List<AtoresResponse> idsAtores;

    public ConsultarDetalhesSerieResponse(Long id, String nome, Genero genero, Integer quantidadeDeTemporadas, Integer quantidadeDeEpisodios, List<AtoresResponse> idsAtores) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.quantidadeDeTemporadas = quantidadeDeTemporadas;
        this.quantidadeDeEpisodios = quantidadeDeEpisodios;
        this.idsAtores = idsAtores;
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

    public List<AtoresResponse> getIdsAtores() {
        return idsAtores;
    }

    public void setIdsAtores(List<AtoresResponse> idsAtores) {
        this.idsAtores = idsAtores;
    }
}
