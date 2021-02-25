package br.com.banco.desgraca.domain.conta;

import br.com.banco.desgraca.domain.InstituicaoBancaria;
import br.com.banco.desgraca.exception.InstituicaoInvalidaException;
import br.com.banco.desgraca.exception.ValorSaqueInvalidoException;

public class ContaDigital extends ContaBancariaAbstrata {
    public ContaDigital(InstituicaoBancaria instituicaoBancaria, int numero) {
        super(instituicaoBancaria, numero);
        validarInstituicao();
    }

    @Override
    public void sacar(Double valor) {
        super.sacar(valor);
        if (valor < 10) {
            throw new ValorSaqueInvalidoException("Saque mínimo: R$10.00");
        }
    }

    private void validarInstituicao() {
        if ((!getInstituicaoBancaria().equals(InstituicaoBancaria.NUBANK)) && (!getInstituicaoBancaria().equals(InstituicaoBancaria.ITAU))){
            throw new InstituicaoInvalidaException("Este banco não permite criação de contas digitais.");
        }
    }
}
