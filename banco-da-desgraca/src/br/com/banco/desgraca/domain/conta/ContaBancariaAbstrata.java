package br.com.banco.desgraca.domain.conta;

import br.com.banco.desgraca.domain.InstituicaoBancaria;
import br.com.banco.desgraca.domain.TipoTransacao;
import br.com.banco.desgraca.domain.Transacao;
import br.com.banco.desgraca.exception.DatasInvalidasException;
import br.com.banco.desgraca.exception.SaldoInsuficienteException;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import static br.com.banco.desgraca.Data.getDataTransacao;

abstract class ContaBancariaAbstrata implements ContaBancaria {

    private InstituicaoBancaria instituicaoBancaria;
    private String numero;
    private Double saldo;

    ArrayList<Transacao> transacoes= new ArrayList();

    ContaBancariaAbstrata(InstituicaoBancaria instituicaoBancaria) {
        this.instituicaoBancaria = instituicaoBancaria;
        this.numero = gerarNumeroConta();
        this.saldo = 0.0;
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
        contaDestino.depositar(valor);
    }

    @Override
    public void exibirExtrato(LocalDate inicio, LocalDate fim) {
        System.out.println("\n-------------------------------------------");
        System.out.println("Extrato da conta " + toString());
        System.out.println("-------------------------------------------");

        if ((inicio != null) && (fim != null)) {
            if (fim.isBefore(inicio)) {
                throw new DatasInvalidasException("A data de início deve ser anterior à data de fim");
            } else {
                for (Transacao transacao : transacoes) {
                    if ((transacao.getData().isAfter(inicio)) && (transacao.getData().isBefore(fim))) {
                        transacao.exibirTransacoes();
                    }
                }
            }
        } else if (fim != null) {
            for (Transacao transacao : transacoes) {
                if (transacao.getData().isBefore(fim)) {
                    transacao.exibirTransacoes();
                }
            }
        } else if (inicio != null) {
            for (Transacao transacao : transacoes) {
                if (transacao.getData().isAfter(inicio)) {
                    transacao.exibirTransacoes();
                }
            }
        } else {
            for (Transacao transacao : transacoes) {
                transacao.exibirTransacoes();
            }
        }
        System.out.println("Saldo atual: " + DecimalFormat.getCurrencyInstance().format(saldo));
        System.out.println("-------------------------------------------\n");
    }

    // ------------------ MÉTODOS AUXILIARES ------------------

    protected String formatarValor(Double valor) {
        return DecimalFormat.getCurrencyInstance().format(valor);
    }

    protected void finalizarTransacao(Double valor, TipoTransacao tipo) {
        String preposicao = "da";
        if (tipo.equals(TipoTransacao.DEPOSITAR)) {
            preposicao = "na";
        }
        System.out.println(tipo.getDescricao() + " no valor de " + formatarValor(valor) + " " + preposicao + " conta " + toString());
        transacoes.add(new Transacao(tipo, valor));
    }

    protected void validarSaida(Double valor) {
        if (valor > saldo) {
            throw new SaldoInsuficienteException("Você não tem saldo suficiente para esta transação.");
        }
    }

    protected void imprimirTaxas(Double taxa, TipoTransacao tipo) {
        System.out.println("Taxa de " + tipo.getDescricao() + ": " + formatarValor(taxa));
    }

    @Override
    public String toString() {
        return instituicaoBancaria.getDescricao() + " " + numero;
    }

    private String gerarNumeroConta() {
        return String.format("%s-%s", new Random().nextInt(99999) + 1, new Random().nextInt(+9) + 1);
    }


    // ------------------ GETTERS & SETTERS ------------------

    @Override
    public InstituicaoBancaria getInstituicaoBancaria() {
        return instituicaoBancaria;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

}