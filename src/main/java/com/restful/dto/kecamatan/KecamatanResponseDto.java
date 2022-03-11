package com.restful.dto.kecamatan;

import com.restful.dto.kota.KotaResponseDto;
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
    private KotaResponseDto kotaResponseDto;
}
