package com.ecommerce.mapper;

import com.ecommerce.dto.product.ProductDTO;
import com.ecommerce.dto.supplier.SupplierDTO;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.Supplier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public SupplierDTO fromEntity(Supplier supplier) {
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setId(supplier.getId());
        supplierDTO.setName(supplier.getName());
        supplierDTO.setEmail(supplier.getEmail());
        supplierDTO.setGender(supplier.getGender());
        supplierDTO.setMobilePhone(supplier.getMobilePhone());
        supplierDTO.setSupplierAddress(supplier.getSupplierAddress());
        supplierDTO.setCreatedAt(supplier.getCreatedAt());
        supplierDTO.setProductSet(supplier.getProducts());
        return supplierDTO;
    }

    public ProductDTO fromEntity(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setCreatedAt(product.getCreatedAt());
        productDTO.setProductDetail(product.getProductDetail());
        productDTO.setCategory(product.getCategory());
        productDTO.setSuppliers(product.getSuppliers());
        return productDTO;
    }

    public List<ProductDTO> mapToProductResponseList(List<Product> productList) {
        return productList.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList())
                ;
    }
}
