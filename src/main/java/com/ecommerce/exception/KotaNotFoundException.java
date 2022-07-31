package com.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class KotaNotFoundException extends Exception {

    public KotaNotFoundException() {
    }

    public KotaNotFoundException(String message) {
        super(message);
    }
}
