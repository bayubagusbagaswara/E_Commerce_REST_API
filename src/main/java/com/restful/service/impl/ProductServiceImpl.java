package com.restful.service.impl;

import com.restful.dto.product.CreateProductRequestDto;
import com.restful.entity.Category;
import com.restful.entity.Product;
import com.restful.entity.ProductDetail;
import com.restful.repository.CategoryRepository;
import com.restful.repository.ProductDetailRepository;
import com.restful.repository.ProductRepository;
import com.restful.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductDetailRepository productDetailRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ProductDetailRepository productDetailRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productDetailRepository = productDetailRepository;
    }

    @Override
    public CreateProductRequestDto createProduct(CreateProductRequestDto createProductRequestDto) {
        // find category by id
        Category category = categoryRepository.findById(createProductRequestDto.getCategoryId()).orElseThrow();

        // find product detail by id
        ProductDetail productDetail = productDetailRepository.findById(createProductRequestDto.getProductDetailId()).orElseThrow();


        // create object product
        Product product = new Product();

        productRepository.save(product);

        return null;
    }
}
