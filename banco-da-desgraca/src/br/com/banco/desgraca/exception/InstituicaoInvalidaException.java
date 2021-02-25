package br.com.banco.desgraca.exception;

public class InstituicaoInvalidaException extends RuntimeException {

    public InstituicaoInvalidaException (String mensagemDeErro) {
        super(mensagemDeErro);
    }
}