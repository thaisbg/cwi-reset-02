package Exercicio03OO;

public class Aluno {

    private String nomeAluno;
    private float nota;
    private boolean aprovado;

    public Aluno (String nomeAluno, float nota) {
        this.nomeAluno = nomeAluno;
        this.nota = nota;
        System.out.println("Nome do aluno: " + nomeAluno);
        System.out.println("Nota do aluno: " + nota);
    }

    boolean isAprovado() {
        if (nota >= 7) {
            return true;
        } else {
            return false;
        }
    }

}
