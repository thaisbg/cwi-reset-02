package br.com.cwi.reset.reseat.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PedidoNaoDisponivelParaEntregaException extends RuntimeException {
    public PedidoNaoDisponivelParaEntregaException(String mensagem) {
        super(mensagem);
    }
}
