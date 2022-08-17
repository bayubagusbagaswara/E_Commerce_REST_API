package com.ecommerce.service.impl;

import com.ecommerce.dto.supplier.*;
import com.ecommerce.entity.product.Product;
import com.ecommerce.entity.enumerator.Gender;
import com.ecommerce.exception.UrbanVillageNotFoundException;
import com.ecommerce.exception.ProductNotFoundException;
import com.ecommerce.exception.SupplierNotFoundException;
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
    @Order(1)
    void createSupplier() throws UrbanVillageNotFoundException {
        CreateSupplierRequestDTO requestDto = new CreateSupplierRequestDTO();
        requestDto.setName("Supplier");
        requestDto.setEmail("supplier@mail.com");
        requestDto.setGender(Gender.MALE);
        requestDto.setMobilePhone("089777444222");
        requestDto.setStreet("Jalan Kapten Tendean");
        // misal kelurahan singonegaran
        requestDto.setUrbanVillageId("3571031005");

        final SupplierDTO responseDto = supplierService.createSupplier(requestDto);
        assertNotNull(responseDto.getId());
        assertNotNull(responseDto.getCreatedAt());
        assertEquals("Supplier", responseDto.getName());
    }

    @Test
    @Order(2)
    void getSupplierById() throws SupplierNotFoundException {
        String id = "";
        final SupplierDTO responseDto = supplierService.getSupplierById(id);
        assertEquals(id, responseDto.getId());
    }

    @Test
    @Order(3)
    void getAllSuppliers() {
        Integer totalSampleData = 0;
        Integer pageNo = 0;
        Integer pageSize = 5;
        String sortBy = "name";
        String sortDir = "asc";

        ListSupplierRequestDTO requestDto = new ListSupplierRequestDTO();
        requestDto.setPageNo(pageNo);
        requestDto.setPageSize(pageSize);
        requestDto.setSortBy(sortBy);
        requestDto.setSortDir(sortDir);

        final ListSupplierResponseDTO responseDto = supplierService.getAllSuppliers(requestDto);
        assertEquals(totalSampleData, responseDto.getTotalElements().intValue());
        assertEquals(pageSize, responseDto.getSupplierDTOList().size());
    }

    @Test
    @Order(4)
    void updateSupplier() throws SupplierNotFoundException, UrbanVillageNotFoundException {
        String id = "";
        UpdateSupplierRequestDTO requestDto = new UpdateSupplierRequestDTO();
        requestDto.setName("Update");
        requestDto.setEmail("");
        requestDto.setGender(Gender.MALE);
        requestDto.setMobilePhone("089777444222");
        requestDto.setStreet("Jalan Kapten Tendean");
        // misal kita pindah kelurahan
        requestDto.setUrbanVillageId("3571031005");

        final SupplierDTO responseDto = supplierService.updateSupplier(id, requestDto);
        assertEquals(id, responseDto.getId());
        assertNotNull(responseDto.getUpdatedAt());
        assertNotEquals(responseDto.getCreatedAt(), responseDto.getUpdatedAt());
        assertEquals("", responseDto.getName());
    }

    @Test
    @Order(5)
    void deleteSupplier() throws SupplierNotFoundException {
        String id = "";
        supplierService.deleteSupplier(id);
        assertThrows(SupplierNotFoundException.class, () -> {
            final SupplierDTO supplier = supplierService.getSupplierById(id);
        });
    }

    @Test
    @Order(6)
    void getSupplierByName() throws SupplierNotFoundException {
        String name = "";
        final SupplierDTO supplier = supplierService.getSupplierByName(name);
        assertEquals(name, supplier.getName().toLowerCase());
    }

    @Test
    @Order(7)
    void getSupplierByEmail() throws SupplierNotFoundException {
        String email = "";
        final SupplierDTO supplier = supplierService.getSupplierByEmail(email);
        assertEquals(email, supplier.getEmail());
    }

    @Test
    @Order(8)
    void getSupplierByNameContains() {
        String name = "";
        int totalExpectedData = 0;
        final List<SupplierDTO> supplier = supplierService.getSupplierByNameContains(name);
        assertEquals(totalExpectedData, supplier.size());
    }

    @Test
    @Order(9)
    void getSupplierByProductsId() {
        String productId = "";
        int totalExpectedData = 0;
        final List<SupplierDTO> supplier = supplierService.getAllSuppliersByProductId(productId);
        assertEquals(totalExpectedData, supplier.size());
    }

    @Test
    @Order(10)
    void addProductToSupplier() throws SupplierNotFoundException, ProductNotFoundException {
        String productId = "";
        String supplierId = "";
        final SupplierDTO supplier = supplierService.addProductToSupplier(supplierId, productId);
        for (Product product : supplier.getProducts()) {
            if (product.getId().equals(productId)) {
            }
        }
    }
}