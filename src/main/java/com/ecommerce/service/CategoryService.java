package com.ecommerce.service;

import com.ecommerce.dto.category.*;

import java.util.List;

public interface CategoryService {

    CategoryDTO createCategory(CreateCategoryRequestDTO createCategoryRequest);

    CategoryDTO getCategoryById(String categoryId);

    ListCategoryResponseDTO getAllCategories(ListCategoryRequestDTO listCategoryRequest);

    CategoryDTO updateCategory(String categoryId, UpdateCategoryRequestDTO updateCategoryRequest);

    void deleteCategory(String categoryId);

    CategoryDTO getCategoryByName(String name);

    List<CategoryDTO> getCategoryByNameContains(String name);

    List<CategoryDTO> getCategoryStartingWithName(String name);

    CategoryDTO getCategoryByProductId(String productId);
}
