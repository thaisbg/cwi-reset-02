package br.com.cwi.resetflix.response;

import java.util.List;

import br.com.cwi.resetflix.domain.Genero;
import br.com.cwi.resetflix.entity.DiretorEntity;

public class ConsultarDetalhesFilmeResponse {

    private Long id;
    private String nome;
    private Genero genero;
    private DiretorEntity diretorFilme;
    private List<AtoresResponse> atores;

    public ConsultarDetalhesFilmeResponse(Long id, String nome, Genero genero, DiretorEntity diretorFilme, List<AtoresResponse> atores) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.diretorFilme = diretorFilme;
        this.atores = atores;
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

    public DiretorEntity getDiretorFilme() {
        return diretorFilme;
    }

    public void setDiretorFilme(DiretorEntity diretorFilme) {
        this.diretorFilme = diretorFilme;
    }

    public List<AtoresResponse> getAtores() {
        return atores;
    }

    public void setAtores(List<AtoresResponse> atores) {
        this.atores = atores;
    }
}
