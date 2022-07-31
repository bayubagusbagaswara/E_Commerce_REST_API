package com.restful.dto.region.kota;

import com.restful.dto.region.provinsi.ProvinsiResponseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class KotaResponseDto {

    private String id;
    private String code;
    private String name;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private ProvinsiResponseDto provinsi;
}
