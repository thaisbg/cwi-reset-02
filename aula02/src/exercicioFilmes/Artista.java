import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;

public class Artista {
    private String nome;
    private Genero genero;
    private LocalDate nascimento;

    public Artista(String nome, LocalDate nascimento, Genero genero) {
        this.nome = nome;
        this.nascimento = nascimento;
        this.genero = genero;
    }

    private int idade (LocalDate nascimento) {
        Period idade = Period.between(nascimento, LocalDate.now());
        return idade.getYears();
    }

    public void exibirInformacoes() {
        System.out.println("Nome: " + this.nome);
        System.out.println("Idade: " + idade(nascimento));
        System.out.println("Gênero: " + this.genero.getDescricao());
    }

    public String getNome() {
        return nome;
    }

}
