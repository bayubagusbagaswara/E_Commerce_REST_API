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

import java.time.Instant;
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
    public CategoryDTO createCategory(CreateCategoryRequestDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setCreatedAt(Instant.now());
        categoryRepository.save(category);
        return categoryMapper.fromCategory(category);
    }

    @Override
    public CategoryDTO getCategoryById(String categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("id", categoryId));
        return categoryMapper.fromCategory(category);
    }

    @Override
    public ListCategoryResponseDTO getAllCategories(ListCategoryRequestDTO listCategoryRequest) {
        Integer pageNo = listCategoryRequest.getPageNo();
        Integer pageSize = listCategoryRequest.getPageSize();
        String sortBy = listCategoryRequest.getSortBy();
        String sortDir = listCategoryRequest.getSortDir();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Category> categories = categoryRepository.findAll(pageable);
        List<Category> categoryList = categories.getContent();

        List<CategoryDTO> categoryDTOList = categoryMapper.fromCategoryList(categoryList);

        ListCategoryResponseDTO listCategoryResponse = new ListCategoryResponseDTO();
        listCategoryResponse.setCategoryDTOList(categoryDTOList);
        listCategoryResponse.setPageNo(categories.getNumber());
        listCategoryResponse.setPageSize(categories.getSize());
        listCategoryResponse.setTotalElements(categories.getTotalElements());
        listCategoryResponse.setTotalPages(categories.getTotalPages());
        listCategoryResponse.setLast(categories.isLast());
        return listCategoryResponse;
    }

    @Override
    public CategoryDTO updateCategory(String categoryId, UpdateCategoryRequestDTO categoryDTO) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("id", categoryId));
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setUpdatedAt(Instant.now());
        categoryRepository.save(category);
        return categoryMapper.fromCategory(category);
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        Category category = categoryRepository.findAllByNameIgnoreCase(name)
                .orElseThrow(() -> new CategoryNotFoundException("name", name));
        return categoryMapper.fromCategory(category);
    }

    @Override
    public List<CategoryDTO> getCategoryByNameContains(String name) {
        List<Category> categoryList = categoryRepository.findAllByNameContainingIgnoreCase(name);
        return categoryMapper.fromCategoryList(categoryList);
    }

    @Override
    public void deleteCategory(String categoryId) {
        final Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("id", categoryId));
        categoryRepository.delete(category);
    }

    @Override
    public List<CategoryDTO> getCategoryStartingWithName(String name) {
        List<Category> categoryList = categoryRepository.findAllByNameIgnoreCaseStartsWith(name);
        return categoryMapper.fromCategoryList(categoryList);
    }

    @Override
    public CategoryDTO getCategoryByProductId(String productId) {
        final Category category = categoryRepository.findAllByProductsId(productId)
                .orElseThrow(() -> new CategoryNotFoundException("product", productId));
        return categoryMapper.fromCategory(category);
    }
}
