package br.com.banco.desgraca.exception;

public class DatasInvalidasException extends RuntimeException {

    public DatasInvalidasException (String mensagemDeErro) {
        super(mensagemDeErro);
    }

}
