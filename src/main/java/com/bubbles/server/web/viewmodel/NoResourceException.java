package com.bubbles.server.web.viewmodel;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;

/**
 * Exception for resource not found.
 *
 * @author Mingshan Lei
 * @since 2015/5/29
 */
public class NoResourceException extends AbstractException {

    public NoResourceException(String message, Errors errors) {
        super(message, errors);
        super.httpstatus = HttpStatus.NOT_FOUND;
        super.code = "ResourceNotFound";
    }

}
