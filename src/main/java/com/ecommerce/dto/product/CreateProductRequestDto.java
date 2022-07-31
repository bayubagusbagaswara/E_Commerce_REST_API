package com.ecommerce.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CreateProductRequestDto {

    @NotBlank(message = "Product name must not be blank")
    private String name;

    @NotBlank(message = "Product SKU must not be blank")
    @Size(max = 50, message = "Product SKU maximum length is 50 characters")
    private String sku;

    @NotNull(message = "Product price can not null")
    @Size(min = 1, message = "Product price must be minimum 1")
    private BigDecimal price;

    @NotNull(message = "Product quantity can not null")
    @Size(min = 0, message = "Product quantity must be minimum 0")
    private Integer quantity;

    @NotBlank(message = "Product description must not be blank")
    @Size(max = 500, message = "Product description maximum length is 500 characters")
    private String description;

    @NotBlank(message = "Category ID must not be blank")
    private String categoryId;
}
