package com.ecommerce.dto.supplier;

import com.ecommerce.entity.Product;
import com.ecommerce.entity.SupplierAddress;
import com.ecommerce.entity.enumerator.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
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

    private Instant createdAt;

    private Instant updatedAt;

    private SupplierAddress supplierAddress;

    private Set<Product> products = new HashSet<>();
}
