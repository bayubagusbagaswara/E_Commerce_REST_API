package com.ecommerce.dto.productdetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailResponseDto {

    private String id;
    private String sku;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
