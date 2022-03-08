package com.restful.dto.supplier;

import com.restful.dto.address.AddressResponseDto;
import com.restful.dto.product.ProductResponseDto;
import com.restful.entity.Address;
import com.restful.entity.Product;
import com.restful.entity.enumerator.Gender;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Address address;

    // dan ini juga harusnya hasilnya dari findAllProductsBySupplierId dari ProductRepository
    private Set<ProductResponseDto> products = new HashSet<>();
//    private Set<Product> products = new HashSet<>();
}
