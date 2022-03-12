package com.restful.dto.supplier;

import com.restful.dto.product.ProductResponseDto;
import com.restful.entity.Address;
import com.restful.entity.enumerator.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class SupplierResponseDto {

    private String id;

    private String name;

    private String email;

    private String mobilePhone;

    private Gender gender;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private Address address;

    private Set<ProductResponseDto> products = new HashSet<>();
}
