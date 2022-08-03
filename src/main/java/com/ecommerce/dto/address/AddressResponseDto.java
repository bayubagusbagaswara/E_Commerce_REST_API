package com.ecommerce.dto.address;

import com.ecommerce.dto.region.urbanVillage.UrbanVillageDTO;
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

    private UrbanVillageDTO urbanVillageDTO;
}
