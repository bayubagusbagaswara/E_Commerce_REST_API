package com.ecommerce.dto.product;

import com.ecommerce.dto.category.CategoryDTO;
import com.ecommerce.dto.supplier.SupplierDTO;
import com.ecommerce.entity.ProductDetail;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class ProductDTO {

    private String id;
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private LocalDateTime createdDate;
    private ProductDetail productDetail;
    private CategoryDTO category;
    private Set<SupplierDTO> suppliers = new HashSet<>();
}
