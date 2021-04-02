import java.time.LocalDate;
import java.util.ArrayList;

public class Aplicacao {
    public static void main (String[] args) {

        Ator ator1 = new Ator("Thomas Bo Larsen", LocalDate.of(1963, 11, 27), Genero.MASCULINO, 4);
        Ator ator2 = new Ator("Mads Mikkelsen", LocalDate.of(1963, 11, 27), Genero.MASCULINO, 3);
        Ator ator3 = new Ator("Maria Bonnevie", LocalDate.of(1963, 11, 27), Genero.FEMININO, 5);
        Diretor diretorFilme1 = new Diretor("Thomas Vinterberg", LocalDate.of(1963, 11, 27), Genero.MASCULINO, 13);
        ArrayList<Ator> elenco1= new ArrayList();
        elenco1.add(ator1);
        elenco1.add(ator2);
        elenco1.add(ator3);


        Filme filme1 = new Filme("Druk", "Comédia dramática/Europeu", 120, 2020, 10, diretorFilme1, elenco1);

        filme1.reproduzir();
        System.out.println("Avaliação: " + filme1.getAvaliacao());
        filme1.exibirCreditos();
        //ator1.exibirInformacoes();
        //diretorFilme1.exibirInformacoes();
    }
}

