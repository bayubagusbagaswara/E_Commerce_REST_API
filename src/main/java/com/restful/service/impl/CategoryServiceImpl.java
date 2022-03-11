package com.restful.service.impl;

import com.restful.dto.CategoryMapper;
import com.restful.dto.category.*;
import com.restful.entity.Category;
import com.restful.exception.CategoryNotFoundException;
import com.restful.repository.CategoryRepository;
import com.restful.repository.ProductRepository;
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
    public CategoryResponseDto createCategory(CreateCategoryRequestDto createCategoryRequestDto) {
        Category category = new Category();
        category.setName(createCategoryRequestDto.getName());
        category.setDescription(createCategoryRequestDto.getDescription());
        category.setCreatedDate(LocalDateTime.now());
        categoryRepository.save(category);
        return categoryMapper.mapCategoryToCategoryResponseDto(category);
    }

    @Override
    public CategoryResponseDto getCategoryById(String categoryId) throws CategoryNotFoundException {
        Category category = getCategory(categoryId);
        return categoryMapper.mapCategoryToCategoryResponseDto(category);
    }

    @Override
    public ListAllCategoryResponseDto getAllCategories(ListAllCategoryRequestDto listAllCategoryRequestDto) {
        int pageNo = listAllCategoryRequestDto.getPageNo();
        int pageSize = listAllCategoryRequestDto.getPageSize();
        String sortBy = listAllCategoryRequestDto.getSortBy();
        String sortDir = listAllCategoryRequestDto.getSortDir();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Category> categories = categoryRepository.findAll(pageable);
        List<Category> categoryList = categories.getContent();

        List<CategoryResponseDto> categoryResponseDtoList = categoryMapper.mapCategoryListToCategoryResponseDtoList(categoryList);

        ListAllCategoryResponseDto listAllCategoryResponseDto = new ListAllCategoryResponseDto();
        listAllCategoryResponseDto.setCategoryResponseDtoList(categoryResponseDtoList);
        listAllCategoryResponseDto.setPageNo(categories.getNumber());
        listAllCategoryResponseDto.setPageSize(categories.getSize());
        listAllCategoryResponseDto.setTotalElements(categories.getTotalElements());
        listAllCategoryResponseDto.setTotalPages(categories.getTotalPages());
        listAllCategoryResponseDto.setLast(categories.isLast());
        return listAllCategoryResponseDto;
    }

    @Override
    public CategoryResponseDto updateCategory(String categoryId, UpdateCategoryRequestDto updateCategoryRequestDto) throws CategoryNotFoundException {
        Category category = getCategory(categoryId);
        category.setName(updateCategoryRequestDto.getName());
        category.setDescription(updateCategoryRequestDto.getDescription());
        category.setUpdatedDate(LocalDateTime.now());
        categoryRepository.save(category);
        return categoryMapper.mapCategoryToCategoryResponseDto(category);
    }

    @Override
    public CategoryResponseDto getCategoryByName(String name) throws CategoryNotFoundException {
        Category category = categoryRepository.findAllByNameIgnoreCase(name).orElseThrow(() -> new CategoryNotFoundException("Category name [" + name + "] not found"));
        return categoryMapper.mapCategoryToCategoryResponseDto(category);
    }

    @Override
    public List<CategoryResponseDto> getCategoryByNameContains(String name) {
        List<Category> categoryList = categoryRepository.findAllByNameContainingIgnoreCase(name);
        return categoryMapper.mapCategoryListToCategoryResponseDtoList(categoryList);
    }

    @Override
    public void deleteCategory(String categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public List<CategoryResponseDto> getCategoryStartingWithName(String name) {
        List<Category> categoryList = categoryRepository.findAllByNameIgnoreCaseStartsWith(name);
        return categoryMapper.mapCategoryListToCategoryResponseDtoList(categoryList);
    }

    private Category getCategory(String categoryId) throws CategoryNotFoundException {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("Category ID: [" + categoryId + "] not found"));
    }
}
