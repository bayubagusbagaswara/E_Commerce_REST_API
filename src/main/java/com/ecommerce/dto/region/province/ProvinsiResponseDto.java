package com.ecommerce.dto.region.province;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ProvinsiResponseDto {

    private String id;
    private String code;
    private String name;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
