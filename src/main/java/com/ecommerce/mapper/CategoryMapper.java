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

    public CategoryDTO fromCategory(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());
        categoryDTO.setCreatedAt(category.getCreatedAt());
        categoryDTO.setProductList(category.getProducts());
        return categoryDTO;
    }

    public List<CategoryDTO> fromCategoryList(List<Category> categoryList) {
        return categoryList.stream()
                .map(this::fromCategory)
                .collect(Collectors.toList());
    }
}
