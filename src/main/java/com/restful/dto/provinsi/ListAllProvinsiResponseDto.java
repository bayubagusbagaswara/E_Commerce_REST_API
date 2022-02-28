package com.restful.dto.provinsi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListAllProvinsiResponseDto {

    private List<ProvinsiResponseDto> provinsiResponseDtoList;

    private Integer pageNo;

    private Integer pageSize;

    private Long totalElements;

    private Integer totalPages;

    private boolean last;
}
