package com.restful.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequestDto {

    @NotBlank(message = "Name must not be blank")
    private String name;

    @NotNull(message = "Price can not null")
    @Size(min = 1, message = "Price must be minimum 1")
    private BigDecimal price;

    @NotNull(message = "Quantity can not null")
    @Size(min = 0, message = "Quantity must be minimum 0")
    private Integer quantity;

    @NotBlank(message = "Product Detail ID must not be blank")
    private String productDetailId;

    @NotBlank(message = "Category ID must not be blank")
    private String categoryId;
}
