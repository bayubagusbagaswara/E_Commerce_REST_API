package com.ecommerce.dto.region.kelurahan;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ListKelurahanResponseDto {

    private List<KelurahanResponseDto> kelurahanList;
    private Integer pageNo;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private boolean last;
}
