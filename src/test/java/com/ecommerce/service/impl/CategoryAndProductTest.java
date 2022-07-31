package com.ecommerce.service.impl;

import com.ecommerce.dto.category.CategoryResponseDto;
import com.ecommerce.dto.category.UpdateCategoryRequestDto;
import com.ecommerce.dto.product.ProductResponseDto;
import com.ecommerce.exception.CategoryNotFoundException;
import com.ecommerce.exception.ProductNotFoundException;
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
@Sql(scripts = {
        "classpath:/sql/delete-data-product.sql",
        "classpath:/sql/delete-data-product-detail.sql",
        "classpath:/sql/delete-data-category.sql",
        "classpath:/sql/sample-data-category.sql",
        "classpath:/sql/sample-data-product-detail.sql",
        "classpath:/sql/sample-data-product.sql",
})
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
    @Order(1)
    void testRemoveCategory() throws CategoryNotFoundException {
        // [laptop]
        String categoryId = "laptop";
        categoryService.deleteCategory(categoryId);
        assertThrows(CategoryNotFoundException.class, () -> {
            final CategoryResponseDto category = categoryService.getCategoryById(categoryId);
        });

        // check di product, pastikan data productnya juga hilang
        // [legion, macbook]
        String productId1 = "macbook";
        assertThrows(ProductNotFoundException.class, () -> {
            final ProductResponseDto product = productService.getProductById(productId1);
        });

        String productId2 = "legion";
        assertThrows(ProductNotFoundException.class, () -> {
            final ProductResponseDto product = productService.getProductById(productId2);
        });

        // kalau kita update cascade set null, maka data product nya tetap ada, handa data category nya kita set null
    }

    @Test
    @Order(2)
    void testUpdateCategory() throws CategoryNotFoundException, ProductNotFoundException {
        String categoryId = "laptop";
        UpdateCategoryRequestDto updateCategoryRequest = new UpdateCategoryRequestDto();
        updateCategoryRequest.setName("Laptop Update");
        updateCategoryRequest.setDescription("Laptop update description");

        final CategoryResponseDto categoryResponse = categoryService.updateCategory(categoryId, updateCategoryRequest);
        assertEquals(categoryId, categoryResponse.getId());

        // cek di product
        // caranya ambil data product, lalu bandingkan equals data categorynya
        String productId = "macbook";
        final ProductResponseDto product = productService.getProductById(productId);
        assertEquals("Laptop Update", product.getCategory().getName());
        assertEquals("Laptop update description", product.getCategory().getDescription());

        log.info("Category Name: {}", categoryResponse.getName());
        log.info("Product category: {}", product.getCategory().getName());
    }

    @Test
    @Order(3)
    void testDeleteProduct() throws ProductNotFoundException, CategoryNotFoundException {
        // jika kita delete product, maka hanya product nya yang di delete,
        // dan tidak akan delete category
        String productId = "legion";
        productService.deleteProduct(productId);
        assertThrows(ProductNotFoundException.class, () -> {
            final ProductResponseDto product = productService.getProductById(productId);
        });

        // kita cek category nya
        String categoryId = "laptop";
        final CategoryResponseDto category = categoryService.getCategoryById(categoryId);
        assertNotNull(category);
        assertEquals(categoryId, category.getId());
    }
}
