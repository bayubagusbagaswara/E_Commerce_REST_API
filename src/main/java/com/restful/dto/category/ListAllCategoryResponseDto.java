package com.restful.dto.category;

import com.restful.dto.product.ProductResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListAllCategoryResponseDto {

    private List<CategoryResponseDto> categoryResponseDtoList;

    private Integer pageNo;

    private Integer pageSize;

    private Long totalElements;

    private Integer totalPages;

    private boolean last;
}
