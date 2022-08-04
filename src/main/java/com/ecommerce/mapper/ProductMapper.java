package com.ecommerce.mapper;

import com.ecommerce.dto.product.ProductDTO;
import com.ecommerce.dto.supplier.SupplierResponseDto;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.Supplier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    private final CategoryMapper categoryMapper;


    public ProductMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public SupplierResponseDto supplierResponse(Supplier supplier) {
        SupplierResponseDto supplierResponse = new SupplierResponseDto();
        supplierResponse.setId(supplier.getId());
        supplierResponse.setName(supplier.getName());
        supplierResponse.setEmail(supplier.getEmail());
        supplierResponse.setGender(supplier.getGender());
        supplierResponse.setMobilePhone(supplier.getMobilePhone());
        supplierResponse.setSupplierAddress(supplier.getSupplierAddress());
        supplierResponse.setCreatedDate(supplier.getCreatedDate());
        supplierResponse.setUpdatedDate(supplier.getUpdatedDate());
        return supplierResponse;
    }

    public ProductDTO mapToProductResponse(Product product) {
        ProductDTO productResponse = new ProductDTO();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        productResponse.setQuantity(product.getQuantity());
        productResponse.setCreatedDate(product.getCreatedDate());
        productResponse.setUpdatedDate(product.getUpdatedDate());
        productResponse.setProductDetail(productDetailMapper.mapToProductDetailResponse(product.getProductDetail()));
        productResponse.setCategory(categoryMapper.mapToCategoryResponse(product.getCategory()));
        productResponse.setSuppliers(product.getSuppliers().stream()
                .map(this::supplierResponse)
                .collect(Collectors.toSet()));
        return productResponse;
    }

    public List<ProductDTO> mapToProductResponseList(List<Product> productList) {
        return productList.stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList())
                ;
    }
}
