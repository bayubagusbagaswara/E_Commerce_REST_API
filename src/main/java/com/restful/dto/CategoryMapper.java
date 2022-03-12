package com.restful.dto;

import com.restful.dto.category.CategoryResponseDto;
import com.restful.dto.product.ProductResponseDto;
import com.restful.entity.Category;
import com.restful.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    private final ProductDetailMapper productDetailMapper;

    public CategoryMapper(ProductDetailMapper productDetailMapper) {
        this.productDetailMapper = productDetailMapper;
    }

    public ProductResponseDto productResponseDto(Product product) {
        ProductResponseDto productResponse = new ProductResponseDto();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        productResponse.setQuantity(product.getQuantity());
        productResponse.setProductDetail(productDetailMapper.mapToProductDetailResponse(product.getProductDetail()));
        productResponse.setCreatedDate(product.getCreatedDate());
        productResponse.setUpdatedDate(product.getUpdatedDate());
        return productResponse;
    }

    public CategoryResponseDto mapToCategoryResponse(Category category) {
        CategoryResponseDto categoryResponse = new CategoryResponseDto();
        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());
        categoryResponse.setDescription(category.getDescription());
        categoryResponse.setCreatedDate(category.getCreatedDate());
        categoryResponse.setUpdatedDate(category.getUpdatedDate());
        categoryResponse.setProductList(category.getProducts().stream()
                .map(this::productResponseDto)
                .collect(Collectors.toList()));
        return categoryResponse;
    }

    public List<CategoryResponseDto> mapToCategoryResponseList(List<Category> categoryList) {
        return categoryList.stream()
                .map(this::mapToCategoryResponse)
                .collect(Collectors.toList());
    }
}
