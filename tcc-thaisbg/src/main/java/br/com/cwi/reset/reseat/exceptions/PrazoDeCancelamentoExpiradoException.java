package br.com.cwi.reset.reseat.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PrazoDeCancelamentoExpiradoException extends RuntimeException {
    public PrazoDeCancelamentoExpiradoException(String mensagem) {
        super(mensagem);
    }
}
