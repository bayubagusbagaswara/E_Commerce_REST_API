package com.restful.dto.provinsi;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateProvinsiRequestDto {

    @NotBlank(message = "Provinsi Code must not be blank")
    private String code;

    @NotBlank(message = "Provinsi Name must not be blank")
    private String name;
}
