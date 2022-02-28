package com.restful.service.impl;

import com.restful.dto.product.CreateProductRequestDto;
import com.restful.dto.product.ProductResponseDto;
import com.restful.entity.Category;
import com.restful.entity.Product;
import com.restful.entity.ProductDetail;
import com.restful.exception.CategoryNotFoundException;
import com.restful.exception.ProductDetailNotFoundException;
import com.restful.repository.CategoryRepository;
import com.restful.repository.ProductDetailRepository;
import com.restful.repository.ProductRepository;
import com.restful.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
    public CreateProductRequestDto createProduct(CreateProductRequestDto createProductRequestDto) throws CategoryNotFoundException, ProductDetailNotFoundException {
        // find category by id
        Category category = categoryRepository.findById(createProductRequestDto.getCategoryId()).orElseThrow(() -> new CategoryNotFoundException("Category ID ["+createProductRequestDto.getCategoryId()+"] not found"));

        // find product detail by id
        ProductDetail productDetail = productDetailRepository.findById(createProductRequestDto.getProductDetailId()).orElseThrow(() -> new ProductDetailNotFoundException("Product Detail ID ["+createProductRequestDto.getProductDetailId()+"] not found"));

        // create object product
        Product product = new Product();
        product.setName(createProductRequestDto.getName());
        product.setPrice(createProductRequestDto.getPrice());
        product.setQuantity(createProductRequestDto.getQuantity());
        product.setProductDetail(productDetail);
        product.setCategory(category);
        product.setCreatedAt(LocalDateTime.now());
        productRepository.save(product);
        return null;
    }

    private ProductResponseDto mapProductToProductResponseDto(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setName(product.getName());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setQuantity(product.getQuantity());
        productResponseDto.setProductDetail(product.getProductDetail());
        productResponseDto.setCategory(product.getCategory());
        productResponseDto.setSuppliers(product.getSuppliers());
        productResponseDto.setCreatedAt(product.getCreatedAt());
        productResponseDto.setUpdatedAt(product.getUpdatedAt());
        return productResponseDto;
    }
}
