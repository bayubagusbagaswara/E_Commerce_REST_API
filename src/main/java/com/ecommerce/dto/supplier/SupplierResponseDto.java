package com.ecommerce.dto.supplier;

import com.ecommerce.dto.product.ProductResponseDto;
import com.ecommerce.entity.SupplierAddress;
import com.ecommerce.entity.enumerator.Gender;
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

    private SupplierAddress supplierAddress;

    private Set<ProductResponseDto> products = new HashSet<>();
}
