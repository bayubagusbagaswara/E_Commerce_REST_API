package com.restful.service.impl;

import com.restful.dto.CategoryMapper;
import com.restful.dto.category.*;
import com.restful.entity.Category;
import com.restful.exception.CategoryNotFoundException;
import com.restful.repository.CategoryRepository;
import com.restful.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryResponseDto createCategory(CreateCategoryRequestDto createCategoryRequest) {
        Category category = new Category();
        category.setName(createCategoryRequest.getName());
        category.setDescription(createCategoryRequest.getDescription());
        category.setCreatedDate(LocalDateTime.now());
        categoryRepository.save(category);
        return categoryMapper.mapToCategoryResponse(category);
    }

    @Override
    public CategoryResponseDto getCategoryById(String id) throws CategoryNotFoundException {
        Category category = getCategory(id);
        return categoryMapper.mapToCategoryResponse(category);
    }

    @Override
    public ListCategoryResponseDto getAllCategories(ListCategoryRequestDto listCategoryRequest) {
        Integer pageNo = listCategoryRequest.getPageNo();
        Integer pageSize = listCategoryRequest.getPageSize();
        String sortBy = listCategoryRequest.getSortBy();
        String sortDir = listCategoryRequest.getSortDir();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Category> categories = categoryRepository.findAll(pageable);
        List<Category> categoryList = categories.getContent();

        List<CategoryResponseDto> categoryResponseList = categoryMapper.mapToCategoryResponseList(categoryList);

        ListCategoryResponseDto listCategoryResponse = new ListCategoryResponseDto();
        listCategoryResponse.setCategoryResponseList(categoryResponseList);
        listCategoryResponse.setPageNo(categories.getNumber());
        listCategoryResponse.setPageSize(categories.getSize());
        listCategoryResponse.setTotalElements(categories.getTotalElements());
        listCategoryResponse.setTotalPages(categories.getTotalPages());
        listCategoryResponse.setLast(categories.isLast());
        return listCategoryResponse;
    }

    @Override
    public CategoryResponseDto updateCategory(String id, UpdateCategoryRequestDto updateCategoryRequest) throws CategoryNotFoundException {
        Category category = getCategory(id);
        category.setName(updateCategoryRequest.getName());
        category.setDescription(updateCategoryRequest.getDescription());
        category.setUpdatedDate(LocalDateTime.now());
        categoryRepository.save(category);
        return categoryMapper.mapToCategoryResponse(category);
    }

    @Override
    public CategoryResponseDto getCategoryByName(String name) throws CategoryNotFoundException {
        Category category = categoryRepository.findAllByNameIgnoreCase(name).orElseThrow(() -> new CategoryNotFoundException("Category name [" + name + "] not found"));
        return categoryMapper.mapToCategoryResponse(category);
    }

    @Override
    public List<CategoryResponseDto> getCategoryByNameContains(String name) {
        List<Category> categoryList = categoryRepository.findAllByNameContainingIgnoreCase(name);
        return categoryMapper.mapToCategoryResponseList(categoryList);
    }

    @Override
    public void deleteCategory(String categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public List<CategoryResponseDto> getCategoryStartingWithName(String name) {
        List<Category> categoryList = categoryRepository.findAllByNameIgnoreCaseStartsWith(name);
        return categoryMapper.mapToCategoryResponseList(categoryList);
    }

    private Category getCategory(String id) throws CategoryNotFoundException {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category ID: [" + id + "] not found"));
    }
}
