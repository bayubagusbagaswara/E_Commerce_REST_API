package com.restful.dto.kecamatan;

import com.restful.dto.kota.KotaResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KecamatanResponseDto {

    private String id;
    private String name;
    private String code;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private KotaResponseDto kotaResponseDto;
}
