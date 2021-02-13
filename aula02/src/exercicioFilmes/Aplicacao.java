package exercicioFilmes;

public class Aplicacao {
    public static void main (String[] args) {
        Diretor diretorFilme1 = new Diretor("Alfred Hitchcock", 80, 200);
        Filme filme1 = new Filme("O Corpo que cai", "O detetive aposentado John Scottie sofre de um terrível medo de alturas.", 120, 1958, 5, diretorFilme1);
        filme1.reproduzir();

        Diretor diretorFilme2 = new Diretor("Thomas Vinterberg", 51, 30);
        Filme filme2 = new Filme("Druk", "Para alegrar um amigo em crise, um grupo de professores decide testar a ousada teoria de que serão mais felizes e bem-sucedidos vivendo com um pouco de álcool no sangue.", 120, 2020, 5, diretorFilme2);
        filme2.reproduzir();
    }
}

