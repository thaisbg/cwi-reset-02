package Exercicio01OO;

public class Aplicacao {

    double x = 14;
    double y = 2;

    public static void main(String[] args) {
        Calculadora calculadora = new Calculadora();
        double resultadoSoma = calculadora.soma(6.7, 43.6);
        System.out.println("Resultado da soma: " + resultadoSoma);

        double resultadoSubtrai = calculadora.subtrai(6.7, 43.6);
        System.out.println("Resultado da subtração: " + resultadoSubtrai);

        double resultadoMultiplica = calculadora.multiplica(6.7, 43.6);
        System.out.println("Resultado da multiplicação: " + resultadoMultiplica);

        double resultadoDivide = calculadora.divide(6.7, 43.6);
        System.out.println("Resultado da divisão: " + resultadoDivide);
    }
}
