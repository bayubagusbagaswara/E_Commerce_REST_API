package com.ecommerce.dto.region.subDistrict;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ListKecamatanResponseDto {

    private List<SubDistrictDTO> kecamatanList;
    private Integer pageNo;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private boolean last;
}
