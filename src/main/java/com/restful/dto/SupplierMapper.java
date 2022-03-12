package com.restful.dto;

import com.restful.dto.product.ProductResponseDto;
import com.restful.dto.supplier.SupplierResponseDto;
import com.restful.entity.Product;
import com.restful.entity.Supplier;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class SupplierMapper {

    private final CategoryMapper categoryMapper;
    private final ProductDetailMapper productDetailMapper;

    public SupplierMapper(CategoryMapper categoryMapper, ProductDetailMapper productDetailMapper) {
        this.categoryMapper = categoryMapper;
        this.productDetailMapper = productDetailMapper;
    }

    public ProductResponseDto productResponse(Product product) {
        ProductResponseDto productResponse = new ProductResponseDto();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        productResponse.setQuantity(product.getQuantity());
        productResponse.setProductDetail(productDetailMapper.mapToProductDetailResponse(product.getProductDetail()));
        productResponse.setCategory(categoryMapper.mapToCategoryResponse(product.getCategory()));
        productResponse.setCreatedDate(product.getCreatedDate());
        productResponse.setUpdatedDate(product.getUpdatedDate());
        return productResponse;
    }

    public SupplierResponseDto mapToSupplierResponse(Supplier supplier) {
        SupplierResponseDto supplierResponse = new SupplierResponseDto();
        supplierResponse.setId(supplier.getId());
        supplierResponse.setName(supplier.getName());
        supplierResponse.setEmail(supplier.getEmail());
        supplierResponse.setMobilePhone(supplier.getMobilePhone());
        supplierResponse.setGender(supplier.getGender());
        supplierResponse.setAddress(supplier.getAddress());
        supplierResponse.setProducts(supplier.getProducts()
                .stream()
                .map(this::productResponse)
                .collect(Collectors.toSet()));
        supplierResponse.setCreatedDate(supplier.getCreatedDate());
        supplierResponse.setUpdatedDate(supplier.getUpdatedDate());
        return supplierResponse;
    }

    public List<SupplierResponseDto> mapToSupplierResponseList(List<Supplier> supplierList) {
        return supplierList.stream()
                .map(this::mapToSupplierResponse)
                .collect(Collectors.toList())
                ;
    }
}
