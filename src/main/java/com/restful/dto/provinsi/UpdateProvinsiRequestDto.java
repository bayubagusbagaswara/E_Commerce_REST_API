package com.restful.dto.provinsi;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProvinsiRequestDto {

    @NotBlank(message = "Provinsi name must not be blank")
    private String name;

    @NotBlank(message = "Provinsi code must not be blank")
    private String code;
}
