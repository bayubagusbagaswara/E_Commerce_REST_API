package com.ecommerce.dto.region.province;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateProvinceRequestDTO {

    @NotBlank(message = "Province code must not be blank")
    private String code;

    @NotBlank(message = "Province name must not be blank")
    private String name;
}
