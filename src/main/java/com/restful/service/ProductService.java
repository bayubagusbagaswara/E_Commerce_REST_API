package com.restful.service;

import com.restful.dto.product.CreateProductRequestDto;
import com.restful.exception.CategoryNotFoundException;
import com.restful.exception.ProductDetailNotFoundException;

public interface ProductService {

    // create new product
    CreateProductRequestDto createProduct(CreateProductRequestDto createProductRequestDto) throws CategoryNotFoundException, ProductDetailNotFoundException;

    // get product by id

    // list all product

    // update product by id

    // delete product by id

    // get product by name

    // get all product by category id
}
