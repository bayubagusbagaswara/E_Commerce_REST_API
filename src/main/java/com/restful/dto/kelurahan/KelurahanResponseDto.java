package com.restful.dto.kelurahan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KelurahanResponseDto {

    private String id;

    private String code;
    private String name;

    private String postalCode;

//    private KecamatanResponseDto kecamatanResponseDto;

    private LocalDateTime createdAt;
    private LocalDate updatedAt;
}
