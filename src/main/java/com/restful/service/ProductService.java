package com.restful.service;

import com.restful.dto.product.*;
import com.restful.exception.CategoryNotFoundException;
import com.restful.exception.ProductDetailNotFoundException;
import com.restful.exception.ProductNotFoundException;

import java.util.List;

public interface ProductService {

    // create new product
    ProductResponseDto createProduct(CreateProductRequestDto createProductRequestDto) throws CategoryNotFoundException, ProductDetailNotFoundException;

    // get product by id
    ProductResponseDto getProductById(String productId) throws ProductNotFoundException;

    // list all product
    ListProductResponseDto getAllProducts(ListProductRequestDto listProductRequestDto);

    // update product by id
    ProductResponseDto updateProduct(String productId, UpdateProductRequestDto updateProductRequestDto) throws ProductNotFoundException, CategoryNotFoundException;

    // delete product by id
    void deleteProduct(String productId) throws ProductNotFoundException;

    // get product by name fix
    ProductResponseDto getProductByName(String name) throws ProductNotFoundException;

    // get product by containing name
    List<ProductResponseDto> getProductByContainingName(String name);

    // get product by SKU
    ProductResponseDto getProductBySku(String sku) throws ProductNotFoundException;

    // get all product by category id
    List<ProductResponseDto> getProductByCategoryId(String categoryId);

    // get product by supplier
    // jadi supplier ini memiliki product apa saja
    List<ProductResponseDto> getProductBySuppliersId(String supplierId);
}
