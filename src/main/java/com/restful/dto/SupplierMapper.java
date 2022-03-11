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

    // tanpa supplier
    public ProductResponseDto productResponseDto(Product product) {
        ProductResponseDto productDto = new ProductResponseDto();
        productDto.setId(productDto.getId());
        productDto.setName(productDto.getName());
        productDto.setPrice(product.getPrice());
        productDto.setQuantity(product.getQuantity());
        productDto.setProductDetail(productDetailMapper.mapProductDetailToProductDetailResponseDto(product.getProductDetail()));
        productDto.setCategory(categoryMapper.mapCategoryToCategoryResponseDto(product.getCategory()));
        productDto.setCreatedAt(product.getCreatedAt());
        productDto.setUpdatedAt(product.getUpdatedAt());
        return productDto;
    }

    // disini ada product
    public SupplierResponseDto mapSupplierToSupplierResponseDto(Supplier supplier) {
        SupplierResponseDto dto = new SupplierResponseDto();
        dto.setId(supplier.getId());
        dto.setName(supplier.getName());
        dto.setEmail(supplier.getEmail());
        dto.setCreatedAt(supplier.getCreatedAt());
        dto.setUpdatedAt(supplier.getUpdatedAt());
        dto.setProducts(supplier.getProducts()
                .stream()
                .map(this::productResponseDto)
                .collect(Collectors.toSet()));
        return dto;
    }

    public List<SupplierResponseDto> mapSupplierListToSupplierResponseDtoList(List<Supplier> supplierList) {
        return supplierList.stream()
                .map(this::mapSupplierToSupplierResponseDto)
                .collect(Collectors.toList())
                ;
    }
}
