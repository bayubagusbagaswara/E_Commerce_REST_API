package com.ecommerce.dto.region.province;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListProvinceRequestDTO {

    @NotNull(message = "Page index can not null")
    @Min(value = 0, message = "Page index must not be less than zero")
    private Integer pageNo;

    @NotNull(message = "Page size can not null")
    @Min(value = 1, message = "Page size must not be less than one")
    private Integer pageSize;

    @NotBlank(message = "Sort by certain data required")
    private String sortBy;

    @NotBlank(message = "Sort direction must be asc or desc and required")
    private String sortDir;
}
