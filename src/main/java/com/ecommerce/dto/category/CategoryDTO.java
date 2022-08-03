package com.ecommerce.dto.category;

import com.ecommerce.dto.product.ProductDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CategoryDTO {

    private String id;
    private String name;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private List<ProductDTO> productList = new ArrayList<>();
}
