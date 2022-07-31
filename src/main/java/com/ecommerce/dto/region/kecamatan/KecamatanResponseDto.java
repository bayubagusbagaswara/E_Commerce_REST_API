package com.ecommerce.dto.region.kecamatan;

import com.ecommerce.dto.region.kota.KotaResponseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class KecamatanResponseDto {

    private String id;
    private String code;
    private String name;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private KotaResponseDto kota;
}
