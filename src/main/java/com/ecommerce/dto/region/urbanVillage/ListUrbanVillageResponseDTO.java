package com.ecommerce.dto.region.urbanVillage;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ListUrbanVillageResponseDTO {

    private List<UrbanVillageDTO> urbanVillageDTOList;
    private Integer pageNo;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private boolean last;
}
