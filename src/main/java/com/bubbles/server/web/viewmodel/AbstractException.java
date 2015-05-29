package com.bubbles.server.web.viewmodel;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;

/**
 * Abstract exception for http request.
 *
 * @author Mingshan Lei
 * @since 2015/5/29
 */
public abstract class AbstractException extends RuntimeException {

    protected Errors errors;

    public AbstractException(String message, Errors errors) {
        super(message);
        this.errors = errors;
    }

    public Errors getErrors() {
        return errors;
    }
}
