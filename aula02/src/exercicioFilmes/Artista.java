package exercicioFilmes;

public class Artista {
    private String nome;
    private int idade;
    private Genero genero;

    public Artista(String nome, int idade, Genero genero) {
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
    }

    public void exibirInformacoes() {
        System.out.println("Nome: " + this.nome);
        System.out.println("Idade: " + this.idade);
        System.out.println("Gênero: " + this.genero.getDescricao());
    }

    public String getNome() {
        return nome;
    }

}
