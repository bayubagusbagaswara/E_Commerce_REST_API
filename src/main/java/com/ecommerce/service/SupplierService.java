package com.ecommerce.service;

import com.ecommerce.dto.supplier.*;
import com.ecommerce.exception.KelurahanNotFoundException;
import com.ecommerce.exception.ProductNotFoundException;
import com.ecommerce.exception.SupplierNotFoundException;

import java.util.List;

public interface SupplierService {

    SupplierResponseDto createSupplier(CreateSupplierRequestDTO createSupplierRequest) throws KelurahanNotFoundException;

    SupplierResponseDto getSupplierById(String id) throws SupplierNotFoundException;

    ListSupplierResponseDto getAllSuppliers(ListSupplierRequestDto listSupplierRequest);

    SupplierResponseDto updateSupplier(String id, UpdateSupplierRequestDto updateSupplierRequest) throws SupplierNotFoundException, KelurahanNotFoundException;

    void deleteSupplier(String id) throws SupplierNotFoundException;

    SupplierResponseDto getSupplierByName(String name) throws SupplierNotFoundException;

    List<SupplierResponseDto> getSupplierByNameContains(String name);

    SupplierResponseDto getSupplierByEmail(String email) throws SupplierNotFoundException;

    List<SupplierResponseDto> getSupplierByProductsId(String productId);

    SupplierResponseDto addProductToSupplier(String supplierId, String productId) throws SupplierNotFoundException, ProductNotFoundException;
}
