package com.ecommerce.controller;

import com.ecommerce.dto.WebResponseDTO;
import com.ecommerce.dto.supplier.*;
import com.ecommerce.exception.KelurahanNotFoundException;
import com.ecommerce.exception.ProductNotFoundException;
import com.ecommerce.exception.SupplierNotFoundException;
import com.ecommerce.service.SupplierService;
import com.ecommerce.util.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDTO<SupplierResponseDto> createSupplier(@RequestBody CreateSupplierRequestDTO createSupplierRequest) throws KelurahanNotFoundException {
        final SupplierResponseDto supplierResponse = supplierService.createSupplier(createSupplierRequest);
        return WebResponseDTO.<SupplierResponseDto>builder()
                .code(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED.getReasonPhrase())
                .data(supplierResponse)
                .build();
    }

    @GetMapping(value = "/{idSupplier}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDTO<SupplierResponseDto> getSupplierById(@PathVariable("idSupplier") String id) throws SupplierNotFoundException {
        final SupplierResponseDto supplierResponse = supplierService.getSupplierById(id);
        return WebResponseDTO.<SupplierResponseDto>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(supplierResponse)
                .build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDTO<ListSupplierResponseDto> getAllSuppliers(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {

        ListSupplierRequestDto requestDto = new ListSupplierRequestDto();
        requestDto.setPageNo(pageNo);
        requestDto.setPageSize(pageSize);
        requestDto.setSortBy(sortBy);
        requestDto.setSortDir(sortDir);

        final ListSupplierResponseDto supplierResponse = supplierService.getAllSuppliers(requestDto);
        return WebResponseDTO.<ListSupplierResponseDto>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(supplierResponse)
                .build();
    }

    @PutMapping(value = "/{idSupplier}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDTO<SupplierResponseDto> updateSupplier(@PathVariable("idSupplier") String id, @RequestBody UpdateSupplierRequestDto updateSupplierRequest) throws SupplierNotFoundException, KelurahanNotFoundException {
        final SupplierResponseDto supplierResponse = supplierService.updateSupplier(id, updateSupplierRequest);
        return WebResponseDTO.<SupplierResponseDto>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(supplierResponse)
                .build();
    }

    @DeleteMapping(value = "/{idSupplier}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDTO<String> deleteSupplier(@PathVariable("idSupplier") String id) throws SupplierNotFoundException {
        supplierService.deleteSupplier(id);
        return WebResponseDTO.<String>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(null)
                .build();
    }

    @GetMapping(value = "/name", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDTO<SupplierResponseDto> getSupplierByName(@RequestParam("name") String name) throws SupplierNotFoundException {
        final SupplierResponseDto supplierResponse = supplierService.getSupplierByName(name);
        return WebResponseDTO.<SupplierResponseDto>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(supplierResponse)
                .build();
    }

    @GetMapping(value = "/name/contains", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDTO<List<SupplierResponseDto>> getSupplierByNameContains(@RequestParam("name") String name) {
        final List<SupplierResponseDto> supplierResponse = supplierService.getSupplierByNameContains(name);
        return WebResponseDTO.<List<SupplierResponseDto>>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(supplierResponse)
                .build();
    }

    @GetMapping(value = "/email", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDTO<SupplierResponseDto> getSupplierByEmail(@RequestParam("email") String email) throws SupplierNotFoundException {
        final SupplierResponseDto supplierResponse = supplierService.getSupplierByEmail(email);
        return WebResponseDTO.<SupplierResponseDto>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(supplierResponse)
                .build();
    }

    @GetMapping(value = "/product/{idProduct}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDTO<List<SupplierResponseDto>> getSupplierByProductId(@PathVariable("idProduct") String idProduct) {
        final List<SupplierResponseDto> supplierResponseList = supplierService.getSupplierByProductsId(idProduct);
        return WebResponseDTO.<List<SupplierResponseDto>>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(supplierResponseList)
                .build();
    }

    @PostMapping(value = "/{idSupplier}/product/{idProduct}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDTO<SupplierResponseDto> addProductToSupplier(@PathVariable("idSupplier") String idSupplier, @PathVariable("idProduct") String idProduct) throws SupplierNotFoundException, ProductNotFoundException {
        final SupplierResponseDto supplierResponse = supplierService.addProductToSupplier(idSupplier, idProduct);
        return WebResponseDTO.<SupplierResponseDto>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(supplierResponse)
                .build();
    }
}
