package com.bubbles.server.web.viewmodel;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;

/**
 * Exception for invalid http request or invalid object binding.
 *
 * @author Mingshan Lei
 * @since 2015/5/29
 */
public class InvalidRequestException extends AbstractException {

    public InvalidRequestException(String message, Errors errors) {
        super(message, errors);
        super.httpstatus = HttpStatus.UNPROCESSABLE_ENTITY;
        super.code = "InvalidRequest";
    }

}
