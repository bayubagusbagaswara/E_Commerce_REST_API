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


    public ProductDTO fromEntity(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setProductDetail(product.getProductDetail());
        return productDTO;
    }

    public CategoryDTO fromEntity(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());
        categoryDTO.setCreatedAt(category.getCreatedAt());
        categoryDTO.setProductList(category.getProducts());
        return categoryDTO;
    }

    public List<CategoryDTO> fromEntities(List<Category> categoryList) {
        return categoryList.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}
