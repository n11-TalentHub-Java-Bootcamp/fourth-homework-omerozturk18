package com.omerozturk.fourthhomework.usr.utilities.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsrUserNotFoundException extends RuntimeException {

    public UsrUserNotFoundException(String message) {
        super(message);
    }
}
