package com.ecommerce.mapper;

import com.ecommerce.dto.category.CategoryDTO;
import com.ecommerce.dto.product.ProductDTO;
import com.ecommerce.entity.Category;
import com.ecommerce.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    private final ProductDetailMapper productDetailMapper;

    public CategoryMapper(ProductDetailMapper productDetailMapper) {
        this.productDetailMapper = productDetailMapper;
    }

    public ProductDTO productResponseDto(Product product) {
        ProductDTO productResponse = new ProductDTO();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        productResponse.setQuantity(product.getQuantity());
        productResponse.setProductDetail(productDetailMapper.mapToProductDetailResponse(product.getProductDetail()));
        productResponse.setCreatedDate(product.getCreatedDate());
        productResponse.setUpdatedDate(product.getUpdatedDate());
        return productResponse;
    }

    public CategoryDTO mapToCategoryResponse(Category category) {
        CategoryDTO categoryResponse = new CategoryDTO();
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

    public List<CategoryDTO> mapToCategoryResponseList(List<Category> categoryList) {
        return categoryList.stream()
                .map(this::mapToCategoryResponse)
                .collect(Collectors.toList());
    }
}
