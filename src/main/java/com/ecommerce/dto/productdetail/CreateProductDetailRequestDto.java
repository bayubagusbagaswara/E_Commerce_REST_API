package com.ecommerce.dto.productdetail;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductDetailRequestDto {

    @NotBlank(message = "SKU must not be blank")
    @Size(max = 50, message = "SKU maximum length is 50 characters")
    private String sku;

    @NotBlank(message = "Description must not be blank")
    @Size(max = 500, message = "Description maximum length is 500 characters")
    private String description;
}
