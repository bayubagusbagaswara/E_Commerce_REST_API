package com.ecommerce.dto.category;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateCategoryRequestDto {

    @NotBlank(message = "Name must not be blank")
    @Size(max = 50, message = "Name length maximum is 50 characters")
    private String name;

    @NotBlank(message = "Description must not be blank")
    @Max(value = 500, message = "Description length maximum is 500 characters")
    private String description;
}
