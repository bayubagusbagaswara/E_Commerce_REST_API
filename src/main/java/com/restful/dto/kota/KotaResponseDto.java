package com.restful.dto.kota;

import com.restful.dto.provinsi.ProvinsiResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KotaResponseDto {

    private String id;
    private String name;
    private String code;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ProvinsiResponseDto provinsiResponseDto;
}
