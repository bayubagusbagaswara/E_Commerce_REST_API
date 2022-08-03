package com.ecommerce.dto.productdetail;

import com.ecommerce.dto.product.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailDTO {

    private String sku;
    private String description;
    private ProductDTO productDTO;
}
