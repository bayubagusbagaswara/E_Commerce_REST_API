package com.ecommerce.service;

import com.ecommerce.dto.supplier.*;

import java.util.List;

public interface SupplierService {

    SupplierDTO createSupplier(CreateSupplierRequestDTO createSupplierRequest);

    SupplierDTO updateSupplier(String supplierId, UpdateSupplierRequestDTO updateSupplierRequest);

    void deleteSupplier(String supplierId);

    SupplierDTO getSupplierById(String supplierId);

    ListSupplierResponseDTO getAllSuppliers(ListSupplierRequestDTO listSupplierRequest);

    SupplierDTO getSupplierByName(String name);

    List<SupplierDTO> getSupplierByNameContains(String name);

    SupplierDTO getSupplierByEmail(String email);

    List<SupplierDTO> getAllSuppliersByProductId(String productId);

    SupplierDTO addProductToSupplier(String supplierId, String productId);
}
