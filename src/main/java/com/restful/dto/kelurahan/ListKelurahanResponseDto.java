package com.restful.dto.kelurahan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListKelurahanResponseDto {

    private List<KelurahanResponseDto> kelurahanResponses;
    private Integer pageNo;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private boolean last;
}
