package com.restful.dto.product;

import com.restful.entity.Category;
import com.restful.entity.ProductDetail;
import com.restful.entity.Supplier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    // ini apa diganti dengan ProductDetailResponseDto
    private ProductDetail productDetail;

    // ini apa diganti dengan CategoryResponseDto
    private Category category;

    // ini apa diganti dengan SupplierResponseDto
    private Set<Supplier> suppliers;
}
