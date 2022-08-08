package com.ecommerce.service.impl;

import com.ecommerce.dto.product.*;
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

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Sql(scripts = {
        "classpath:/sql/delete-data-supplier.sql",
        "classpath:/sql/delete-data-product.sql",
        "classpath:/sql/delete-data-product-detail.sql",
        "classpath:/sql/delete-data-category.sql",
        "classpath:/sql/sample-data-category.sql",
        "classpath:/sql/sample-data-product-detail.sql",
        "classpath:/sql/sample-data-product.sql",
        "classpath:/sql/sample-data-supplier.sql"
})
class ProductServiceImplTest {

    private final static Logger log = LoggerFactory.getLogger(ProductServiceImplTest.class);

    @Autowired
    ProductServiceImpl productService;

    @Autowired
    CategoryServiceImpl categoryService;

    @Test
    @Order(1)
    void createProduct() throws CategoryNotFoundException {
        CreateProductRequestDTO requestDto = new CreateProductRequestDTO();
        requestDto.setName("Asus test");
        requestDto.setPrice(new BigDecimal(5_000_000));
        requestDto.setQuantity(12);
        requestDto.setSku("SKU-Asus");
        requestDto.setDescription("Asus description");
        // misal kita create product dengan category Laptop
        requestDto.setCategoryId("laptop");

        final ProductDTO responseDto = productService.createProduct(requestDto);
        assertNotNull(responseDto.getId());
        assertNotNull(responseDto.getUpdatedAt());
        assertEquals("Asus test", responseDto.getName());
        assertEquals("SKU-Asus", responseDto.getProductDetail().getSku());
    }

    @Test
    @Order(2)
    void getProductById() throws ProductNotFoundException {
        String id = "macbook";
        final ProductDTO responseDto = productService.getProductById(id);
        assertEquals("macbook", responseDto.getId());
        log.info("ID: {}", responseDto.getId());
    }

    @Test
    @Order(3)
    void getAllProducts() {
        Integer totalSampleData = 11;
        Integer pageNo = 0;
        Integer pageSize = 5;
        String sortBy = "name";
        String sortDir = "asc";

        ListProductRequestDTO requestDto = new ListProductRequestDTO();
        requestDto.setPageNo(pageNo);
        requestDto.setPageSize(pageSize);
        requestDto.setSortBy(sortBy);
        requestDto.setSortDir(sortDir);

        final ListProductResponseDTO products = productService.getAllProducts(requestDto);
        assertEquals(11, products.getTotalElements());
        assertEquals(5, products.getProductDTOList().size());
    }

    @Test
    @Order(4)
    void updateProduct() throws CategoryNotFoundException, ProductNotFoundException {
        String id = "legion";
        UpdateProductRequestDTO requestDto = new UpdateProductRequestDTO();
        requestDto.setName("Lenovo update");
        requestDto.setPrice(new BigDecimal(7_700_000));
        requestDto.setQuantity(90);
        requestDto.setSku("SKU-LenovoUpdate");
        requestDto.setDescription("Lenovo update description");
        // kita ganti category menjadi phone
        requestDto.setCategoryId("phone");

        final ProductDTO responseDto = productService.updateProduct(id, requestDto);
        assertEquals(id, responseDto.getId());
        assertNotNull(responseDto.getUpdatedAt());
    }

    @Test
    @Order(5)
    void deleteProduct() throws ProductNotFoundException {
        String id = "";
        productService.deleteProduct(id);
        assertThrows(ProductNotFoundException.class, () -> {
            final ProductDTO product = productService.getProductById(id);
        });
    }

    @Test
    @Order(6)
    void getProductByName() throws ProductNotFoundException {
        String name = "";
        final ProductDTO product = productService.getProductByName(name);
        assertEquals(name, product.getName());
    }

    @Test
    @Order(7)
    void getProductByContainingName() {
        String name = "";
        final List<ProductDTO> products = productService.getProductByContainingName(name);
        assertEquals(0, products.size());
    }

    @Test
    @Order(8)
    void getProductBySku() throws ProductNotFoundException {
        String sku = "";
        final ProductDTO product = productService.getProductBySku(sku);
        assertEquals(sku, product.getProductDetail().getSku());
    }

    @Test
    @Order(9)
    void getProductByCategoryId() {
        String categoryId = "";
        final List<ProductDTO> products = productService.getProductByCategoryId(categoryId);
        assertEquals(0, products.size());
    }

    @Test
    @Order(10)
    void getProductBySuppliersId() {
        String supplierId = "";
        final List<ProductDTO> products = productService.getProductBySuppliersId(supplierId);
        assertEquals(0, products.size());
    }
}