package com.ecommerce.service.impl;

import com.ecommerce.mapper.CategoryMapper;
import com.ecommerce.dto.category.*;
import com.ecommerce.entity.Category;
import com.ecommerce.exception.CategoryNotFoundException;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.service.CategoryService;
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
    public CategoryDTO createCategory(CreateCategoryRequestDTO createCategoryRequest) {
        Category category = new Category();
        category.setName(createCategoryRequest.getName());
        category.setDescription(createCategoryRequest.getDescription());
        category.setCreatedDate(LocalDateTime.now());
        categoryRepository.save(category);
        return categoryMapper.mapToCategoryResponse(category);
    }

    @Override
    public CategoryDTO getCategoryById(String id) throws CategoryNotFoundException {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category ID [" + id + "] not found"));
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

        List<CategoryDTO> categoryResponseList = categoryMapper.mapToCategoryResponseList(categoryList);

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
    public CategoryDTO updateCategory(String id, UpdateCategoryRequestDto updateCategoryRequest) throws CategoryNotFoundException {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category ID [" + id + "] not found"));
        category.setName(updateCategoryRequest.getName());
        category.setDescription(updateCategoryRequest.getDescription());
        category.setUpdatedDate(LocalDateTime.now());
        categoryRepository.save(category);
        return categoryMapper.mapToCategoryResponse(category);
    }

    @Override
    public CategoryDTO getCategoryByName(String name) throws CategoryNotFoundException {
        Category category = categoryRepository.findAllByNameIgnoreCase(name).orElseThrow(() -> new CategoryNotFoundException("Category name [" + name + "] not found"));
        return categoryMapper.mapToCategoryResponse(category);
    }

    @Override
    public List<CategoryDTO> getCategoryByNameContains(String name) {
        List<Category> categoryList = categoryRepository.findAllByNameContainingIgnoreCase(name);
        return categoryMapper.mapToCategoryResponseList(categoryList);
    }

    @Override
    public void deleteCategory(String id) throws CategoryNotFoundException {
        final Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category ID [" + id + "] not found"));
        categoryRepository.delete(category);
    }

    @Override
    public List<CategoryDTO> getCategoryStartingWithName(String name) {
        List<Category> categoryList = categoryRepository.findAllByNameIgnoreCaseStartsWith(name);
        return categoryMapper.mapToCategoryResponseList(categoryList);
    }

    @Override
    public CategoryDTO getCategoryByProductId(String idProduct) throws CategoryNotFoundException {
        final Category category = categoryRepository.findAllByProductsId(idProduct)
                .orElseThrow(() -> new CategoryNotFoundException("Category with Product ID [" + idProduct + "] not found"));
        return categoryMapper.mapToCategoryResponse(category);
    }
}
