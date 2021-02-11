package Exercicio03OO;

public class Aplicacao {

    public static void main(String[] args) {
        Aluno aluno = new Aluno("Iggy Pop", 4);
        System.out.println("Situação de aprovação: " + aluno.isAprovado());
    }
}
