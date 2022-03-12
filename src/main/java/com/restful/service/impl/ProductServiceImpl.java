package com.restful.service.impl;

import com.restful.dto.ProductMapper;
import com.restful.dto.product.*;
import com.restful.entity.Category;
import com.restful.entity.Product;
import com.restful.entity.ProductDetail;
import com.restful.exception.CategoryNotFoundException;
import com.restful.exception.ProductDetailNotFoundException;
import com.restful.exception.ProductNotFoundException;
import com.restful.repository.CategoryRepository;
import com.restful.repository.ProductDetailRepository;
import com.restful.repository.ProductRepository;
import com.restful.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductDetailRepository productDetailRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ProductDetailRepository productDetailRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productDetailRepository = productDetailRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductResponseDto createProduct(CreateProductRequestDto createProductRequest) throws CategoryNotFoundException, ProductDetailNotFoundException {

        Category category = categoryRepository.findById(createProductRequest.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category ID [" + createProductRequest.getCategoryId() + "] not found"));

        ProductDetail productDetail = new ProductDetail();
        productDetail.setSku(createProductRequest.getSku());
        productDetail.setDescription(createProductRequest.getDescription());
        productDetail.setCreatedDate(LocalDateTime.now());

        Product product = new Product();
        product.setName(createProductRequest.getName());
        product.setPrice(createProductRequest.getPrice());
        product.setQuantity(createProductRequest.getQuantity());
        product.setProductDetail(productDetail);
        product.setCategory(category);
        product.setCreatedDate(LocalDateTime.now());

        productDetailRepository.save(productDetail);
        productRepository.save(product);
        return productMapper.mapProductToProductResponseDto(product);
    }

    @Override
    public ProductResponseDto getProductById(String productId) throws ProductNotFoundException {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product ID: [" + productId + "] not found"));
        return productMapper.mapProductToProductResponseDto(product);
    }

    @Override
    public ListProductResponseDto getAllProducts(ListProductRequestDto listProductRequestDto) {
        int pageNo = listProductRequestDto.getPageNo();
        int pageSize = listProductRequestDto.getPageSize();
        String sortBy = listProductRequestDto.getSortBy();
        String sortDir = listProductRequestDto.getSortDir();

        // cek sortDir apakah ASC atau DESC, jika true maka sort by ascending, jika false maka sort by descending
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Product> productPage = productRepository.findAll(pageable);
        List<Product> productList = productPage.getContent();

        List<ProductResponseDto> productResponseDtoList = productMapper.mapProductListToProductResponseDtoList(productList);

        ListProductResponseDto dto = new ListProductResponseDto();
        dto.setProductResponseDtoList(productResponseDtoList);
        dto.setPageNo(productPage.getNumber());
        dto.setPageSize(productPage.getSize());
        dto.setTotalPages(productPage.getTotalPages());
        dto.setTotalElements(productPage.getTotalElements());
        dto.setLast(productPage.isLast());
        return dto;
    }

    @Override
    public ProductResponseDto updateProduct(String productId, UpdateProductRequestDto updateProductRequestDto) throws ProductNotFoundException, CategoryNotFoundException {
        final Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product ID [" + productId + "] not found"));

        final Category category = categoryRepository.findById(updateProductRequestDto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category ID [" + updateProductRequestDto.getCategoryId() + "] not found"));

        final ProductDetail productDetail = product.getProductDetail();
        productDetail.setSku(updateProductRequestDto.getSku());
        productDetail.setDescription(updateProductRequestDto.getDescription());

        product.setName(updateProductRequestDto.getName());
        product.setPrice(updateProductRequestDto.getPrice());
        product.setQuantity(updateProductRequestDto.getQuantity());
        product.setProductDetail(productDetail);
        product.setCategory(category);
        product.setUpdatedAt(LocalDateTime.now());

        productDetailRepository.save(productDetail);
        productRepository.save(product);
        return productMapper.mapProductToProductResponseDto(product);
    }

    @Override
    public void deleteProduct(String productId) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException("Product ID [" + productId + " ] not found");
        }
        productOptional.get().removeSuppliers();
        productRepository.deleteById(productOptional.get().getId());
    }

    @Override
    public ProductResponseDto getProductByName(String name) throws ProductNotFoundException {
        Product product = productRepository.findProductByNameIgnoreCase(name)
                .orElseThrow(() -> new ProductNotFoundException("Product name [" + name + "] not found"));
        return productMapper.mapProductToProductResponseDto(product);
    }

    @Override
    public List<ProductResponseDto> getProductByContainingName(String name) {
        List<Product> productList = productRepository.findAllByNameContainingIgnoreCase(name);
        return productMapper.mapProductListToProductResponseDtoList(productList);
    }

    @Override
    public ProductResponseDto getProductBySku(String sku) throws ProductNotFoundException {
        Product product = productRepository.findAllByProductDetailSku(sku)
                .orElseThrow(() -> new ProductNotFoundException("Product SKU [" + sku + "] not found"));
        return productMapper.mapProductToProductResponseDto(product);
    }

    @Override
    public List<ProductResponseDto> getProductByCategoryId(String categoryId) {
        List<Product> productList = productRepository.findAllByCategoryId(categoryId);
        return productMapper.mapProductListToProductResponseDtoList(productList);
    }

    @Override
    public List<ProductResponseDto> getProductBySuppliersId(String supplierId) {
        List<Product> productList = productRepository.findAllBySuppliersId(supplierId);
        return productMapper.mapProductListToProductResponseDtoList(productList);
    }
}
