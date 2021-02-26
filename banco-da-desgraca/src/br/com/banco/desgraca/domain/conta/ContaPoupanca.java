package br.com.banco.desgraca.domain.conta;

import br.com.banco.desgraca.domain.InstituicaoBancaria;
import br.com.banco.desgraca.domain.TipoTransacao;
import br.com.banco.desgraca.exception.InstituicaoInvalidaException;
import br.com.banco.desgraca.exception.ValorSaqueInvalidoException;

public class ContaPoupanca extends ContaBancariaAbstrata{
    public ContaPoupanca(InstituicaoBancaria instituicaoBancaria, int numero) {
        super(instituicaoBancaria, numero);
        validarInstituicao();
    }

    @Override
    public void sacar(Double valor) {
        validarSaida(valor);
        if (valor < 50) {
            throw new ValorSaqueInvalidoException("Saque mínimo: R$50.00");
        } else {
            double taxa = valor * 0.02;
            setSaldo(super.getSaldo() - valor - taxa);
            finalizarTransacao(valor, TipoTransacao.SACAR);
            imprimirTaxas(taxa, TipoTransacao.SACAR);
        }
    }

    @Override
    public void transferir(Double valor, ContaBancaria contaDestino) {
        validarSaida(valor);
        double taxa = valor * 0.01;
        if (contaDestino.getInstituicaoBancaria().equals(this.getInstituicaoBancaria())) {
            taxa = valor * 0.005;
        }
        setSaldo(super.getSaldo() - valor - taxa);
        finalizarTransacao(valor,TipoTransacao.TRANSFERIR);
        imprimirTaxas(taxa, TipoTransacao.TRANSFERIR);
        contaDestino.depositar(valor);
    }

    private void validarInstituicao() {
        if (super.getInstituicaoBancaria().equals(InstituicaoBancaria.NUBANK)) {
            throw new InstituicaoInvalidaException("Este banco não permite criação de conta poupança.");
        }
    }
}
