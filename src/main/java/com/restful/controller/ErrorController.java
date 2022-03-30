package com.restful.controller;

import com.restful.dto.WebResponseDto;
import com.restful.exception.*;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(value = {
            CategoryNotFoundException.class,
            ProvinsiNotFoundException.class,
            KotaNotFoundException.class,
            KecamatanNotFoundException.class,
            KelurahanNotFoundException.class,
            ProductNotFoundException.class,
            SupplierNotFoundException.class,
            ProductDetailNotFoundException.class
    })
    public WebResponseDto<String> dataNotFoundHandler(
            CategoryNotFoundException categoryNotFoundException,
            ProductNotFoundException productNotFoundException,
            SupplierNotFoundException supplierNotFoundException,
            ProductDetailNotFoundException productDetailNotFoundException,
            ProvinsiNotFoundException provinsiNotFoundException,
            KotaNotFoundException kotaNotFoundException,
            KecamatanNotFoundException kecamatanNotFoundException,
            KelurahanNotFoundException kelurahanNotFoundException
    ) {
        return WebResponseDto.<String>builder()
                .code(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND.getReasonPhrase())
                .data("Data not found")
                .build();
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public WebResponseDto<String> validatorHandler(ConstraintViolationException constraintViolationException) {
        return WebResponseDto.<String>builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .data(constraintViolationException.getMessage())
                .build();
    }
}