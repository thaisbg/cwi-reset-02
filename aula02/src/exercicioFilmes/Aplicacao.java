package exercicioFilmes;

public class Aplicacao {
    public static void main (String[] args) {
        /*Diretor diretorFilme1 = new Diretor("Alfred Hitchcock", 80, Genero.MASCULINO, 60);
        Ator ator1 = new Ator("Kim Novak", 88, Genero.FEMININO, 4);
        Filme filme1 = new Filme("O Corpo que cai", "Drama/Clássico", 120, 1958, 5, diretorFilme1);

        filme1.reproduzir();
        ator1.exibirInformacoes();
        diretorFilme1.exibirInformacoes();
        */

        Diretor diretorFilme2 = new Diretor("Thomas Vinterberg", 51, Genero.MASCULINO, 13);
        Ator ator2 = new Ator("Mads Mikkelsen", 55, Genero.MASCULINO, 3);
        Filme filme2 = new Filme("O Clube da Luta", "Comédia dramática/Europeu", 120, 2020, 10, diretorFilme2);

        filme2.reproduzir();
        System.out.println("Avaliação: " + filme2.getAvaliacao());
        ator2.exibirInformacoes();
        diretorFilme2.exibirInformacoes();
    }
}

