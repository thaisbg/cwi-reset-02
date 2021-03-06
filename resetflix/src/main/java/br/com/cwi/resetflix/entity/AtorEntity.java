package br.com.cwi.resetflix.entity;

import java.util.Collection;
import java.util.List;

public class AtorEntity {

    private Long id;
    private String nome;
    private List<Long> idsFilmes;
    private List<Long> idsSeries;

    public AtorEntity(Long id, String nome, List<Long> idsFilmes, List<Long> idsSeries) {
        this.id = id;
        this.nome = nome;
        this.idsFilmes = idsFilmes;
        this.idsSeries = idsSeries;
    }

    public AtorEntity(String nome, List<Long> idsFilmes) {
        this.nome = nome;
        this.idsFilmes = idsFilmes;
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

    public List<Long> getIdsFilmes() {
        return idsFilmes;
    }

    public void setIdsFilmes(List<Long> idsFilmes) {
        this.idsFilmes = idsFilmes;
    }

    public List<Long> getIdsSeries() {
        return idsSeries;
    }

    public void setIdsSeries(List<Long> idsSeries) {
        this.idsSeries = idsSeries;
    }
}
