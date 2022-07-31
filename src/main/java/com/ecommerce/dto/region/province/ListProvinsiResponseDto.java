package com.ecommerce.dto.region.province;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ListProvinsiResponseDto {

    private List<ProvinsiResponseDto> provinsiList;
    private Integer pageNo;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private boolean last;
}
