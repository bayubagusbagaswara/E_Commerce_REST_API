package com.restful.controller;

import com.restful.dto.WebResponseDto;
import com.restful.dto.category.*;
import com.restful.exception.CategoryNotFoundException;
import com.restful.service.CategoryService;
import com.restful.util.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // consumes = Content-Type (atau yang dikirim dari request user) atau ini yang dikonsumsi oleh API
    // produces = Accept (yang dikirim dari server berupa response json) atau ini hasil (produksi) dari API
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<CategoryResponseDto> createCategory(@RequestBody CreateCategoryRequestDto createCategoryRequest) {
        CategoryResponseDto categoryResponse = categoryService.createCategory(createCategoryRequest);
        return WebResponseDto.<CategoryResponseDto>builder()
                .code(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED.getReasonPhrase())
                .data(categoryResponse)
                .build();
    }

    // get Category By ID
    @GetMapping(value = "/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<CategoryResponseDto> getCategoryById(@PathVariable("idCategory") String id) throws CategoryNotFoundException {
        final CategoryResponseDto categoryResponse = categoryService.getCategoryById(id);
        return WebResponseDto.<CategoryResponseDto>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(categoryResponse)
                .build();
    }

    // get All Category
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<ListCategoryResponseDto> getAllCategories(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {

        ListCategoryRequestDto requestDto = new ListCategoryRequestDto();
        requestDto.setPageNo(pageNo);
        requestDto.setPageSize(pageSize);
        requestDto.setSortBy(sortBy);
        requestDto.setSortDir(sortDir);

        final ListCategoryResponseDto allCategories = categoryService.getAllCategories(requestDto);
        return WebResponseDto.<ListCategoryResponseDto>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(allCategories)
                .build();
    }

    // update Category
    @PutMapping(value = "/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<CategoryResponseDto> updateCategory(@PathVariable("idCategory") String id, @RequestBody UpdateCategoryRequestDto updateCategoryRequest) throws CategoryNotFoundException {
        final CategoryResponseDto categoryResponse = categoryService.updateCategory(id, updateCategoryRequest);
        return WebResponseDto.<CategoryResponseDto>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(categoryResponse)
                .build();
    }

    // delete Category
    @DeleteMapping(value = "/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<String> deleteCategory(@PathVariable("idCategory") String id) {
        categoryService.deleteCategory(id);
        return WebResponseDto.<String>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(null)
                .build();
    }

    // get CategoryBy Name Containing (ini harusnya bisa dengan endpoint /categorie atau getAllCategories)
}
