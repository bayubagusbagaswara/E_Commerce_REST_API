package com.ecommerce.mapper;

import com.ecommerce.dto.supplier.SupplierDTO;
import com.ecommerce.entity.Supplier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SupplierMapper {

    public SupplierDTO fromSupplier(Supplier supplier) {
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setId(supplier.getId());
        supplierDTO.setName(supplier.getName());
        supplierDTO.setEmail(supplier.getEmail());
        supplierDTO.setMobilePhone(supplier.getMobilePhone());
        supplierDTO.setGender(supplier.getGender());
        supplierDTO.setSupplierAddress(supplier.getSupplierAddress());
        supplierDTO.setProducts(supplier.getProducts());
        supplierDTO.setCreatedAt(supplier.getCreatedAt());
        supplierDTO.setUpdatedAt(supplier.getUpdatedAt());
        return supplierDTO;
    }

    public List<SupplierDTO> fromSupplierList(List<Supplier> supplierList) {
        return supplierList.stream()
                .map(this::fromSupplier)
                .collect(Collectors.toList());
    }
}
