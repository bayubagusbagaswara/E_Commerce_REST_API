package com.restful.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductRequestDto {

    private String name;
    private BigDecimal price;
    private Integer quantity;
    private String productDetailId;
    private String categoryId;
}
