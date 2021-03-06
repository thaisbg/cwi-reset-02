package br.com.cwi.resetflix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = 7321950247402549380L;
    static HttpStatus status = HttpStatus.BAD_REQUEST;

    public BadRequestException(final String statusText) {
        super(statusText);
    }
}
