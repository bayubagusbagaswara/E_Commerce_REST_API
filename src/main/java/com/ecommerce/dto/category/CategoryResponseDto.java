package com.ecommerce.dto.category;

import com.ecommerce.dto.product.ProductResponseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CategoryResponseDto {

    private String id;
    private String name;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private List<ProductResponseDto> productList = new ArrayList<>();
}
