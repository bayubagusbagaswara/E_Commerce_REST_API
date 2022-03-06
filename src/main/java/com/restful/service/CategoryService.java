package com.restful.service;

import com.restful.dto.category.*;
import com.restful.exception.CategoryNotFoundException;

public interface CategoryService {

    // create category
    CategoryResponseDto createCategory(CreateCategoryRequestDto createCategoryRequestDto);

    // get category by id
    CategoryResponseDto getCategoryById(String categoryId) throws CategoryNotFoundException;

    // get all category
    ListAllCategoryResponseDto getAllCategories(ListAllCategoryRequestDto listAllCategoryRequestDto);

    // update category
    CategoryResponseDto updateCategory(String categoryId, UpdateCategoryRequestDto updateCategoryRequestDto) throws CategoryNotFoundException;

    // delete category
    void deleteCategory(String categoryId);

    // get category by name
    CategoryResponseDto getCategoryByName(String name);

    // get category by name starting with
    CategoryResponseDto getCategoryStartingWithName(String name);
}
