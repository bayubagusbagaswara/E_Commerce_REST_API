package com.restful.service;

import com.restful.dto.product.*;
import com.restful.exception.CategoryNotFoundException;
import com.restful.exception.ProductDetailNotFoundException;
import com.restful.exception.ProductNotFoundException;

import java.util.List;

public interface ProductService {

    ProductResponseDto createProduct(CreateProductRequestDto createProductRequest) throws CategoryNotFoundException, ProductDetailNotFoundException;

    ProductResponseDto getProductById(String id) throws ProductNotFoundException;

    ListProductResponseDto getAllProducts(ListProductRequestDto listProductRequest);

    ProductResponseDto updateProduct(String productId, UpdateProductRequestDto updateProductRequest) throws ProductNotFoundException, CategoryNotFoundException;

    void deleteProduct(String id) throws ProductNotFoundException;

    ProductResponseDto getProductByName(String name) throws ProductNotFoundException;

    List<ProductResponseDto> getProductByContainingName(String name);

    ProductResponseDto getProductBySku(String sku) throws ProductNotFoundException;

    List<ProductResponseDto> getProductByCategoryId(String categoryId);

    List<ProductResponseDto> getProductBySuppliersId(String supplierId);
}
