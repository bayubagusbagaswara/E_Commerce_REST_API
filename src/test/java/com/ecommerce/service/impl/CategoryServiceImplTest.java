package com.ecommerce.service.impl;

import com.ecommerce.dto.category.*;
import com.ecommerce.exception.CategoryNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Sql(scripts = {
        "classpath:/sql/delete-data-category.sql",
        "classpath:/sql/sample-data-category.sql"
})
class CategoryServiceImplTest {

    private final static Logger log = LoggerFactory.getLogger(CategoryServiceImplTest.class);

    @Autowired
    CategoryServiceImpl categoryService;

    @Test
    @Order(1)
    void createCategory() {
        CreateCategoryRequestDTO requestDto = new CreateCategoryRequestDTO();
        requestDto.setName("Category test");
        requestDto.setDescription("Category description");

        final CategoryDTO responseDto = categoryService.createCategory(requestDto);
        assertNotNull(responseDto.getId());
        assertNotNull(responseDto.getCreatedAt());
        assertEquals(requestDto.getName(), responseDto.getName());
        log.info("Name: {}", responseDto.getName());
    }

    @Test
    @Order(2)
    void getCategoryById() throws CategoryNotFoundException {
        String id = "laptop";
        final CategoryDTO responseDto = categoryService.getCategoryById(id);
        assertEquals(id, responseDto.getId());
    }

    @Test
    @Order(3)
    void getAllCategories() {
        int totalSampleData = 8;
        int pageNo = 0;
        int pageSize = 5;
        String sortBy = "name";
        String sortDir = "asc";

        ListCategoryRequestDTO requestDto = new ListCategoryRequestDTO(pageNo, pageSize, sortBy, sortDir);

        final ListCategoryResponseDTO responseDto = categoryService.getAllCategories(requestDto);
        assertEquals(totalSampleData, responseDto.getTotalElements());
        assertEquals(pageSize, responseDto.getCategoryDTOList().size());
    }

    @Test
    @Order(4)
    void updateCategory() throws CategoryNotFoundException {
        String id = "tablet";
        UpdateCategoryRequestDTO requestDto = new UpdateCategoryRequestDTO();
        requestDto.setName("Tablet Update");
        requestDto.setDescription("Tablet description update");

        final CategoryDTO responseDto = categoryService.updateCategory(id, requestDto);
        assertEquals(id, responseDto.getId());
        assertNotNull(responseDto.getUpdatedAt());
        assertNotEquals(responseDto.getCreatedAt(), responseDto.getUpdatedAt());
    }

    @Test
    @Order(5)
    void deleteCategory() throws CategoryNotFoundException {
        String id = "camera";
        categoryService.deleteCategory(id);
        assertThrows(CategoryNotFoundException.class, () -> {
            final CategoryDTO category = categoryService.getCategoryById(id);
        });
    }


    @Test
    @Order(6)
    void getCategoryByName() throws CategoryNotFoundException {
        String name = "laptop";
        final CategoryDTO responseDto = categoryService.getCategoryByName(name);
        assertEquals(name, responseDto.getName().toLowerCase());
        log.info("Name: {}", responseDto.getName());
    }

    @Test
    @Order(7)
    void getCategoryByNameContains() {
        // [Monitor, Television, Hand Phone]
        String name = "on";
        int totalData = 3;
        final List<CategoryDTO> responseDtoList = categoryService.getCategoryByNameContains(name);
        assertEquals(totalData, responseDtoList.size());
    }


    @Test
    @Order(8)
    void getCategoryStartingWithName() {
        // [Mouser, Monitor]
        String name = "mo";
        int totalData = 2;
        final List<CategoryDTO> responseDtoList = categoryService.getCategoryStartingWithName(name);
        assertEquals(totalData, responseDtoList.size());
    }
}