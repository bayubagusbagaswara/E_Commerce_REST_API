package com.restful.mapper;

import com.restful.dto.product.ProductResponseDto;
import com.restful.dto.supplier.SupplierResponseDto;
import com.restful.entity.Product;
import com.restful.entity.Supplier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    private final CategoryMapper categoryMapper;
    private final ProductDetailMapper productDetailMapper;

    public ProductMapper(CategoryMapper categoryMapper, ProductDetailMapper productDetailMapper) {
        this.categoryMapper = categoryMapper;
        this.productDetailMapper = productDetailMapper;
    }

    public SupplierResponseDto supplierResponse(Supplier supplier) {
        SupplierResponseDto supplierResponse = new SupplierResponseDto();
        supplierResponse.setId(supplier.getId());
        supplierResponse.setName(supplier.getName());
        supplierResponse.setEmail(supplier.getEmail());
        supplierResponse.setGender(supplier.getGender());
        supplierResponse.setMobilePhone(supplier.getMobilePhone());
        supplierResponse.setAddress(supplier.getAddress());
        supplierResponse.setCreatedDate(supplier.getCreatedDate());
        supplierResponse.setUpdatedDate(supplier.getUpdatedDate());
        return supplierResponse;
    }

    public ProductResponseDto mapToProductResponse(Product product) {
        ProductResponseDto productResponse = new ProductResponseDto();
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

    public List<ProductResponseDto> mapToProductResponseList(List<Product> productList) {
        return productList.stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList())
                ;
    }
}
