package exercicioFilmes;

public class Diretor extends Artista {
    private int quantidadeFilmes;

    public Diretor(String nome, int idade, Genero genero, int quantidadeFilmes) {
        super(nome, idade, genero);
        this.quantidadeFilmes = quantidadeFilmes;
    }

}
