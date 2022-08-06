package com.ecommerce.service;

import com.ecommerce.dto.product.*;

import java.util.List;

public interface ProductService {

    ProductDTO createProduct(CreateProductRequestDTO createProductRequest);

    ProductDTO updateProduct(String productId, UpdateProductRequestDTO updateProductRequest);

    void deleteProduct(String productId);

    ProductDTO getProductById(String productId);

    ListProductResponseDTO getAllProducts(ListProductRequestDTO listProductRequest);

    ProductDTO getProductByName(String name);

    List<ProductDTO> getProductByContainingName(String name);

    ProductDTO getProductBySku(String sku);

    List<ProductDTO> getProductByCategoryId(String categoryId);

    List<ProductDTO> getProductBySuppliersId(String supplierId);

}
