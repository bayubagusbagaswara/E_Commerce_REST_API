package com.restful.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SupplierNotFoundException extends Exception {

    public SupplierNotFoundException() {
    }

    public SupplierNotFoundException(String message) {
        super(message);
    }
}
