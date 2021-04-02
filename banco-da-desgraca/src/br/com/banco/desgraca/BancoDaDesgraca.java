package br.com.banco.desgraca;

import br.com.banco.desgraca.domain.conta.ContaBancaria;
import br.com.banco.desgraca.domain.conta.ContaCorrente;
import br.com.banco.desgraca.domain.conta.ContaDigital;
import br.com.banco.desgraca.domain.conta.ContaPoupanca;
import br.com.banco.desgraca.exception.InstituicaoInvalidaException;
import br.com.banco.desgraca.exception.SaldoInsuficienteException;
import br.com.banco.desgraca.exception.ValorSaqueInvalidoException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.function.Supplier;

import static br.com.banco.desgraca.domain.InstituicaoBancaria.*;
import static java.text.DecimalFormat.getCurrencyInstance;
import static java.util.Arrays.asList;

public class BancoDaDesgraca {

    private static final Collection<ContaBancaria> CONTAS = new ArrayList();
    private static final Collection<ContaBancaria> CONTAS_CORRENTE = new ArrayList();
    private static final Collection<ContaBancaria> CONTAS_POUPANCA = new ArrayList();
    private static final Collection<ContaBancaria> CONTAS_DIGITAL = new ArrayList();

    public static void main(String[] args) throws Exception {

        System.out.println("\n > Criando contas bancárias...");

        ContaBancaria bbCc = new ContaCorrente(BANCO_DO_BRASIL);
        ContaBancaria bradescoCc = new ContaCorrente(BRADESCO);
        ContaBancaria caixaCc = new ContaCorrente(CAIXA);
        ContaBancaria itauCc = new ContaCorrente(ITAU);
        ContaBancaria nubankCc = new ContaCorrente(NUBANK);
        CONTAS_CORRENTE.addAll(asList(bbCc, bradescoCc, caixaCc, itauCc, nubankCc));

        ContaBancaria bbPoupanca = new ContaPoupanca(BANCO_DO_BRASIL);
        ContaBancaria bradescoPoupanca = new ContaPoupanca(BRADESCO);
        ContaBancaria caixaPoupanca = new ContaPoupanca(CAIXA);
        ContaBancaria itauPoupanca = new ContaPoupanca(ITAU);
        CONTAS_POUPANCA.addAll(asList(bbPoupanca, bradescoPoupanca, caixaPoupanca, itauPoupanca));

        ContaBancaria itauDigital = new ContaDigital(ITAU);
        ContaBancaria nubankDigital = new ContaDigital(NUBANK);
        CONTAS_DIGITAL.addAll(asList(itauDigital, nubankDigital));

        CONTAS.addAll(CONTAS_CORRENTE);
        CONTAS.addAll(CONTAS_POUPANCA);
        CONTAS.addAll(CONTAS_DIGITAL);


        ///


        System.out.println("\n > Verificando contas que não podem ser criadas...");
        verificarContasQueNaoPodemSerCriadas();


        ///


        System.out.println("\n > Depositando R$ 1000,00 em cada conta...");
        CONTAS.forEach(conta -> conta.depositar(1000.0));
        // > cc = 1000
        // > digital = 1000
        // > poupanca = 1000


        ///


        System.out.println("\n > Sacando R$ 100,00 de cada conta...");
        CONTAS.forEach(conta -> conta.sacar(100.0));
        // > cc = 900
        // > digital = 900
        // > poupanca = 898


        ///


        System.out.println("\n > Verificando saques com valores menores do que o permitido...");
        verificarSaqueInvalidosMenores();


        ///


        System.out.println("\n > Transferindo R$ 10,00 de cada conta digital para cada conta corrente...");
        CONTAS_DIGITAL.forEach(digital ->
                CONTAS_CORRENTE.forEach(cc ->
                        digital.transferir(10.0, cc)));
        // > cc = 920
        // > digital = 850
        // > poupanca = 898


        ///


        System.out.println("\n > Transferindo R$ 10,00 de cada conta corrente para cada conta poupança...");
        CONTAS_CORRENTE.forEach(cc ->
                CONTAS_POUPANCA.forEach(poupanca ->
                        cc.transferir(10.0, poupanca)));
        // > cc (nubank) = 879.6
        // > cc (outros) = 879.7
        // > digital = 850
        // > poupanca = 948


        ///


        System.out.println("\n > Transferindo R$ 10,00 de cada conta poupança para cada conta digital...");
        CONTAS_POUPANCA.forEach(poupanca ->
                CONTAS_DIGITAL.forEach(digital ->
                        poupanca.transferir(10.0, digital)));
        // > cc (nubank) = 879.6
        // > cc (outros) = 879.7
        // > digital = 890
        // > poupanca (itaú) = 927.85
        // > poupanca (outros) = 927.8


        ///


        System.out.println("\n > Tentando sacar valores maiores do que o saldo...");
        verificarSaquesMaioresDoQueSaldo();


        ///


        System.out.println("\n > Verificando extrato da Conta Digital Nubank no período de 01/01/2021 a 31/01/2021 (devem aparecer 3 registros)...");
        nubankDigital.exibirExtrato(LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 31));


