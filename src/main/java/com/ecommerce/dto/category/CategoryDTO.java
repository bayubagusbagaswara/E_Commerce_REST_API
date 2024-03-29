package com.ecommerce.dto.category;

import com.ecommerce.entity.product.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CategoryDTO {

    private String id;
    private String name;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;
    private List<Product> productList = new ArrayList<>();
}
