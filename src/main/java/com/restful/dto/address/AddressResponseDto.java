package com.restful.dto.address;

import com.restful.dto.region.kelurahan.KelurahanResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponseDto {

    private String id;

    private String street;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private KelurahanResponseDto kelurahanResponseDto;
}
