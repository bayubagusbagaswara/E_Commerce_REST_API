package com.restful.service.impl;

import com.restful.dto.category.CategoryResponseDto;
import com.restful.dto.category.UpdateCategoryRequestDto;
import com.restful.dto.product.ProductResponseDto;
import com.restful.exception.CategoryNotFoundException;
import com.restful.exception.ProductNotFoundException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CategoryAndProductTest {

    // kita akan test relasi antara Category dan Product
    // Skenario 1: dimana jika category dihapus, maka pastikan semua product yang berelasi dengan category tersebut juga dihapus
    // Skenario 2: dimana jika category diupdate, maka data category di product juga terupdate
    // Skenario 3: dimana jika data product dihapus, maka tidak akan menghapus data di table category
    // Skenario 4: dimana jika data product diupdate, maka tidak akan mengubah data di table category

    private final static Logger log = LoggerFactory.getLogger(CategoryAndProductTest.class);

    @Autowired
    CategoryServiceImpl categoryService;

    @Autowired
    ProductServiceImpl productService;

    @Test
    void testRemoveCategory() {
        // [laptop]
        String categoryId = "laptop";
        categoryService.deleteCategory(categoryId);
        assertThrows(CategoryNotFoundException.class, () -> {
            final CategoryResponseDto category = categoryService.getCategoryById(categoryId);
        });

        // check di product, pastikan data productnya juga hilang
        // [lenovo-legion-5, macbook-pro-2021]
        String productId1 = "macbook-pro-2021";
        assertThrows(ProductNotFoundException.class, () -> {
            final ProductResponseDto product = productService.getProductById(productId1);
        });

        String productId2 = "lenovo-legion-5";
        assertThrows(ProductNotFoundException.class, () -> {
            final ProductResponseDto product = productService.getProductById(productId2);
        });

        // kalau kita update cascade set null, maka data product nya tetap ada, handa data category nya kita set null
    }

    @Test
    void testUpdateCategory() throws CategoryNotFoundException, ProductNotFoundException {
        String categoryId = "laptop";
        UpdateCategoryRequestDto updateCategoryRequest = new UpdateCategoryRequestDto();
        updateCategoryRequest.setName("Laptop Update");
        updateCategoryRequest.setDescription("Laptop update description");

        final CategoryResponseDto categoryResponse = categoryService.updateCategory(categoryId, updateCategoryRequest);
        assertEquals(categoryId, categoryResponse.getId());

        // cek di product
        // caranya ambil data product, lalu bandingkan equals data categorynya
        String productId = "macbook-pro-2021";
        final ProductResponseDto product = productService.getProductById(productId);
        assertEquals("Laptop Update", product.getCategory().getName());
        assertEquals("Laptop update description", product.getCategory().getDescription());

        log.info("Category Name: {}", categoryResponse.getName());
        log.info("Product category: {}", product.getCategory().getName());
    }
}
