package com.restful.dto;

import com.restful.dto.productdetail.ProductDetailResponseDto;
import com.restful.entity.ProductDetail;
import org.springframework.stereotype.Component;

@Component
public class ProductDetailMapper {

    public ProductDetailResponseDto mapToProductDetailResponse(ProductDetail productDetail) {
        ProductDetailResponseDto productDetailResponse = new ProductDetailResponseDto();
        productDetailResponse.setId(productDetail.getId());
        productDetailResponse.setSku(productDetail.getSku());
        productDetailResponse.setDescription(productDetail.getDescription());
        productDetailResponse.setCreatedAt(productDetail.getCreatedDate());
        productDetailResponse.setUpdatedAt(productDetail.getUpdatedDate());
        return productDetailResponse;
    }
}
