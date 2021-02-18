public abstract class Imovel {
    private Endereco endereco;
    private double valor;

    public Imovel(Endereco endereco, double valor) {
        this.endereco = endereco;
        this.valor = valor;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public double getValor() {
        return valor;
    }

    public String apresentacao() {
        return "Imóvel localizado no endereço " + endereco.getLogradouro() + " " + endereco.getNumero() + ", " +endereco.getCidade() + " - " + endereco.getEstado() + ". Valor: " + getValor() + ". ";
    }
}
