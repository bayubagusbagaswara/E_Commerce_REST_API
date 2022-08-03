package com.ecommerce.dto.region.district;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ListDistrictResponseDTO {

    private List<DistrictDTO> kotaList;
    private Integer pageNo;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private boolean last;
}
