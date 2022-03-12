package com.restful.service;

import com.restful.dto.supplier.*;
import com.restful.exception.KelurahanNotFoundException;
import com.restful.exception.ProductNotFoundException;
import com.restful.exception.SupplierNotFoundException;

import java.util.List;

public interface SupplierService {

    SupplierResponseDto createSupplier(CreateSupplierRequestDto createSupplierRequest) throws KelurahanNotFoundException;

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
