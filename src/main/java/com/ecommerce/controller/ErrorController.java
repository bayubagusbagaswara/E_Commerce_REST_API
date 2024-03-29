package com.ecommerce.controller;

import com.ecommerce.dto.WebResponseDTO;
import com.ecommerce.exception.*;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(value = {
            CategoryNotFoundException.class,
            ProvinceNotFoundException.class,
            DistrictNotFoundException.class,
            SubDistrictNotFoundException.class,
            UrbanVillageNotFoundException.class,
            ProductNotFoundException.class,
            SupplierNotFoundException.class,
            ProductDetailNotFoundException.class
    })
    public WebResponseDTO<String> dataNotFoundHandler(
            CategoryNotFoundException categoryNotFoundException,
            ProductNotFoundException productNotFoundException,
            SupplierNotFoundException supplierNotFoundException,
            ProductDetailNotFoundException productDetailNotFoundException,
            ProvinceNotFoundException provinceNotFoundException,
            DistrictNotFoundException districtNotFoundException,
            SubDistrictNotFoundException subDistrictNotFoundException,
            UrbanVillageNotFoundException urbanVillageNotFoundException
    ) {
        return WebResponseDTO.<String>builder()
                .code(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND.getReasonPhrase())
                .data("Data not found")
                .build();
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public WebResponseDTO<String> validatorHandler(ConstraintViolationException constraintViolationException) {
        return WebResponseDTO.<String>builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .data(constraintViolationException.getMessage())
                .build();
    }
}