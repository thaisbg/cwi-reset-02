package br.com.banco.desgraca.domain.conta;

import br.com.banco.desgraca.domain.InstituicaoBancaria;
import br.com.banco.desgraca.domain.TipoTransacao;
import br.com.banco.desgraca.exception.ValorSaqueInvalidoException;

public class ContaCorrente extends ContaBancariaAbstrata {

    public ContaCorrente(InstituicaoBancaria instituicaoBancaria, int numero) {
        super(instituicaoBancaria, numero);
    }

    @Override
    public void sacar(Double valor) {
        super.sacar(valor);
        if (valor % 5 != 0) {
            throw new ValorSaqueInvalidoException("Cédulas disponíveis: 5, 10, 20, 100 e 200. Escolha um valor que possa ser sacado com as cédulas mencionadas.");
        }
    }

    @Override
    public void transferir(Double valor, ContaBancaria contaDestino) {
        validarSaida(valor);
        double taxa = 0;
        if (!contaDestino.getInstituicaoBancaria().equals(this.getInstituicaoBancaria())) {
            taxa = valor * 0.01;
        }
        setSaldo(super.getSaldo() - valor - taxa);
        finalizarTransacao(valor, TipoTransacao.TRANSFERIR);
        imprimirTaxas(taxa, TipoTransacao.TRANSFERIR);
        contaDestino.depositar(valor);
    }
}