package br.com.cwi.reset.reseat.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class EntregadoresIndisponiveisException extends RuntimeException {
    public EntregadoresIndisponiveisException(String mensagem) {
        super(mensagem);
    }
}
