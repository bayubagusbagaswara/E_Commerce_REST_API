package com.ecommerce.dto.region.kelurahan;

import com.ecommerce.dto.region.subDistrict.KecamatanResponseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class KelurahanResponseDto {

    private String id;
    private String code;
    private String name;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private KecamatanResponseDto kecamatan;
}
