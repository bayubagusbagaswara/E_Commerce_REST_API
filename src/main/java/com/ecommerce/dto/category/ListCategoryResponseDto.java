package com.ecommerce.dto.category;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ListCategoryResponseDto {

    private List<CategoryDTO> categoryResponseList;
    private Integer pageNo;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private boolean last;
}
