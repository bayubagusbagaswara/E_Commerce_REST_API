package com.restful.dto.product;


import com.restful.dto.category.CategoryResponseDto;
import com.restful.dto.productdetail.ProductDetailResponseDto;
import com.restful.dto.supplier.SupplierResponseDto;
import com.restful.entity.Category;
import com.restful.entity.ProductDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {

    private String id;
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ProductDetailResponseDto productDetail;
    private CategoryResponseDto category;
    private Set<SupplierResponseDto> suppliers = new HashSet<>();
}
