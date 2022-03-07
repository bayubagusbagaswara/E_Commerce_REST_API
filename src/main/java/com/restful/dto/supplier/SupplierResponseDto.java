package com.restful.dto.supplier;

import com.restful.entity.Address;
import com.restful.entity.Product;
import com.restful.entity.enumerator.Gender;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SupplierResponseDto {

    private String id;

    private String name;

    private String email;

    private String mobilePhone;

    private String gender;

    // kita ganti dengan AddressResponseDto
    private Address address;

    // kita ganti denga ProductResponseDto
    private Set<Product> products = new HashSet<>();
}