        ///


        System.out.println("\n > Verificando o saldo das contas...");
        verificarSaldo(bbCc, 879.70);
        verificarSaldo(bradescoCc, 879.70);
        verificarSaldo(caixaCc, 879.70);
        verificarSaldo(itauCc, 879.70);
        verificarSaldo(nubankCc, 879.60);
        verificarSaldo(bbPoupanca, 927.80);
        verificarSaldo(bradescoPoupanca, 927.80);
        verificarSaldo(caixaPoupanca, 927.80);
        verificarSaldo(itauPoupanca, 927.85);
        verificarSaldo(itauDigital, 890.00);
        verificarSaldo(nubankDigital, 890.00);

        System.out.println("\n\n\nPROGRAMA VALIDADO COM SUCESSO! PARABÉNS! :D\n\n");
    }

    private static void verificarSaldo(ContaBancaria conta, Double saldoEsperado) {

        BigDecimal saldoConta = new BigDecimal(conta.consultarSaldo()).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal saldoReal = new BigDecimal(saldoEsperado).setScale(2, RoundingMode.HALF_EVEN);

        if (saldoConta.compareTo(saldoReal) != 0) {
            throw new RuntimeException("Atenção, saldo incorreto! O saldo da conta " + conta + " deveria ser " + getCurrencyInstance().format(saldoEsperado) +
                    ", mas atualmente é " + getCurrencyInstance().format(conta.consultarSaldo()));
        }
    }

    private static void verificarContasQueNaoPodemSerCriadas() {

        contaInvalida(() -> new ContaDigital(BANCO_DO_BRASIL));
        contaInvalida(() -> new ContaDigital(BRADESCO));
        contaInvalida(() -> new ContaDigital(CAIXA));

        contaInvalida(() -> new ContaPoupanca(NUBANK));
    }

    private static void contaInvalida(Supplier<ContaBancaria> conta) {

        try {
            ContaBancaria cb = conta.get();
            throw new RuntimeException("Atenção! Não deveria ser possível criar a " + cb);
        } catch (InstituicaoInvalidaException ibie) {
        }
    }

    private static void verificarSaqueInvalidosMenores() {

        saqueInvalido(CONTAS_DIGITAL.iterator().next(), 9.99);

        double valorCc = 0.0;
        while (valorCc < 1 || valorCc % 5 == 0) {
            valorCc = (new Random().nextDouble() * 200) + 1;
        }
        saqueInvalido(CONTAS_CORRENTE.iterator().next(), valorCc);

        saqueInvalido(CONTAS_POUPANCA.iterator().next(), new Random().nextDouble() * 50);
    }

    private static void saqueInvalido(ContaBancaria conta, Double valor) {

        try {
            conta.sacar(valor);
            throw new RuntimeException("Atenção! Não deveria ser possível sacar " + getCurrencyInstance().format(valor) + " de " + conta);
        } catch (ValorSaqueInvalidoException vsie) {
            System.out.println("  X Saque cancelado: " + vsie.getMessage());
        }
    }

    private static void verificarSaquesMaioresDoQueSaldo() {

        CONTAS_CORRENTE.forEach(conta -> saldoInvalido(conta, 880.0));

        CONTAS_DIGITAL.forEach(conta -> saldoInvalido(conta, 890.01));

        CONTAS_POUPANCA.forEach(conta -> saldoInvalido(conta, conta.getInstituicaoBancaria() == ITAU ? 927.86 : 927.81));
    }

    private static void saldoInvalido(ContaBancaria conta, Double valor) {

        try {
            conta.sacar(valor);
            throw new RuntimeException("Atenção! Não deveria ser possível sacar " + getCurrencyInstance().format(valor) + " de " + conta);
        } catch (SaldoInsuficienteException sie) {
            System.out.println("  X Saque cancelado: " + sie.getMessage());
        }
    }
}

