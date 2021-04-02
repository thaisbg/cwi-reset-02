package com.company.domain;

import com.company.exceptions.EditoraException;

public class Editora {
    private String nome;
    private String localizacao;

    public Editora (String nome, String localizacao) {
        validaNome(nome);
        this.nome = nome;
        this.localizacao = localizacao;
    }

    private void validaNome(String nome) {
        if (nome.equals("DC Comics")) {
            throw new EditoraException("Você não pode usar esse nome!");
        }
    }

    public String getNome() {
        return nome;
    }
}


