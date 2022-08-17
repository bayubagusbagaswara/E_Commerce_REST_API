package com.ecommerce.mapper;

import com.ecommerce.dto.product.ProductDTO;
import com.ecommerce.entity.product.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public ProductDTO fromProduct(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setCreatedAt(product.getCreatedAt());
        productDTO.setUpdatedAt(product.getUpdatedAt());
        productDTO.setProductDetail(product.getProductDetail());
        productDTO.setCategory(product.getCategory());
        productDTO.setSuppliers(product.getSuppliers());
        return productDTO;
    }

    public List<ProductDTO> fromProductList(List<Product> productList) {
        return productList.stream()
                .map(this::fromProduct)
                .collect(Collectors.toList());
    }
}
