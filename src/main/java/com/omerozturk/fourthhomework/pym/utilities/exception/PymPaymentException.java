package com.omerozturk.fourthhomework.pym.utilities.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PymPaymentException extends RuntimeException {

    public PymPaymentException(String message) {
        super(message);
    }
}
