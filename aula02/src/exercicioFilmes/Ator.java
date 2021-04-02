import java.time.LocalDate;

public class Ator extends Artista {
    private int numeroOscars;

    public Ator(String nome, LocalDate nascimento, Genero genero, int numeroOscars) {
        super(nome, nascimento, genero);
        this.numeroOscars = numeroOscars;
    }

    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("Quantidade de Oscars: " + numeroOscars);
    }

}
