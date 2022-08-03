package com.ecommerce.controller;

import com.ecommerce.dto.WebResponseDto;
import com.ecommerce.dto.category.*;
import com.ecommerce.exception.CategoryNotFoundException;
import com.ecommerce.service.CategoryService;
import com.ecommerce.util.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<CategoryDTO> createCategory(@RequestBody CreateCategoryRequestDTO createCategoryRequest) {
        CategoryDTO categoryResponse = categoryService.createCategory(createCategoryRequest);
        return WebResponseDto.<CategoryDTO>builder()
                .code(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED.getReasonPhrase())
                .data(categoryResponse)
                .build();
    }

    @GetMapping(value = "/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<CategoryDTO> getCategoryById(@PathVariable("idCategory") String id) throws CategoryNotFoundException {
        final CategoryDTO categoryResponse = categoryService.getCategoryById(id);
        return WebResponseDto.<CategoryDTO>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(categoryResponse)
                .build();
    }

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

    @PutMapping(value = "/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<CategoryDTO> updateCategory(@PathVariable("idCategory") String id, @RequestBody UpdateCategoryRequestDto updateCategoryRequest) throws CategoryNotFoundException {
        final CategoryDTO categoryResponse = categoryService.updateCategory(id, updateCategoryRequest);
        return WebResponseDto.<CategoryDTO>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(categoryResponse)
                .build();
    }

    @DeleteMapping(value = "/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<String> deleteCategory(@PathVariable("idCategory") String id) throws CategoryNotFoundException {
        categoryService.deleteCategory(id);
        return WebResponseDto.<String>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(null)
                .build();
    }

    @GetMapping(value = "/name", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<CategoryDTO> getCategoryByName(@RequestParam("name") String name) throws CategoryNotFoundException {
        final CategoryDTO categoryResponse = categoryService.getCategoryByName(name);
        return WebResponseDto.<CategoryDTO>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(categoryResponse)
                .build();
    }

    @GetMapping(value = "/name/contains", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<List<CategoryDTO>> getCategoryByNameContains(@RequestParam("name") String name) {
        final List<CategoryDTO> categoryResponseList = categoryService.getCategoryByNameContains(name);
        return WebResponseDto.<List<CategoryDTO>>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(categoryResponseList)
                .build();
    }

    @GetMapping(value = "/name/starting", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<List<CategoryDTO>> getCategoryByNameStartsWith(@RequestParam("name") String name) {
        final List<CategoryDTO> categoryResponseList = categoryService.getCategoryStartingWithName(name);
        return WebResponseDto.<List<CategoryDTO>>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(categoryResponseList)
                .build();
    }

    @GetMapping(value = "/product/{idProduct}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<CategoryDTO> getCategoryByProductId(@PathVariable("idProduct") String id) throws CategoryNotFoundException {
        final CategoryDTO categoryResponse = categoryService.getCategoryByProductId(id);
        return WebResponseDto.<CategoryDTO>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(categoryResponse)
                .build();
    }
}
