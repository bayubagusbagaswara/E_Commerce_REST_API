package com.ecommerce.service.impl;

import com.ecommerce.mapper.ProductMapper;
import com.ecommerce.dto.product.*;
import com.ecommerce.entity.Category;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.ProductDetail;
import com.ecommerce.exception.CategoryNotFoundException;
import com.ecommerce.exception.ProductNotFoundException;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDTO createProduct(CreateProductRequestDTO createProductRequest) {
        Category category = categoryRepository.findById(createProductRequest.getCategoryId()).orElseThrow(() -> new CategoryNotFoundException("id", createProductRequest.getCategoryId()));
        Product product = Product.builder()
                .name(createProductRequest.getName())
                .price(createProductRequest.getPrice())
                .quantity(createProductRequest.getQuantity())
                .productDetail(new ProductDetail(createProductRequest.getSku(), createProductRequest.getDescription()))
                .category(category)
                .build();
        productRepository.save(product);
        return productMapper.fromProduct(product);
    }

    @Override
    public ProductDTO getProductById(String productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("id", productId));
        return productMapper.fromProduct(product);
    }

    @Override
    public ListProductResponseDTO getAllProducts(ListProductRequestDTO listProductRequest) {
        Integer pageNo = listProductRequest.getPageNo();
        Integer pageSize = listProductRequest.getPageSize();
        String sortBy = listProductRequest.getSortBy();
        String sortDir = listProductRequest.getSortDir();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Product> productPage = productRepository.findAll(pageable);
        List<Product> productList = productPage.getContent();
        List<ProductDTO> productDTOS = productMapper.fromProductList(productList);

        return ListProductResponseDTO.builder()
                .productDTOList(productDTOS)
                .pageNo(productPage.getNumber())
                .pageSize(productPage.getSize())
                .totalElements(productPage.getTotalElements())
                .totalPages(productPage.getTotalPages())
                .last(productPage.isLast())
                .build();
    }

    @Override
    public ProductDTO updateProduct(String productId, UpdateProductRequestDTO updateProductRequest) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("id", productId));
        Category category = categoryRepository.findById(updateProductRequest.getCategoryId()).orElseThrow(() -> new CategoryNotFoundException("id", updateProductRequest.getCategoryId()));

        ProductDetail productDetail = product.getProductDetail();
        productDetail.setSku(updateProductRequest.getSku());
        productDetail.setDescription(updateProductRequest.getDescription());

        product.setName(updateProductRequest.getName());
        product.setPrice(updateProductRequest.getPrice());
        product.setQuantity(updateProductRequest.getQuantity());
        product.setProductDetail(productDetail);
        product.setCategory(category);
        product.setUpdatedAt(Instant.now());

        productRepository.save(product);
        return productMapper.fromProduct(product);
    }

    @Override
    public void deleteProduct(String productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException("id", productId);
        }
        productOptional.get().removeSuppliers();
        productRepository.deleteById(productOptional.get().getId());
    }

    @Override
    public ProductDTO getProductByName(String name) {
        Product product = productRepository.findProductByNameIgnoreCase(name).orElseThrow(() -> new ProductNotFoundException("name", name));
        return productMapper.fromProduct(product);
    }

    @Override
    public List<ProductDTO> getProductByContainingName(String name) {
        List<Product> productList = productRepository.findAllByNameContainingIgnoreCase(name);
        return productMapper.fromProductList(productList);
    }

    @Override
    public ProductDTO getProductBySku(String sku) {
        Product product = productRepository.findAllByProductDetailSku(sku).orElseThrow(() -> new ProductNotFoundException("SKU", sku));
        return productMapper.fromProduct(product);
    }

    @Override
    public List<ProductDTO> getProductByCategoryId(String categoryId) {
        List<Product> productList = productRepository.findAllByCategoryId(categoryId);
        return productMapper.fromProductList(productList);
    }

    @Override
    public List<ProductDTO> getProductBySuppliersId(String supplierId) {
        List<Product> productList = productRepository.findAllBySuppliersId(supplierId);
        return productMapper.fromProductList(productList);
    }
}
