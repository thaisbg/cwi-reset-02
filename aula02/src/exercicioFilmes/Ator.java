package exercicioFilmes;

public class Ator extends Artista {
    private int numeroOscars;

    public Ator(String nome, int idade, Genero genero, int numeroOscars) {
        super(nome, idade, genero);
        this.numeroOscars = numeroOscars;
    }

}
