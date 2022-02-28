package com.restful.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListAllProductResponseDto {

    private List<ProductResponseDto> productResponseDtoList;

    private Integer pageNo;

    private Integer pageSize;

    private Long totalElements;

    private Integer totalPages;

    private boolean last;
}
