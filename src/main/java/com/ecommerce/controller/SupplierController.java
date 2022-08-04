package com.ecommerce.controller;

import com.ecommerce.dto.WebResponseDTO;
import com.ecommerce.dto.supplier.*;
import com.ecommerce.exception.UrbanVillageNotFoundException;
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
    public WebResponseDTO<SupplierDTO> createSupplier(@RequestBody CreateSupplierRequestDTO createSupplierRequest) throws UrbanVillageNotFoundException {
        final SupplierDTO supplierResponse = supplierService.createSupplier(createSupplierRequest);
        return WebResponseDTO.<SupplierDTO>builder()
                .code(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED.getReasonPhrase())
                .data(supplierResponse)
                .build();
    }

    @GetMapping(value = "/{idSupplier}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDTO<SupplierDTO> getSupplierById(@PathVariable("idSupplier") String id) throws SupplierNotFoundException {
        final SupplierDTO supplierResponse = supplierService.getSupplierById(id);
        return WebResponseDTO.<SupplierDTO>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(supplierResponse)
                .build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDTO<ListSupplierResponseDTO> getAllSuppliers(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {

        ListSupplierRequestDTO requestDto = new ListSupplierRequestDTO();
        requestDto.setPageNo(pageNo);
        requestDto.setPageSize(pageSize);
        requestDto.setSortBy(sortBy);
        requestDto.setSortDir(sortDir);

        final ListSupplierResponseDTO supplierResponse = supplierService.getAllSuppliers(requestDto);
        return WebResponseDTO.<ListSupplierResponseDTO>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(supplierResponse)
                .build();
    }

    @PutMapping(value = "/{idSupplier}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDTO<SupplierDTO> updateSupplier(@PathVariable("idSupplier") String id, @RequestBody UpdateSupplierRequestDTO updateSupplierRequest) throws SupplierNotFoundException, UrbanVillageNotFoundException {
        final SupplierDTO supplierResponse = supplierService.updateSupplier(id, updateSupplierRequest);
        return WebResponseDTO.<SupplierDTO>builder()
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
    public WebResponseDTO<SupplierDTO> getSupplierByName(@RequestParam("name") String name) throws SupplierNotFoundException {
        final SupplierDTO supplierResponse = supplierService.getSupplierByName(name);
        return WebResponseDTO.<SupplierDTO>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(supplierResponse)
                .build();
    }

    @GetMapping(value = "/name/contains", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDTO<List<SupplierDTO>> getSupplierByNameContains(@RequestParam("name") String name) {
        final List<SupplierDTO> supplierResponse = supplierService.getSupplierByNameContains(name);
        return WebResponseDTO.<List<SupplierDTO>>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(supplierResponse)
                .build();
    }

    @GetMapping(value = "/email", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDTO<SupplierDTO> getSupplierByEmail(@RequestParam("email") String email) throws SupplierNotFoundException {
        final SupplierDTO supplierResponse = supplierService.getSupplierByEmail(email);
        return WebResponseDTO.<SupplierDTO>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(supplierResponse)
                .build();
    }

    @GetMapping(value = "/product/{idProduct}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDTO<List<SupplierDTO>> getSupplierByProductId(@PathVariable("idProduct") String idProduct) {
        final List<SupplierDTO> supplierResponseList = supplierService.getSupplierByProductsId(idProduct);
        return WebResponseDTO.<List<SupplierDTO>>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(supplierResponseList)
                .build();
    }

    @PostMapping(value = "/{idSupplier}/product/{idProduct}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDTO<SupplierDTO> addProductToSupplier(@PathVariable("idSupplier") String idSupplier, @PathVariable("idProduct") String idProduct) throws SupplierNotFoundException, ProductNotFoundException {
        final SupplierDTO supplierResponse = supplierService.addProductToSupplier(idSupplier, idProduct);
        return WebResponseDTO.<SupplierDTO>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(supplierResponse)
                .build();
    }
}
