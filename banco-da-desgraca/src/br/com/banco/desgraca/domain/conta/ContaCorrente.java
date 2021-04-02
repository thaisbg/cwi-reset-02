package br.com.banco.desgraca.domain.conta;

import br.com.banco.desgraca.domain.InstituicaoBancaria;
import br.com.banco.desgraca.domain.TipoTransacao;
import br.com.banco.desgraca.exception.ValorSaqueInvalidoException;

public class ContaCorrente extends ContaBancariaAbstrata {

    public ContaCorrente(InstituicaoBancaria instituicaoBancaria) {
        super(instituicaoBancaria);
    }

    @Override
    public void sacar(Double valor) {
        if (valor % 5 != 0) {
            throw new ValorSaqueInvalidoException("Cédulas disponíveis: 5, 10, 20, 100 e 200. Escolha um valor que possa ser sacado com as cédulas mencionadas.");
        }
        super.sacar(valor);
    }

    @Override
    public void transferir(Double valor, ContaBancaria contaDestino) {
        validarSaida(valor);
        Double taxa = 0.0;
        if (!contaDestino.getInstituicaoBancaria().equals(this.getInstituicaoBancaria())) {
            taxa = valor * 0.01;
        }
        finalizarTransacao(valor+taxa, TipoTransacao.TRANSFERIR);
        setSaldo(this.getSaldo() - valor - taxa);
        imprimirTaxas(taxa, TipoTransacao.TRANSFERIR);
        contaDestino.depositar(valor);
    }

}