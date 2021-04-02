package br.com.cwi.reset.reseat.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EstabelecimentoNaoEncontradoException extends RuntimeException {
    public EstabelecimentoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
