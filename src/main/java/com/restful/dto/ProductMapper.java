package com.restful.dto;

import com.restful.dto.product.ProductResponseDto;
import com.restful.dto.supplier.SupplierResponseDto;
import com.restful.entity.Product;
import com.restful.entity.Supplier;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class ProductMapper {

    private final CategoryMapper categoryMapper;
    private final ProductDetailMapper productDetailMapper;

    public ProductMapper(CategoryMapper categoryMapper, ProductDetailMapper productDetailMapper) {
        this.categoryMapper = categoryMapper;
        this.productDetailMapper = productDetailMapper;
    }

    // tanpa ada data product
    public SupplierResponseDto supplierResponseDto(Supplier supplier) {
        SupplierResponseDto supplierResponseDto = new SupplierResponseDto();
        supplierResponseDto.setId(supplier.getId());
        supplierResponseDto.setName(supplier.getName());
        supplierResponseDto.setEmail(supplier.getEmail());
        supplierResponseDto.setGender(supplier.getGender());
        supplierResponseDto.setMobilePhone(supplier.getMobilePhone());
        supplierResponseDto.setAddress(supplier.getAddress());
        supplierResponseDto.setCreatedAt(supplier.getCreatedAt());
        supplierResponseDto.setUpdatedAt(supplier.getUpdatedAt());
        return supplierResponseDto;
    }

    // disini dengan supplier
    public ProductResponseDto mapProductToProductResponseDto(Product product) {
        ProductResponseDto productDto = new ProductResponseDto();
        productDto.setId(productDto.getId());
        productDto.setName(productDto.getName());
        productDto.setPrice(product.getPrice());
        productDto.setQuantity(product.getQuantity());
        productDto.setProductDetail(productDetailMapper.mapProductDetailToProductDetailResponseDto(product.getProductDetail()));
        productDto.setCategory(categoryMapper.mapCategoryToCategoryResponseDto(product.getCategory()));
        productDto.setSuppliers(product.getSuppliers().stream()
                .map(this::supplierResponseDto)
                .collect(Collectors.toSet()));
        productDto.setCreatedAt(product.getCreatedAt());
        productDto.setUpdatedAt(product.getUpdatedAt());
        return productDto;
    }

    public List<ProductResponseDto> mapProductListToProductResponseDtoList(List<Product> productList) {
        return productList.stream()
                .map(this::mapProductToProductResponseDto)
                .collect(Collectors.toList())
                ;
    }
}
