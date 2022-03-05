package com.restful.dto.kelurahan;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateKelurahanRequestDto {

    @NotBlank(message = "Name must not be blank")
    private String name;

    @NotBlank(message = "Code must not be blank")
    private String code;

    @NotBlank(message = "Kecamatan ID must not be blank")
    private String kecamatanId;
}
