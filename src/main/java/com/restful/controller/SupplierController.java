package com.restful.controller;

import com.restful.dto.WebResponseDto;
import com.restful.dto.supplier.CreateSupplierRequestDto;
import com.restful.dto.supplier.SupplierResponseDto;
import com.restful.exception.KelurahanNotFoundException;
import com.restful.service.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<SupplierResponseDto> createSupplier(@RequestBody CreateSupplierRequestDto createSupplierRequest) throws KelurahanNotFoundException {
        final SupplierResponseDto supplierResponse = supplierService.createSupplier(createSupplierRequest);
        return WebResponseDto.<SupplierResponseDto>builder()
                .code(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED.getReasonPhrase())
                .data(supplierResponse)
                .build();
    }
}
