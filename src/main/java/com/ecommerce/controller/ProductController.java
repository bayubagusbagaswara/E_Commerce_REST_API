package com.ecommerce.controller;

import com.ecommerce.dto.WebResponseDto;
import com.ecommerce.dto.product.*;
import com.ecommerce.exception.CategoryNotFoundException;
import com.ecommerce.exception.ProductNotFoundException;
import com.ecommerce.service.ProductService;
import com.ecommerce.util.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<ProductResponseDto> createProduct(@RequestBody CreateProductRequestDTO createProductRequest) throws CategoryNotFoundException {
        final ProductResponseDto productResponse = productService.createProduct(createProductRequest);
        return WebResponseDto.<ProductResponseDto>builder()
                .code(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED.getReasonPhrase())
                .data(productResponse)
                .build();
    }

    @GetMapping(value = "/{idProduct}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<ProductResponseDto> getProductById(@PathVariable("idProduct") String id) throws ProductNotFoundException {
        final ProductResponseDto productResponse = productService.getProductById(id);
        return WebResponseDto.<ProductResponseDto>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(productResponse)
                .build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<ListProductResponseDto> getAllProducts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {

        ListProductRequestDto requestDto = new ListProductRequestDto();
        requestDto.setPageNo(pageNo);
        requestDto.setPageSize(pageSize);
        requestDto.setSortBy(sortBy);
        requestDto.setSortDir(sortDir);

        final ListProductResponseDto productResponseList = productService.getAllProducts(requestDto);
        return WebResponseDto.<ListProductResponseDto>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(productResponseList)
                .build();
    }

    @PutMapping(value = "/{idProduct}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<ProductResponseDto> updateProduct(@PathVariable("idProduct") String id, @RequestBody UpdateProductRequestDto updateProductRequest) throws CategoryNotFoundException, ProductNotFoundException {
        final ProductResponseDto productResponse = productService.updateProduct(id, updateProductRequest);
        return WebResponseDto.<ProductResponseDto>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(productResponse)
                .build();
    }

    @DeleteMapping(value = "/{idProduct}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<String> deleteProduct(@PathVariable("idProduct") String id) throws ProductNotFoundException {
        productService.deleteProduct(id);
        return WebResponseDto.<String>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(null)
                .build();
    }

    @GetMapping(value = "/name", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<ProductResponseDto> getProductByName(@RequestParam("name") String name) throws ProductNotFoundException {
        final ProductResponseDto productResponse = productService.getProductByName(name);
        return WebResponseDto.<ProductResponseDto>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(productResponse)
                .build();
    }

    @GetMapping(value = "/name/contains", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<List<ProductResponseDto>> getProductByNameContains(@RequestParam("name") String name) {
        final List<ProductResponseDto> productResponseList = productService.getProductByContainingName(name);
        return WebResponseDto.<List<ProductResponseDto>>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(productResponseList)
                .build();
    }

    @GetMapping(value = "/sku", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<ProductResponseDto> getProductBySku(@RequestParam("sku") String sku) throws ProductNotFoundException {
        final ProductResponseDto productResponse = productService.getProductBySku(sku);
        return WebResponseDto.<ProductResponseDto>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(productResponse)
                .build();
    }

    @GetMapping(value = "/category/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<List<ProductResponseDto>> getProductByCategoryId(@PathVariable("idCategory") String idCategory) {
        final List<ProductResponseDto> productResponseList = productService.getProductByCategoryId(idCategory);
        return WebResponseDto.<List<ProductResponseDto>>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(productResponseList)
                .build();
    }

    @GetMapping(value = "/supplier/{idSupplier}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<List<ProductResponseDto>> getProductsBySupplierId(@PathVariable("idSupplier") String idSupplier) {
        final List<ProductResponseDto> productResponseList = productService.getProductBySuppliersId(idSupplier);
        return WebResponseDto.<List<ProductResponseDto>>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(productResponseList)
                .build();
    }
}
