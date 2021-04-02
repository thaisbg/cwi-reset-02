import java.time.LocalDate;

public class Diretor extends Artista {
    private int quantidadeFilmes;

    public Diretor(String nome, LocalDate nascimento, Genero genero, int quantidadeFilmes) {
        super(nome, nascimento, genero);
        this.quantidadeFilmes = quantidadeFilmes;
    }

    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("Quantidade de filmes dirigidos: " + quantidadeFilmes);
    }


}
