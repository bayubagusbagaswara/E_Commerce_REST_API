package com.ecommerce.mapper;

import com.ecommerce.dto.productdetail.ProductDetailResponseDto;
import com.ecommerce.entity.ProductDetail;
import org.springframework.stereotype.Component;

@Component
public class ProductDetailMapper {

    public ProductDetailResponseDto mapToProductDetailResponse(ProductDetail productDetail) {
        ProductDetailResponseDto productDetailResponse = new ProductDetailResponseDto();
        productDetailResponse.setId(productDetail.getId());
        productDetailResponse.setSku(productDetail.getSku());
        productDetailResponse.setDescription(productDetail.getDescription());
//        productDetailResponse.setCreatedAt(productDetail.getCreatedAt());
//        productDetailResponse.setUpdatedAt(productDetail.getUpdatedDate());
        return productDetailResponse;
    }
}
