package com.ecommerce.service;

import com.ecommerce.dto.category.*;
import com.ecommerce.exception.CategoryNotFoundException;

import java.util.List;

public interface CategoryService {

    CategoryDTO createCategory(CreateCategoryRequestDTO createCategoryRequest);

    CategoryDTO getCategoryById(String id) throws CategoryNotFoundException;

    ListCategoryResponseDTO getAllCategories(ListCategoryRequestDTO listCategoryRequest);

    CategoryDTO updateCategory(String id, UpdateCategoryRequestDto updateCategoryRequest) throws CategoryNotFoundException;

    void deleteCategory(String id) throws CategoryNotFoundException;

    CategoryDTO getCategoryByName(String name) throws CategoryNotFoundException;

    List<CategoryDTO> getCategoryByNameContains(String name);

    List<CategoryDTO> getCategoryStartingWithName(String name);

    CategoryDTO getCategoryByProductId(String idProduct) throws CategoryNotFoundException;
}
