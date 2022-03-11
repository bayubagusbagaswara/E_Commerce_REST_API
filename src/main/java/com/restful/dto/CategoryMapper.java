package com.restful.dto;

import com.restful.dto.category.CategoryResponseDto;
import com.restful.dto.product.ProductResponseDto;
import com.restful.entity.Category;
import com.restful.entity.Product;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class CategoryMapper {

    private final ProductDetailMapper productDetailMapper;;

    public CategoryMapper(ProductDetailMapper productDetailMapper) {
        this.productDetailMapper = productDetailMapper;
    }

    // tanpa category dan tanpa supplier
    public ProductResponseDto productResponseDto(Product product) {
        ProductResponseDto productDto = new ProductResponseDto();
        productDto.setId(productDto.getId());
        productDto.setName(productDto.getName());
        productDto.setPrice(product.getPrice());
        productDto.setQuantity(product.getQuantity());
        productDto.setProductDetail(productDetailMapper.mapProductDetailToProductDetailResponseDto(product.getProductDetail()));
        productDto.setCreatedAt(product.getCreatedAt());
        productDto.setUpdatedAt(product.getUpdatedAt());
        return productDto;
    }

    public CategoryResponseDto mapCategoryToCategoryResponseDto(Category category) {
        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        dto.setCreatedAt(category.getCreatedAt());
        dto.setUpdatedAt(category.getUpdatedAt());
        dto.setProductList(category.getProducts().stream()
                .map(this::productResponseDto)
                .collect(Collectors.toList()));
        return dto;
    }

    public List<CategoryResponseDto> mapCategoryListToCategoryResponseDtoList(List<Category> categoryList) {
        return categoryList
                .stream()
                .map(this::mapCategoryToCategoryResponseDto)
                .collect(Collectors.toList())
                ;
    }
}
