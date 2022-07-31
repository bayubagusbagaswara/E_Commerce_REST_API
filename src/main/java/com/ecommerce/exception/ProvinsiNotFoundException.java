package com.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProvinsiNotFoundException extends Exception {

    public ProvinsiNotFoundException() {
    }

    public ProvinsiNotFoundException(String message) {
        super(message);
    }
}
