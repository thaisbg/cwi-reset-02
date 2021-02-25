package br.com.banco.desgraca.domain;

public enum InstituicaoBancaria {
    BANCO_DO_BRASIL("Banco do Brasil"),
    BRADESCO("Bradesco"),
    CAIXA("Caixa Econômica Federal"),
    ITAU("Itaú"),
    NUBANK("Nubank");

    private String descricao;

    InstituicaoBancaria(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}