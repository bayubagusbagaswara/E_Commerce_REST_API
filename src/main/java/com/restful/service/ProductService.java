package com.restful.service;

import com.restful.dto.product.*;
import com.restful.exception.CategoryNotFoundException;
import com.restful.exception.ProductDetailNotFoundException;
import com.restful.exception.ProductNotFoundException;

import java.util.List;

public interface ProductService {

    ProductResponseDto createProduct(CreateProductRequestDto createProductRequestDto) throws CategoryNotFoundException, ProductDetailNotFoundException;

    ProductResponseDto getProductById(String productId) throws ProductNotFoundException;

    ListProductResponseDto getAllProducts(ListProductRequestDto listProductRequestDto);

    ProductResponseDto updateProduct(String productId, UpdateProductRequestDto updateProductRequestDto) throws ProductNotFoundException, CategoryNotFoundException;

    void deleteProduct(String productId) throws ProductNotFoundException;

    ProductResponseDto getProductByName(String name) throws ProductNotFoundException;

    List<ProductResponseDto> getProductByContainingName(String name);

    ProductResponseDto getProductBySku(String sku) throws ProductNotFoundException;

    List<ProductResponseDto> getProductByCategoryId(String categoryId);

    List<ProductResponseDto> getProductBySuppliersId(String supplierId);
}
