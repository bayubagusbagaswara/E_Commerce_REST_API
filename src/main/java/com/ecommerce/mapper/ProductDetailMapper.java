package com.ecommerce.mapper;

import com.ecommerce.dto.productdetail.ProductDetailDTO;
import com.ecommerce.entity.ProductDetail;
import org.springframework.stereotype.Component;

@Component
public class ProductDetailMapper {

    public ProductDetailDTO mapToProductDetailResponse(ProductDetail productDetail) {
        ProductDetailDTO productDetailResponse = new ProductDetailDTO();
        productDetailResponse.setId(productDetail.getId());
        productDetailResponse.setSku(productDetail.getSku());
        productDetailResponse.setDescription(productDetail.getDescription());
//        productDetailResponse.setCreatedAt(productDetail.getCreatedAt());
//        productDetailResponse.setUpdatedAt(productDetail.getUpdatedDate());
        return productDetailResponse;
    }
}
