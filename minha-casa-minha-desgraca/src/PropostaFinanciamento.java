public class PropostaFinanciamento {

    private Beneficiario beneficiario;
    private Imovel imovelEscolhido;
    private int mesesParaPagamento;

    public PropostaFinanciamento(Beneficiario beneficiario, Imovel imovelEscolhido, int mesesParaPagamento) {
        this.beneficiario = beneficiario;
        this.imovelEscolhido = imovelEscolhido;
        this.mesesParaPagamento = mesesParaPagamento;
    }

    /**
     * Valida a proposta de financiamento e apresenta o resultado para o cliente.
     *
     * A proposta de financiamento é aceita somente se o salário do beneficiário multiplicado pelo prazo de pagamento
     *   for equivalente a pelo menos 50% do valor do imóvel.
     *
     *   Esta regra possui duas exceções: se o imóvel se localiza no estado SP ou RJ,
     *      o salário multiplicado pelo prazo deve ser equivalente a 65% e 60% do valor imóvel (respectivamente).
     */

    public void validarProposta() {

        double porcentagemDoValorImovel = 0;
        if (imovelEscolhido.getEndereco().getEstado() == UnidadeFederativa.SP) {
            porcentagemDoValorImovel = 0.65;
        } else if (imovelEscolhido.getEndereco().getEstado() == UnidadeFederativa.RJ) {
            porcentagemDoValorImovel = 0.6;
        } else {
            porcentagemDoValorImovel = 0.5;
        }

        boolean criterio = (beneficiario.getSalario() * mesesParaPagamento) >= (imovelEscolhido.getValor() * porcentagemDoValorImovel);

        if (criterio) {
            imprimirPropostaAprovada();
        }
        else {
            imprimirPropostaNegada();
        }

    }

    public void dadosProposta() {
        System.out.println("Beneficiário: " + beneficiario.getNome());
        System.out.println(imovelEscolhido.apresentacao());
        System.out.println("Prazo: " + mesesParaPagamento + " meses");
    }

    public void imprimirPropostaAprovada() {
        dadosProposta();
        System.out.println("Parabéns, sua proposta foi aceita!");
    }

    public void imprimirPropostaNegada() {
        dadosProposta();
        System.out.println("Proposta negada!");
    }

}