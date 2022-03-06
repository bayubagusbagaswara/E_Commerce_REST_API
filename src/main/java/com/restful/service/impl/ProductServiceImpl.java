package com.restful.service.impl;

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
import java.util.stream.Collectors;

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
    public ProductResponseDto createProduct(CreateProductRequestDto createProductRequestDto) throws CategoryNotFoundException, ProductDetailNotFoundException {

        // find category by id
        Category category = categoryRepository.findById(createProductRequestDto.getCategoryId()).orElseThrow(() -> new CategoryNotFoundException("Category ID ["+createProductRequestDto.getCategoryId()+"] not found"));

        // saat kita create new product, otomatis kita input data baru product detail
        // namun bedanya, data sku dan description itu ditaruh di table sendiri

        // jadi kita tetap create new ProductDetail, lalu masukkan dto ke object ProductDetail
        // lalu save di productDetailRepository, lalu masukkan ke productDetail di object product
        ProductDetail productDetail = new ProductDetail();
        productDetail.setSku(createProductRequestDto.getSku());
        productDetail.setDescription(createProductRequestDto.getDescription());
        productDetail.setCreatedAt(LocalDateTime.now());

        // create object product
        Product product = new Product();
        product.setName(createProductRequestDto.getName());
        product.setPrice(createProductRequestDto.getPrice());
        product.setQuantity(createProductRequestDto.getQuantity());
        product.setProductDetail(productDetail);
        product.setCategory(category);
        product.setCreatedAt(LocalDateTime.now());

        productDetailRepository.save(productDetail);
        productRepository.save(product);
        return mapProductToProductResponseDto(product);
    }

    @Override
    public ProductResponseDto getProductById(String productId) throws ProductNotFoundException {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product ID: [" + productId + "] not found"));
        return mapProductToProductResponseDto(product);
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
        List<ProductResponseDto> productResponseDtoList = mapProductListToProductResponseDtoList(productList);

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
        // get product id
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product ID ["+productId+"] not found"));

        // get Category yang baru
        categoryRepository.findById(updateProductRequestDto.getCategoryId()).orElseThrow(() -> new CategoryNotFoundException("Category ID ["+updateProductRequestDto.getCategoryId()+"] not found"));

        // update category


        return null;
    }

    @Override
    public void deleteProduct(String productId) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(productId);

        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException("Product ID ["+productId+"] not found");
        }
        productOptional.get().removeSuppliers();
        productRepository.deleteById(productOptional.get().getId());
    }

    @Override
    public ProductResponseDto getProductByName(String name) throws ProductNotFoundException {
        Product product = productRepository.findProductByNameIgnoreCase(name).orElseThrow(() -> new ProductNotFoundException("Product name [" + name + "] not found"));
        return mapProductToProductResponseDto(product);
    }

    @Override
    public List<ProductResponseDto> getProductByContainingName(String name) {
        List<Product> productList = productRepository.findAllByNameContainingIgnoreCase(name);
        return mapProductListToProductResponseDtoList(productList);
    }

    @Override
    public ProductResponseDto getProductBySku(String sku) throws ProductNotFoundException {
        Product product = productRepository.findAllByProductDetailSku(sku).orElseThrow(() -> new ProductNotFoundException("Product SKU [" + sku + "] not found"));
        return mapProductToProductResponseDto(product);
    }

    @Override
    public List<ProductResponseDto> getProductByCategoryId(String categoryId) {
        List<Product> productList = productRepository.findAllByCategoryId(categoryId);
        return mapProductListToProductResponseDtoList(productList);
    }

    @Override
    public List<ProductResponseDto> getProductBySuppliersId(String supplierId) {
        List<Product> productList = productRepository.findAllBySuppliersId(supplierId);
        return mapProductListToProductResponseDtoList(productList);
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

    private List<ProductResponseDto> mapProductListToProductResponseDtoList(List<Product> productList) {
        return productList.stream()
                .map((product) -> {
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
                })
                .collect(Collectors.toList())
                ;
    }
}
