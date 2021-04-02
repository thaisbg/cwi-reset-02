package br.com.banco.desgraca.domain.conta;

import br.com.banco.desgraca.domain.InstituicaoBancaria;
import br.com.banco.desgraca.domain.TipoTransacao;
import br.com.banco.desgraca.exception.InstituicaoInvalidaException;
import br.com.banco.desgraca.exception.ValorSaqueInvalidoException;

public class ContaPoupanca extends ContaBancariaAbstrata{
    public ContaPoupanca(InstituicaoBancaria instituicaoBancaria) {
        super(instituicaoBancaria);
        validarInstituicao();
    }

    @Override
    public void sacar(Double valor) {
        validarSaida(valor);
        if (valor < 50) {
            throw new ValorSaqueInvalidoException("Saque mínimo: R$50.00");
        } else {
            Double taxa = valor * 0.02;
            finalizarTransacao(valor+taxa, TipoTransacao.SACAR);
            setSaldo(this.getSaldo() - valor - taxa);
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
        finalizarTransacao(valor+taxa,TipoTransacao.TRANSFERIR);
        setSaldo(this.getSaldo() - valor - taxa);
        imprimirTaxas(taxa, TipoTransacao.TRANSFERIR);
        contaDestino.depositar(valor);
    }


    private void validarInstituicao() {
        if (super.getInstituicaoBancaria().equals(InstituicaoBancaria.NUBANK)) {
            throw new InstituicaoInvalidaException("Este banco não permite criação de conta poupança.");
        }
    }

}
