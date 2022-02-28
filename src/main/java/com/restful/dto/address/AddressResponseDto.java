package com.restful.dto.address;

import com.restful.dto.kelurahan.KelurahanResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponseDto {

    private String id;

    private String street;

    private KelurahanResponseDto kelurahanResponseDto;
}
