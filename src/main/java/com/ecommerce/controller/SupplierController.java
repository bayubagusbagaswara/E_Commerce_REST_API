package com.ecommerce.controller;

import com.ecommerce.dto.WebResponseDTO;
import com.ecommerce.dto.supplier.*;
import com.ecommerce.exception.UrbanVillageNotFoundException;
import com.ecommerce.exception.ProductNotFoundException;
import com.ecommerce.exception.SupplierNotFoundException;
import com.ecommerce.service.SupplierService;
import com.ecommerce.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<SupplierDTO>> createSupplier(@RequestBody CreateSupplierRequestDTO createSupplierRequest) {
        SupplierDTO supplier = supplierService.createSupplier(createSupplierRequest);
        return new ResponseEntity<>(new WebResponseDTO<>(200, "OK", supplier), HttpStatus.OK);
    }

    @GetMapping(value = "/{supplierId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<SupplierDTO>> getSupplierById(@PathVariable(name = "supplierId") String id) {
        SupplierDTO supplier = supplierService.getSupplierById(id);
        return new ResponseEntity<>(new WebResponseDTO<>(200, "OK", supplier), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<ListSupplierResponseDTO>> getAllSuppliers(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {

        ListSupplierRequestDTO listSupplierRequestDTO = new ListSupplierRequestDTO(pageNo, pageSize, sortBy, sortDir);
        ListSupplierResponseDTO allSuppliers = supplierService.getAllSuppliers(listSupplierRequestDTO);
        return new ResponseEntity<>(new WebResponseDTO<>(200, "OK", allSuppliers), HttpStatus.OK);
    }

    @PutMapping(value = "/{supplierId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<SupplierDTO>> updateSupplier(@PathVariable(name = "supplierId") String id, @RequestBody UpdateSupplierRequestDTO updateSupplierRequest) {
        SupplierDTO supplierDTO = supplierService.updateSupplier(id, updateSupplierRequest);
        return new ResponseEntity<>(new WebResponseDTO<>(201, "CREATED", supplierDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{supplierId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<String>> deleteSupplier(@PathVariable(name = "supplierId") String id) {
        supplierService.deleteSupplier(id);
        return new ResponseEntity<>(new WebResponseDTO<>(200, "OK", "Successfully deleted."), HttpStatus.OK);
    }

    @GetMapping(value = "/name", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<SupplierDTO>> getSupplierByName(@RequestParam(name = "name") String name) {
        SupplierDTO supplier = supplierService.getSupplierByName(name);
        return new ResponseEntity<>(new WebResponseDTO<>(200, "OK", supplier), HttpStatus.OK);
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

    @GetMapping(value = "/product/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<List<SupplierDTO>>> getAllSuppliersByProductId(@PathVariable(name = "productId") String id) {
        List<SupplierDTO> supplierDTOList = supplierService.getAllSuppliersByProductId(id);
        return new ResponseEntity<>(new WebResponseDTO<>(200, "OK", supplierDTOList), HttpStatus.OK);
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
