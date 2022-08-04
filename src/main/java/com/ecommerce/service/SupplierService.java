package com.ecommerce.service;

import com.ecommerce.dto.supplier.*;
import com.ecommerce.exception.KelurahanNotFoundException;
import com.ecommerce.exception.ProductNotFoundException;
import com.ecommerce.exception.SupplierNotFoundException;

import java.util.List;

public interface SupplierService {

    SupplierDTO createSupplier(CreateSupplierRequestDTO createSupplierRequest) throws KelurahanNotFoundException;

    SupplierDTO getSupplierById(String id) throws SupplierNotFoundException;

    ListSupplierResponseDto getAllSuppliers(ListSupplierRequestDTO listSupplierRequest);

    SupplierDTO updateSupplier(String id, UpdateSupplierRequestDto updateSupplierRequest) throws SupplierNotFoundException, KelurahanNotFoundException;

    void deleteSupplier(String id) throws SupplierNotFoundException;

    SupplierDTO getSupplierByName(String name) throws SupplierNotFoundException;

    List<SupplierDTO> getSupplierByNameContains(String name);

    SupplierDTO getSupplierByEmail(String email) throws SupplierNotFoundException;

    List<SupplierDTO> getSupplierByProductsId(String productId);

    SupplierDTO addProductToSupplier(String supplierId, String productId) throws SupplierNotFoundException, ProductNotFoundException;
}
