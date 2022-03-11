package com.restful.dto;

import com.restful.dto.productdetail.ProductDetailResponseDto;
import com.restful.entity.ProductDetail;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductDetailMapper {

    public ProductDetailResponseDto mapProductDetailToProductDetailResponseDto(ProductDetail productDetail) {
        ProductDetailResponseDto productDetailDto = new ProductDetailResponseDto();
        productDetailDto.setId(productDetail.getId());
        productDetailDto.setSku(productDetail.getSku());
        productDetailDto.setDescription(productDetail.getDescription());
        productDetailDto.setCreatedAt(productDetail.getCreatedAt());
        productDetailDto.setUpdatedAt(productDetail.getUpdatedAt());
        return productDetailDto;
    }
}
