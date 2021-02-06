package Exercicio01OO;

public class Aplicacao {

    public static void main(String[] args) {
        Calculadora calculadora = new Calculadora();
        int resultadoSoma = calculadora.soma(5, 8);
        System.out.println("Resultado da soma: " + resultadoSoma);

        int resultadoSubtrai = calculadora.subtrai(12, 6);
        System.out.println("Resultado da subtração: " + resultadoSubtrai);

        int resultadoMultiplica = calculadora.multiplica(5, 3);
        System.out.println("Resultado da multiplicação: " + resultadoMultiplica);

        int resultadoDivide = calculadora.divide(14, 2);
        System.out.println("Resultado da divisão: " + resultadoDivide);
    }
}
