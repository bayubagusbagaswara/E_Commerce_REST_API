package com.ecommerce.dto.product;

import com.ecommerce.entity.Category;
import com.ecommerce.entity.product.ProductDetail;
import com.ecommerce.entity.Supplier;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class ProductDTO {

    private String id;
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private Instant createdAt;
    private Instant updatedAt;
    private ProductDetail productDetail;
    private Category category;
    private Set<Supplier> suppliers = new HashSet<>();
}
