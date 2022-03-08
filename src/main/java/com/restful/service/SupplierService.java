package com.restful.service;

import com.restful.dto.supplier.*;
import com.restful.exception.ProductNotFoundException;
import com.restful.exception.SupplierNotFoundException;

import java.util.List;

public interface SupplierService {

    // create supplier
    // user baru harus mengirimkan data:
    // name, email, mobile phone, gender, address
    // address terdiri dari street, postal code, dan kelurahan id
    SupplierResponseDto createSupplier(CreateSupplierRequestDto createSupplierRequestDto);

    // get supplier by id
    SupplierResponseDto getSupplierById(String id);

    // get all supplier
    ListSupplierResponseDto getAllSuppliers(ListSupplierRequestDto listSupplierRequestDto);

    // update supplier
    SupplierResponseDto updateSupplier(String id, UpdateSupplierRequestDto updateSupplierRequestDto) throws SupplierNotFoundException;

    // delete supplier
    void deleteSupplier(String id);

    // get supplier by name
    SupplierResponseDto getSupplierByName(String name) throws SupplierNotFoundException;

    // get supplier by email
    SupplierResponseDto getSupplierByEmail(String email) throws SupplierNotFoundException;

    // get supplier by product
    // jadi product A memiliki supplier siapa saja
    List<SupplierResponseDto> getSupplierByProductId(String productId);

    // add product to supplier
    SupplierResponseDto addProductToSupplier(String supplierId, String productId) throws SupplierNotFoundException, ProductNotFoundException;
}
