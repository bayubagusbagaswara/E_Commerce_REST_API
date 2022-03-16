package com.restful.controller;

import com.restful.dto.category.CategoryResponseDto;
import com.restful.dto.category.CreateCategoryRequestDto;
import com.restful.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(CreateCategoryRequestDto createCategoryRequest) {
        CategoryResponseDto category = categoryService.createCategory(createCategoryRequest);
        // tapi ini coba liat format response nya, apakah terdiri dari code, status, dan data
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }
}
