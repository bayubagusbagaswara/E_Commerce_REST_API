package com.ecommerce.mapper;

import com.ecommerce.dto.supplier.SupplierDTO;
import com.ecommerce.entity.Supplier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SupplierMapper {

    public SupplierDTO fromSupplier(Supplier supplier) {
        SupplierDTO supplierResponse = new SupplierDTO();
        supplierResponse.setId(supplier.getId());
        supplierResponse.setName(supplier.getName());
        supplierResponse.setEmail(supplier.getEmail());
        supplierResponse.setMobilePhone(supplier.getMobilePhone());
        supplierResponse.setGender(supplier.getGender());
        supplierResponse.setSupplierAddress(supplier.getSupplierAddress());
        supplierResponse.setProducts(supplier.getProducts());
        supplierResponse.setCreatedAt(supplier.getCreatedAt());
        return supplierResponse;
    }

    public List<SupplierDTO> fromSupplierList(List<Supplier> supplierList) {
        return supplierList.stream()
                .map(this::fromSupplier)
                .collect(Collectors.toList());
    }
}
