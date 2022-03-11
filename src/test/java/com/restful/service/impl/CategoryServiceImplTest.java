package com.restful.service.impl;

import com.restful.dto.category.CategoryResponseDto;
import com.restful.dto.category.CreateCategoryRequestDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Sql(scripts = {})
class CategoryServiceImplTest {

    private final static Logger log = LoggerFactory.getLogger(CategoryServiceImplTest.class);

    @Autowired
    CategoryServiceImpl categoryService;

    @Test
    @Order(1)
    void createCategory() {
        CreateCategoryRequestDto requestDto = new CreateCategoryRequestDto();
        requestDto.setName("Category test");
        requestDto.setDescription("Category description");

        final CategoryResponseDto responseDto = categoryService.createCategory(requestDto);
        assertNotNull(responseDto.getId());
        assertNotNull(responseDto.getCreatedAt());
        assertEquals(requestDto.getName(), responseDto.getName());
    }

    @Test
    @Order(2)
    void getCategoryById() {
    }

    @Test
    @Order(3)
    void getAllCategories() {
    }

    @Test
    @Order(4)
    void updateCategory() {
    }

    @Test
    @Order(5)
    void getCategoryByName() {
    }

    @Test
    @Order(6)
    void getCategoryByNameContains() {
    }

    @Test
    @Order(7)
    void deleteCategory() {
    }

    @Test
    @Order(8)
    void getCategoryStartingWithName() {
    }
}