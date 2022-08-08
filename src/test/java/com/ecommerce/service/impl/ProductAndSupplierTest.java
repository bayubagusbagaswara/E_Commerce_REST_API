package com.ecommerce.service.impl;

import com.ecommerce.dto.product.ProductDTO;
import com.ecommerce.dto.supplier.SupplierDTO;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.Supplier;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Sql(scripts = {
        "classpath:/sql/delete-data-product-supplier.sql",
        "classpath:/sql/delete-data-supplier.sql",
        "classpath:/sql/delete-data-address.sql",
        "classpath:/sql/delete-data-kelurahan.sql",
        "classpath:/sql/delete-data-kecamatan.sql",
        "classpath:/sql/delete-data-kota.sql",
        "classpath:/sql/delete-data-provinsi.sql",
        "classpath:/sql/delete-data-product.sql",
        "classpath:/sql/delete-data-product-detail.sql",
        "classpath:/sql/delete-data-category.sql",
        "classpath:/sql/sample-data-category.sql",
        "classpath:/sql/sample-data-product-detail.sql",
        "classpath:/sql/sample-data-product.sql",
        "classpath:/sql/sample-data-provinsi.sql",
        "classpath:/sql/sample-data-kota.sql",
        "classpath:/sql/sample-data-kecamatan.sql",
        "classpath:/sql/sample-data-kelurahan.sql",
        "classpath:/sql/sample-data-address.sql",
        "classpath:/sql/sample-data-supplier.sql",
        "classpath:/sql/sample-data-products-suppliers.sql"
})
public class ProductAndSupplierTest {

    // Skenario 1 : test jika product dihapus, maka product tersebut sudah tidak punya relasi dengan supplier
    // Skenario 2 : test jika supplier dihapus, maka supplier tersebut sudah tidak punya relasi dengan product
    // kita cek dalam table bridging nya
    // Skenario 3 : kita coba ubah Cascade nya satu persatu

    private final static Logger log = LoggerFactory.getLogger(ProductServiceImplTest.class);

    @Autowired
    ProductServiceImpl productService;

    @Autowired
    SupplierServiceImpl supplierService;

    @Test
    @Order(1)
    void testDeleteProduct() throws ProductNotFoundException, SupplierNotFoundException {
        // product macbook-pro-2021 memiliki supplier [bayu, albert, tesla]
        String productId = "macbook";
        productService.deleteProduct(productId);
        assertThrows(ProductNotFoundException.class, () -> {
            final ProductDTO product = productService.getProductById(productId);
        });

        // cek apakah supplier masih punya relasi dengan product yang sudah terhapus
        // harusnya sudah tidak punya relasi (alias null)
        // misal kita cari supplier bayu yang memiliki product [macbook, redmibook, ipad]
        // jadi setelah kita hapus product macbook, hanya hanya tinggal berelasi dengan redmibook dan ipad
        // sehingga total product nya tinggal 2
        String supplierIdBayu = "bayu";
        final SupplierDTO supplier = supplierService.getSupplierById(supplierIdBayu);
        assertEquals(2, supplier.getProducts().size());
        for (Product product : supplier.getProducts()) {
            log.info("Product: {}", product.getId());
            log.info("======");
        }

        // supplier tesla [legion, macbook]
        String supplierIdTesla = "tesla";
        final SupplierDTO supplier1 = supplierService.getSupplierById(supplierIdTesla);
        assertEquals(1, supplier1.getProducts().size());
        for (Product product : supplier1.getProducts()) {
            assertEquals("legion", product.getId());
        }

        // supplier albert [iphone, canon, macbook]
        String supplierIdAlbert = "albert";
        final SupplierDTO supplier2 = supplierService.getSupplierById(supplierIdAlbert);
        assertEquals(2, supplier2.getProducts().size());
    }

    @Test
    @Order(2)
    void testDeleteSupplier() throws SupplierNotFoundException, ProductNotFoundException {
        // albert punya prouct [iphone, canon, macbook]
        String supplierId = "albert";
        supplierService.deleteSupplier(supplierId);
        assertThrows(SupplierNotFoundException.class, () -> {
            final SupplierDTO supplier = supplierService.getSupplierById(supplierId);
        });
        // cek bahwa product sudah tidak memiliki relasi dengan supplier

        // product macbook [bayu, albert, tesla]
        String productIdMacbook = "macbook";
        final ProductDTO product = productService.getProductById(productIdMacbook);
        assertEquals(2, product.getSuppliers().size());

        // product canon [bagus, newton, albert]
        String productIdCanon = "canon";
        final ProductDTO product1 = productService.getProductById(productIdCanon);
        assertEquals(2, product1.getSuppliers().size());

        // product iphone [albert, newton]
        String productIdIphone = "iphone";
        final ProductDTO product2 = productService.getProductById(productIdIphone);
        assertEquals(1, product2.getSuppliers().size());
        for (Supplier supplier : product2.getSuppliers()) {
            assertEquals("newton", supplier.getId());
        }
    }
}
