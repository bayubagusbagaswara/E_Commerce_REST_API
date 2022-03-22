package com.restful.controller;

import com.restful.dto.WebResponseDto;
import com.restful.dto.category.CategoryResponseDto;
import com.restful.dto.category.CreateCategoryRequestDto;
import com.restful.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<CategoryResponseDto> createCategory(@RequestBody CreateCategoryRequestDto createCategoryRequest) {
        CategoryResponseDto categoryResponse = categoryService.createCategory(createCategoryRequest);
        return WebResponseDto.<CategoryResponseDto>builder()
                .code(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED.getReasonPhrase())
                .data(categoryResponse)
                .build();
    }
}
