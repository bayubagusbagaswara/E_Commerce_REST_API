package com.restful.service.impl;

import org.junit.jupiter.api.MethodOrderer;
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
        "classpath:/sql/delete-data-supplier.sql",
        "classpath:/sql/delete-data-product.sql",
        "classpath:/sql/delete-data-product-detail.sql",
        "classpath:/sql/delete-data-category.sql",
        "classpath:/sql/sample-data-category.sql",
        "classpath:/sql/sample-data-product-detail.sql",
        "classpath:/sql/sample-data-product.sql",
        "classpath:/sql/sample-data-supplier"
})
class SupplierServiceImplTest {

    private final static Logger log = LoggerFactory.getLogger(SupplierServiceImplTest.class);

    @Autowired
    SupplierServiceImpl supplierService;

    @Test
    void createSupplier() {
    }

    @Test
    void getSupplierById() {
    }

    @Test
    void getAllSuppliers() {
    }

    @Test
    void updateSupplier() {
    }

    @Test
    void deleteSupplier() {
    }

    @Test
    void getSupplierByName() {
    }

    @Test
    void getSupplierByEmail() {
    }

    @Test
    void getSupplierByNameContains() {
    }

    @Test
    void getSupplierByProductsId() {
    }

    @Test
    void addProductToSupplier() {
    }
}