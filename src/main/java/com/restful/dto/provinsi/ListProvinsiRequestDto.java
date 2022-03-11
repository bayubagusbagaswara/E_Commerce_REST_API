package com.restful.dto.provinsi;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ListProvinsiRequestDto {

    @NotNull(message = "Page index can not null")
    @Min(value = 0, message = "Page Index must not be less than zero")
    private Integer pageNo;

    @NotNull(message = "Page size can not null")
    @Min(value = 1, message = "Page Size must not be less than one")
    private Integer pageSize;

    @NotBlank(message = "Sort by field must not be blank")
    private String sortBy;

    @NotBlank(message = "Sort direction must be asc or desc")
    private String sortDir;
}
