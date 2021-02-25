package br.com.banco.desgraca.exception;

public class ValorSaqueInvalidoException extends RuntimeException {

    public ValorSaqueInvalidoException (String mensagemDeErro) {
        super(mensagemDeErro);
    }
}
