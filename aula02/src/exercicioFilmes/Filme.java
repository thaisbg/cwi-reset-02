package exercicioFilmes;

public class Filme {
    private String nome;
    private String descricao;
    private int duracao;
    private int anoLancamento;
    private int avaliacao;
    private Diretor diretor;
    private ArrayList elenco;

    public Filme(String nome, String descricao, int duracao, int anoLancamento, int avaliacao, Diretor diretor, ArrayList elenco) {
        this.nome = nome;
        this.descricao = descricao;
        this.duracao = duracao;
        this.anoLancamento = anoLancamento;
        this.diretor = diretor;
        this.elenco = elenco;

        validarAvaliacao(avaliacao);
        validarNomeAvaliacao(nome);
    }

    public void reproduzir() {
        System.out.println("Título: " + this.nome);
        System.out.println("Descrição: " + this.descricao);
        System.out.println("Duração: " + this.duracao + " minutos");
        System.out.println("Diretor: " + this.diretor.getNome());
    }

    public void validarAvaliacao(int avaliacao) {
        if ((avaliacao < 1) || (avaliacao > 5)) {
            this.avaliacao = 3;
        } else {
            this.avaliacao = avaliacao;
        }
    }

    private void validarNomeAvaliacao(String nome) {
        if (nome.equals("Batman vs Superman")) {
            this.avaliacao = 1;
        } else if (nome.equals("O Clube da Luta")) {
            this.avaliacao = 5;
        }
    }

    public void exibirCreditos() {
        System.out.println(ator1)
    }

    public int getAvaliacao() {
        return avaliacao;
    }
}



