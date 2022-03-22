package com.restful.controller;

import com.restful.dto.WebResponseDto;
import com.restful.dto.product.CreateProductRequestDto;
import com.restful.dto.product.ProductResponseDto;
import com.restful.exception.CategoryNotFoundException;
import com.restful.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<ProductResponseDto> createProduct(@RequestBody CreateProductRequestDto createProductRequest) throws CategoryNotFoundException {
        final ProductResponseDto productResponse = productService.createProduct(createProductRequest);
        return WebResponseDto.<ProductResponseDto>builder()
                .code(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED.getReasonPhrase())
                .data(productResponse)
                .build();
    }
}
