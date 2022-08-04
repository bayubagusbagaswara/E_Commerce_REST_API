package com.ecommerce.dto.supplier;

import com.ecommerce.dto.product.ProductDTO;
import com.ecommerce.entity.SupplierAddress;
import com.ecommerce.entity.enumerator.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDTO {

    private String id;

    private String name;

    private String email;

    private String mobilePhone;

    private Gender gender;

    private SupplierAddress supplierAddress;

    private Set<ProductDTO> productDTOSet = new HashSet<>();
}
