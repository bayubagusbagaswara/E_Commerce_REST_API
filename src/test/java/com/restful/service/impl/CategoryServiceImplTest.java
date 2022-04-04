package com.restful.service.impl;

import com.restful.dto.category.*;
import com.restful.exception.CategoryNotFoundException;
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
        CreateCategoryRequestDto requestDto = new CreateCategoryRequestDto();
        requestDto.setName("Category test");
        requestDto.setDescription("Category description");

        final CategoryResponseDto responseDto = categoryService.createCategory(requestDto);
        assertNotNull(responseDto.getId());
        assertNotNull(responseDto.getCreatedDate());
        assertEquals(requestDto.getName(), responseDto.getName());
        log.info("Name: {}", responseDto.getName());
    }

    @Test
    @Order(2)
    void getCategoryById() throws CategoryNotFoundException {
        String id = "laptop";
        final CategoryResponseDto responseDto = categoryService.getCategoryById(id);
        assertEquals(id, responseDto.getId());
    }

    @Test
    @Order(3)
    void getAllCategories() {
        int totalSampleData = 8;
        int pageNo = 0;
        int pageSize = 5;
        String sortBy = "name";
        String sordDir = "asc";

        ListCategoryRequestDto requestDto = new ListCategoryRequestDto();
        requestDto.setPageNo(pageNo);
        requestDto.setPageSize(pageSize);
        requestDto.setSortBy(sortBy);
        requestDto.setSortDir(sordDir);

        final ListCategoryResponseDto responseDto = categoryService.getAllCategories(requestDto);
        assertEquals(totalSampleData, responseDto.getTotalElements());
        assertEquals(pageSize, responseDto.getCategoryResponseList().size());
    }

    @Test
    @Order(4)
    void updateCategory() throws CategoryNotFoundException {
        String id = "tablet";
        UpdateCategoryRequestDto requestDto = new UpdateCategoryRequestDto();
        requestDto.setName("Tablet Update");
        requestDto.setDescription("Tablet description update");

        final CategoryResponseDto responseDto = categoryService.updateCategory(id, requestDto);
        assertEquals(id, responseDto.getId());
        assertNotNull(responseDto.getUpdatedDate());
        assertNotEquals(responseDto.getCreatedDate(), responseDto.getUpdatedDate());
    }

    @Test
    @Order(5)
    void deleteCategory() throws CategoryNotFoundException {
        String id = "camera";
        categoryService.deleteCategory(id);
        assertThrows(CategoryNotFoundException.class, () -> {
            final CategoryResponseDto category = categoryService.getCategoryById(id);
        });
    }


    @Test
    @Order(6)
    void getCategoryByName() throws CategoryNotFoundException {
        String name = "laptop";
        final CategoryResponseDto responseDto = categoryService.getCategoryByName(name);
        assertEquals(name, responseDto.getName().toLowerCase());
        log.info("Name: {}", responseDto.getName());
    }

    @Test
    @Order(7)
    void getCategoryByNameContains() {
        // [Monitor, Television, Hand Phone]
        String name = "on";
        int totalData = 3;
        final List<CategoryResponseDto> responseDtoList = categoryService.getCategoryByNameContains(name);
        assertEquals(totalData, responseDtoList.size());
    }


    @Test
    @Order(8)
    void getCategoryStartingWithName() {
        // [Mouser, Monitor]
        String name = "mo";
        int totalData = 2;
        final List<CategoryResponseDto> responseDtoList = categoryService.getCategoryStartingWithName(name);
        assertEquals(totalData, responseDtoList.size());
    }
}