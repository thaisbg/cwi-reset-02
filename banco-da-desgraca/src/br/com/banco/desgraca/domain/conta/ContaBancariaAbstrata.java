package br.com.banco.desgraca.domain.conta;

import br.com.banco.desgraca.domain.InstituicaoBancaria;
import br.com.banco.desgraca.domain.TipoTransacao;
import br.com.banco.desgraca.domain.Transacao;
import br.com.banco.desgraca.exception.SaldoInsuficienteException;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import static br.com.banco.desgraca.Data.getDataTransacao;

public abstract class ContaBancariaAbstrata implements ContaBancaria {
    private InstituicaoBancaria instituicaoBancaria;
    private int numero;
    private double saldo;

    ArrayList<Transacao> transacoes= new ArrayList();

    public ContaBancariaAbstrata(InstituicaoBancaria instituicaoBancaria, int numero) {
        this.instituicaoBancaria = instituicaoBancaria;
        this.numero = numero;
    }

    // ------------------ MÉTODOS DE TRANSAÇÕES ------------------

    @Override
    public Double consultarSaldo() {
        return saldo;
    }

    @Override
    public void depositar(Double valor) {
        saldo += valor;
        finalizarTransacao(valor, TipoTransacao.DEPOSITAR);
    }

    @Override
    public void sacar(Double valor) {
        validarSaida(valor);
        saldo -= valor;
        finalizarTransacao(valor, TipoTransacao.SACAR);
    }

    @Override
    public void transferir(Double valor, ContaBancaria contaDestino) {
        validarSaida(valor);
        saldo -= valor;
        finalizarTransacao(valor, TipoTransacao.TRANSFERIR);
    }

    @Override
    public void exibirExtrato(LocalDate inicio, LocalDate fim) {
        System.out.println("\n-------------------------------------------");
        System.out.println("Extrato da conta " + toString());
        System.out.println("-------------------------------------------");
        for (Transacao transacao : transacoes)
            transacao.exibirTransacoes();
        System.out.println("Saldo: " + DecimalFormat.getCurrencyInstance().format(saldo));
        System.out.println("-------------------------------------------\n");
    }

    // ------------------ MÉTODOS AUXILIARES ------------------

    protected String formatarValor(double valor) {
        return DecimalFormat.getCurrencyInstance().format(valor);
    }

    protected void finalizarTransacao(double valor, TipoTransacao tipo) {
        String preposicao = "da";
        if (tipo.equals(TipoTransacao.DEPOSITAR)) {
            preposicao = "na";
        }
        System.out.println(tipo.getDescricao() + " no valor de " + formatarValor(valor) + " " + preposicao + " conta " + toString());
        transacoes.add(new Transacao(tipo, getDataTransacao(), valor));
    }

    protected void validarSaida(double valor) {
        if (valor > getSaldo()) {
            throw new SaldoInsuficienteException("Você não tem saldo suficiente para esta transação.");
        }
    }

    protected void imprimirTaxas(double taxa, TipoTransacao tipo) {
        System.out.println("Taxa de " + tipo.getDescricao() + ": " + formatarValor(taxa));
    }

    @Override
    public String toString() {
        return instituicaoBancaria.getDescricao() + " " + numero;
    }


    // ------------------ GETTERS & SETTERS ------------------

    @Override
    public InstituicaoBancaria getInstituicaoBancaria() {
        return instituicaoBancaria;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

}