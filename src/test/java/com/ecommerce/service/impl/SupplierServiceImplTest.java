package com.ecommerce.service.impl;

import com.ecommerce.dto.product.ProductDTO;
import com.ecommerce.dto.supplier.*;
import com.ecommerce.entity.enumerator.Gender;
import com.ecommerce.exception.KelurahanNotFoundException;
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
    void createSupplier() throws KelurahanNotFoundException {
        CreateSupplierRequestDTO requestDto = new CreateSupplierRequestDTO();
        requestDto.setName("Supplier");
        requestDto.setEmail("supplier@mail.com");
        requestDto.setGender(Gender.MALE);
        requestDto.setMobilePhone("089777444222");
        requestDto.setStreet("Jalan Kapten Tendean");
        requestDto.setPostalCode("64132");
        // misal kelurahan singonegaran
        requestDto.setKelurahanId("3571031005");

        final SupplierResponseDto responseDto = supplierService.createSupplier(requestDto);
        assertNotNull(responseDto.getId());
        assertNotNull(responseDto.getCreatedDate());
        assertEquals("Supplier", responseDto.getName());
    }

    @Test
    @Order(2)
    void getSupplierById() throws SupplierNotFoundException {
        String id = "";
        final SupplierResponseDto responseDto = supplierService.getSupplierById(id);
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

        ListSupplierRequestDto requestDto = new ListSupplierRequestDto();
        requestDto.setPageNo(pageNo);
        requestDto.setPageSize(pageSize);
        requestDto.setSortBy(sortBy);
        requestDto.setSortDir(sortDir);

        final ListSupplierResponseDto responseDto = supplierService.getAllSuppliers(requestDto);
        assertEquals(totalSampleData, responseDto.getTotalElements().intValue());
        assertEquals(pageSize, responseDto.getSupplierList().size());
    }

    @Test
    @Order(4)
    void updateSupplier() throws SupplierNotFoundException, KelurahanNotFoundException {
        String id = "";
        UpdateSupplierRequestDto requestDto = new UpdateSupplierRequestDto();
        requestDto.setName("Update");
        requestDto.setEmail("");
        requestDto.setGender(Gender.MALE);
        requestDto.setMobilePhone("089777444222");
        requestDto.setStreet("Jalan Kapten Tendean");
        requestDto.setPostalCode("64132");
        // misal kita pindah kelurahan
        requestDto.setKelurahanId("3571031005");

        final SupplierResponseDto responseDto = supplierService.updateSupplier(id, requestDto);
        assertEquals(id, responseDto.getId());
        assertNotNull(responseDto.getUpdatedDate());
        assertNotEquals(responseDto.getCreatedDate(), responseDto.getUpdatedDate());
        assertEquals("", responseDto.getName());
    }

    @Test
    @Order(5)
    void deleteSupplier() throws SupplierNotFoundException {
        String id = "";
        supplierService.deleteSupplier(id);
        assertThrows(SupplierNotFoundException.class, () -> {
            final SupplierResponseDto supplier = supplierService.getSupplierById(id);
        });
    }

    @Test
    @Order(6)
    void getSupplierByName() throws SupplierNotFoundException {
        String name = "";
        final SupplierResponseDto supplier = supplierService.getSupplierByName(name);
        assertEquals(name, supplier.getName().toLowerCase());
    }

    @Test
    @Order(7)
    void getSupplierByEmail() throws SupplierNotFoundException {
        String email = "";
        final SupplierResponseDto supplier = supplierService.getSupplierByEmail(email);
        assertEquals(email, supplier.getEmail());
    }

    @Test
    @Order(8)
    void getSupplierByNameContains() {
        String name = "";
        int totalExpectedData = 0;
        final List<SupplierResponseDto> supplier = supplierService.getSupplierByNameContains(name);
        assertEquals(totalExpectedData, supplier.size());
    }

    @Test
    @Order(9)
    void getSupplierByProductsId() {
        String productId = "";
        int totalExpectedData = 0;
        final List<SupplierResponseDto> supplier = supplierService.getSupplierByProductsId(productId);
        assertEquals(totalExpectedData, supplier.size());
    }

    @Test
    @Order(10)
    void addProductToSupplier() throws SupplierNotFoundException, ProductNotFoundException {
        String productId = "";
        String supplierId = "";
        final SupplierResponseDto supplier = supplierService.addProductToSupplier(supplierId, productId);
        for (ProductDTO product : supplier.getProducts()) {
            if (product.getId().equals(productId)) {
            }
        }
    }
}