import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Teste {

    public static void main (String[] args) {

        List<Artista> artistas = new ArrayList<Artista>();
        Diretor diretor1 = new Diretor("Thomas Vinterberg", LocalDate.of(1969, 05, 19), Genero.MASCULINO, 13);
        artistas.add(diretor1);
        artistas.add(new Ator("Thomas Bo Larsen", LocalDate.of(1963, 11, 27), Genero.MASCULINO, 4));
        artistas.add(new Ator("Mads Mikkelsen", LocalDate.of(1965, 11, 22), Genero.MASCULINO, 3));
        artistas.add(new Ator("Maria Bonnevie", LocalDate.of(1973, 9, 26), Genero.FEMININO, 5));
        Filme filme1 = new Filme("Druk", "Comédia dramática/Europeu", 120, 2020, 10, diretor1, artistas);

        filme1.exibirCreditos();

    }

}
