package br.com.cwi.resetflix.response;

import java.util.List;

public class ConsultarDetalhesDiretorResponse {
    private Long id;
    private String nome;
    private List<FilmesResponse> filmes;

    public ConsultarDetalhesDiretorResponse(Long id, String nome, List<FilmesResponse> filmes) {
        this.id = id;
        this.nome = nome;
        this.filmes = filmes;
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

    public List<FilmesResponse> getFilmes() {
        return filmes;
    }

    public void setFilmes(List<FilmesResponse> filmes) {
        this.filmes = filmes;
    }
}
