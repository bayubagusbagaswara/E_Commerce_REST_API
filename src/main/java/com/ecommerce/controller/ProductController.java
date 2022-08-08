package com.ecommerce.controller;

import com.ecommerce.dto.WebResponseDTO;
import com.ecommerce.dto.product.*;
import com.ecommerce.exception.CategoryNotFoundException;
import com.ecommerce.exception.ProductNotFoundException;
import com.ecommerce.service.ProductService;
import com.ecommerce.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<ProductDTO>> createProduct(@RequestBody CreateProductRequestDTO createProductRequest) {
        ProductDTO product = productService.createProduct(createProductRequest);
        return new ResponseEntity<>(new WebResponseDTO<>(201, "CREATED", product), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{idProduct}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDTO<ProductDTO> getProductById(@PathVariable("idProduct") String id) throws ProductNotFoundException {
        final ProductDTO productResponse = productService.getProductById(id);
        return WebResponseDTO.<ProductDTO>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(productResponse)
                .build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDTO<ListProductResponseDTO> getAllProducts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {

        ListProductRequestDTO requestDto = new ListProductRequestDTO();
        requestDto.setPageNo(pageNo);
        requestDto.setPageSize(pageSize);
        requestDto.setSortBy(sortBy);
        requestDto.setSortDir(sortDir);

        final ListProductResponseDTO productResponseList = productService.getAllProducts(requestDto);
        return WebResponseDTO.<ListProductResponseDTO>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(productResponseList)
                .build();
    }

    @PutMapping(value = "/{idProduct}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDTO<ProductDTO> updateProduct(@PathVariable("idProduct") String id, @RequestBody UpdateProductRequestDTO updateProductRequest) throws CategoryNotFoundException, ProductNotFoundException {
        final ProductDTO productResponse = productService.updateProduct(id, updateProductRequest);
        return WebResponseDTO.<ProductDTO>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(productResponse)
                .build();
    }

    @DeleteMapping(value = "/{idProduct}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDTO<String> deleteProduct(@PathVariable("idProduct") String id) throws ProductNotFoundException {
        productService.deleteProduct(id);
        return WebResponseDTO.<String>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(null)
                .build();
    }

    @GetMapping(value = "/name", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDTO<ProductDTO> getProductByName(@RequestParam("name") String name) throws ProductNotFoundException {
        final ProductDTO productResponse = productService.getProductByName(name);
        return WebResponseDTO.<ProductDTO>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(productResponse)
                .build();
    }

    @GetMapping(value = "/name/contains", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDTO<List<ProductDTO>> getProductByNameContains(@RequestParam("name") String name) {
        final List<ProductDTO> productResponseList = productService.getProductByContainingName(name);
        return WebResponseDTO.<List<ProductDTO>>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(productResponseList)
                .build();
    }

    @GetMapping(value = "/sku", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDTO<ProductDTO> getProductBySku(@RequestParam("sku") String sku) throws ProductNotFoundException {
        final ProductDTO productResponse = productService.getProductBySku(sku);
        return WebResponseDTO.<ProductDTO>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(productResponse)
                .build();
    }

    @GetMapping(value = "/category/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDTO<List<ProductDTO>> getProductByCategoryId(@PathVariable("idCategory") String idCategory) {
        final List<ProductDTO> productResponseList = productService.getProductByCategoryId(idCategory);
        return WebResponseDTO.<List<ProductDTO>>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(productResponseList)
                .build();
    }

    @GetMapping(value = "/supplier/{idSupplier}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDTO<List<ProductDTO>> getProductsBySupplierId(@PathVariable("idSupplier") String idSupplier) {
        final List<ProductDTO> productResponseList = productService.getProductBySuppliersId(idSupplier);
        return WebResponseDTO.<List<ProductDTO>>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(productResponseList)
                .build();
    }
}
