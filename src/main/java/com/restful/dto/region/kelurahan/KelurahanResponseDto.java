package com.restful.dto.region.kelurahan;

import com.restful.dto.region.kecamatan.KecamatanResponseDto;
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
