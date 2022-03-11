package com.restful.dto.kelurahan;

import com.restful.dto.kecamatan.KecamatanResponseDto;
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
    private KecamatanResponseDto kecamatanResponse;
}
