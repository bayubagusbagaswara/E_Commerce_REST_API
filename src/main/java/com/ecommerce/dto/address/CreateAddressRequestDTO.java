package com.ecommerce.dto.address;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAddressRequestDTO {

    @NotBlank(message = "Street must not be blank")
    private String street;

    @NotBlank(message = "UrbanVillage ID must not be blank")
    private String urbanVillageId;
}
