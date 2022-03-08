package com.restful.service;

import com.restful.dto.category.*;
import com.restful.exception.CategoryNotFoundException;

import java.util.List;

public interface CategoryService {

    CategoryResponseDto createCategory(CreateCategoryRequestDto createCategoryRequestDto);

    CategoryResponseDto getCategoryById(String categoryId) throws CategoryNotFoundException;

    ListAllCategoryResponseDto getAllCategories(ListAllCategoryRequestDto listAllCategoryRequestDto);

    CategoryResponseDto updateCategory(String categoryId, UpdateCategoryRequestDto updateCategoryRequestDto) throws CategoryNotFoundException;

    void deleteCategory(String categoryId);

    CategoryResponseDto getCategoryByName(String name) throws CategoryNotFoundException;

    List<CategoryResponseDto> getCategoryByNameContains(String name);

    List<CategoryResponseDto> getCategoryStartingWithName(String name);
}
