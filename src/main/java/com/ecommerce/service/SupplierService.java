package com.ecommerce.service;

import com.ecommerce.dto.supplier.*;
import com.ecommerce.exception.UrbanVillageNotFoundException;
import com.ecommerce.exception.ProductNotFoundException;
import com.ecommerce.exception.SupplierNotFoundException;

import java.util.List;

public interface SupplierService {

    SupplierDTO createSupplier(CreateSupplierRequestDTO createSupplierRequest) throws UrbanVillageNotFoundException;

    SupplierDTO getSupplierById(String id) throws SupplierNotFoundException;

    ListSupplierResponseDTO getAllSuppliers(ListSupplierRequestDTO listSupplierRequest);

    SupplierDTO updateSupplier(String id, UpdateSupplierRequestDTO updateSupplierRequest) throws SupplierNotFoundException, UrbanVillageNotFoundException;

    void deleteSupplier(String id) throws SupplierNotFoundException;

    SupplierDTO getSupplierByName(String name) throws SupplierNotFoundException;

    List<SupplierDTO> getSupplierByNameContains(String name);

    SupplierDTO getSupplierByEmail(String email) throws SupplierNotFoundException;

    List<SupplierDTO> getSupplierByProductsId(String productId);

    SupplierDTO addProductToSupplier(String supplierId, String productId) throws SupplierNotFoundException, ProductNotFoundException;
}
