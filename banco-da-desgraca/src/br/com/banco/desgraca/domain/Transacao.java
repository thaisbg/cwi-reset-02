package br.com.banco.desgraca.domain;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static br.com.banco.desgraca.Data.getDataTransacao;

public class Transacao {
    private TipoTransacao tipoTransacao;
    private LocalDate data;
    private double valor;

    public Transacao(TipoTransacao tipoTransacao, LocalDate data, double valor) {
        this.tipoTransacao = tipoTransacao;
        this.data = data;
        this.valor = valor;
    }

    public void exibirTransacoes() {
        String valorFormatado = DecimalFormat.getCurrencyInstance().format(valor);
        char simboloEntradaSaida = '+';
        if (tipoTransacao.equals(TipoTransacao.TRANSFERIR) || tipoTransacao.equals(TipoTransacao.SACAR)) {
            simboloEntradaSaida = '-';
        }
        System.out.println(simboloEntradaSaida + " " + valorFormatado + " " + data.format(DateTimeFormatter.ofPattern("dd-MM-yy")));
    }

    public LocalDate getData() {
        return data;
    }
}
