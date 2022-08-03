package com.ecommerce.service;

import com.ecommerce.dto.product.*;
import com.ecommerce.exception.CategoryNotFoundException;
import com.ecommerce.exception.ProductNotFoundException;

import java.util.List;

public interface ProductService {

    ProductDTO createProduct(CreateProductRequestDTO createProductRequest) throws CategoryNotFoundException;

    ProductDTO getProductById(String id) throws ProductNotFoundException;

    ListProductResponseDTO getAllProducts(ListProductRequestDTO listProductRequest);

    ProductDTO updateProduct(String id, UpdateProductRequestDto updateProductRequest) throws ProductNotFoundException, CategoryNotFoundException;

    void deleteProduct(String id) throws ProductNotFoundException;

    ProductDTO getProductByName(String name) throws ProductNotFoundException;

    List<ProductDTO> getProductByContainingName(String name);

    ProductDTO getProductBySku(String sku) throws ProductNotFoundException;

    List<ProductDTO> getProductByCategoryId(String categoryId);

    List<ProductDTO> getProductBySuppliersId(String supplierId);

    // save product to database
}
