package com.omerozturk.fourthhomework.pym.utilities.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PymPaymentNotFoundException extends RuntimeException {

    public PymPaymentNotFoundException(String message) {
        super(message);
    }
}
