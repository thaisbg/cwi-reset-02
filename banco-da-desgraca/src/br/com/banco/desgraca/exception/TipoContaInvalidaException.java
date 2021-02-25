package br.com.banco.desgraca.exception;

public class TipoContaInvalidaException extends RuntimeException {

    public TipoContaInvalidaException (String mensagemDeErro) {
        super(mensagemDeErro);
    }

}
