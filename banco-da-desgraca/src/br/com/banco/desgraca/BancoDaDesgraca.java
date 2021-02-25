package br.com.banco.desgraca;

import br.com.banco.desgraca.domain.InstituicaoBancaria;
import br.com.banco.desgraca.domain.conta.ContaCorrente;
import br.com.banco.desgraca.domain.conta.ContaDigital;
import br.com.banco.desgraca.domain.conta.ContaPoupanca;

public class BancoDaDesgraca {

    public static void main(String[] args) throws Exception {

        ContaCorrente contaTeste = new ContaCorrente(InstituicaoBancaria.BANCO_DO_BRASIL, 5466);
        ContaCorrente contaTeste2 = new ContaCorrente(InstituicaoBancaria.ITAU, 111);
        ContaCorrente contaTeste3 = new ContaCorrente(InstituicaoBancaria.BRADESCO, 433);

        ContaDigital testeDigital = new ContaDigital(InstituicaoBancaria.NUBANK, 300);
        testeDigital.depositar(200.00);
        testeDigital.transferir(80.0, contaTeste);
        //testeDigital.sacar(6.0);
        testeDigital.exibirExtrato(null, null);

        ContaCorrente testeCorrente = new ContaCorrente(InstituicaoBancaria.ITAU, 506);
        testeCorrente.depositar(600.0);
        testeCorrente.transferir(40.0, contaTeste); // teste transferência para outro banco
        testeCorrente.transferir(50.0, contaTeste2); // teste transferência para mesmo banco
        //testeCorrente.sacar(4.0);
        testeCorrente.exibirExtrato(null, null);

        ContaPoupanca testePoupanca = new ContaPoupanca(InstituicaoBancaria.BRADESCO, 432);
        testePoupanca.depositar(900.0);
        testePoupanca.sacar(90.0);
        testePoupanca.transferir(50.0, contaTeste); // teste transferência para outro banco
        testePoupanca.transferir(90.0, contaTeste3); // teste transferência para mesmo banco
        testePoupanca.exibirExtrato(null, null);

//        ContaPoupanca testeExceptionConta = new ContaPoupanca(InstituicaoBancaria.NUBANK, 545);
//        testeExceptionConta.depositar(45.00);

//        ContaDigital testeExceptionDigital = new ContaDigital(InstituicaoBancaria.BRADESCO, 434);
//        testeExceptionDigital.depositar(455.0);

    }
}