//package br.com.banco.desgraca;
//
//import br.com.banco.desgraca.domain.InstituicaoBancaria;
//import br.com.banco.desgraca.domain.conta.ContaCorrente;
//import br.com.banco.desgraca.domain.conta.ContaDigital;
//import br.com.banco.desgraca.domain.conta.ContaPoupanca;
//
//import java.time.LocalDate;
//
//public class BancoDaDesgraca {
//
//    public static void main(String[] args) throws Exception {
//
//        ContaCorrente contaTeste = new ContaCorrente(InstituicaoBancaria.BANCO_DO_BRASIL, 5466);
//        ContaCorrente contaTeste2 = new ContaCorrente(InstituicaoBancaria.ITAU, 111);
//        ContaCorrente contaTeste3 = new ContaCorrente(InstituicaoBancaria.BRADESCO, 433);
//
//        ContaDigital testeDigital = new ContaDigital(InstituicaoBancaria.NUBANK, 300);
//        testeDigital.depositar(200.00);
//        testeDigital.transferir(80.0, contaTeste);
//        //testeDigital.sacar(6.0);
//        testeDigital.exibirExtrato(null, null);
//
//        ContaCorrente testeCorrente = new ContaCorrente(InstituicaoBancaria.ITAU, 506);
//        testeCorrente.depositar(600.0);
//        testeCorrente.transferir(40.0, contaTeste); // teste transferência para outro banco
//        testeCorrente.transferir(50.0, contaTeste2); // teste transferência para mesmo banco
//        //testeCorrente.sacar(4.0);
//        testeCorrente.exibirExtrato(null, null);
//
//        ContaPoupanca testePoupanca = new ContaPoupanca(InstituicaoBancaria.BRADESCO, 432);
//        testePoupanca.depositar(900.0);
//        testePoupanca.sacar(50.0);
//        testePoupanca.transferir(60.0, contaTeste); // teste transferência para outro banco
//        testePoupanca.transferir(70.0, contaTeste3); // teste transferência para mesmo banco
//        testePoupanca.exibirExtrato(null,null);
//        testePoupanca.exibirExtrato(null, LocalDate.of(2020,8,14));
//        testePoupanca.exibirExtrato(LocalDate.of(2020, 8, 5), null);
//        testePoupanca.exibirExtrato(LocalDate.of(2020, 8, 5), LocalDate.of(2020, 8, 15));
////        testePoupanca.exibirExtrato(LocalDate.of(2020, 10, 5), LocalDate.of(2020,9,4));
//
//        contaTeste.exibirExtrato(null,null);
//        contaTeste3.exibirExtrato(null,null);
//
////        ContaPoupanca testeExceptionConta = new ContaPoupanca(InstituicaoBancaria.NUBANK, 545);
////        testeExceptionConta.depositar(45.00);
//
////        ContaDigital testeExceptionDigital = new ContaDigital(InstituicaoBancaria.BRADESCO, 434);
////        testeExceptionDigital.depositar(455.0);
//
//    }
//}