package com.restful.dto.kelurahan;

import com.restful.dto.kecamatan.KecamatanResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KelurahanResponseDto {

    private String id;
    private String name;
    private String code;
    private String postalCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private KecamatanResponseDto kecamatanResponseDto;
}
