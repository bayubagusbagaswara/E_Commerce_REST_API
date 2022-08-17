package com.ecommerce.service.impl;

import com.ecommerce.entity.SupplierAddress;
import com.ecommerce.entity.region.UrbanVillage;
import com.ecommerce.mapper.SupplierMapper;
import com.ecommerce.dto.supplier.*;
import com.ecommerce.entity.product.Product;
import com.ecommerce.entity.Supplier;
import com.ecommerce.exception.UrbanVillageNotFoundException;
import com.ecommerce.exception.ProductNotFoundException;
import com.ecommerce.exception.SupplierNotFoundException;
import com.ecommerce.repository.region.UrbanVillageRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.SupplierRepository;
import com.ecommerce.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;
    private final UrbanVillageRepository urbanVillageRepository;
    private final SupplierMapper supplierMapper;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, ProductRepository productRepository, UrbanVillageRepository urbanVillageRepository, SupplierMapper supplierMapper) {
        this.supplierRepository = supplierRepository;
        this.productRepository = productRepository;
        this.urbanVillageRepository = urbanVillageRepository;
        this.supplierMapper = supplierMapper;
    }

    @Override
    public SupplierDTO createSupplier(CreateSupplierRequestDTO createSupplierRequest) {
        UrbanVillage urbanVillage = urbanVillageRepository.findById(createSupplierRequest.getUrbanVillageId()).orElseThrow(() -> new UrbanVillageNotFoundException("id", createSupplierRequest.getUrbanVillageId()));

        SupplierAddress supplierAddress = SupplierAddress.builder()
                .fullAddress(createSupplierRequest.getStreet())
                .urbanVillage(urbanVillage)
                .build();

        Supplier supplier = Supplier.builder()
                .name(createSupplierRequest.getName())
                .email(createSupplierRequest.getEmail())
                .mobilePhone(createSupplierRequest.getMobilePhone())
                .gender(createSupplierRequest.getGender())
                .supplierAddress(supplierAddress)
                .build();
        supplierRepository.save(supplier);
        return supplierMapper.fromSupplier(supplier);
    }

    @Override
    public SupplierDTO getSupplierById(String supplierId) {
        Supplier supplier = getSupplier(supplierId);
        return supplierMapper.fromSupplier(supplier);
    }

    @Override
    public ListSupplierResponseDTO getAllSuppliers(ListSupplierRequestDTO listSupplierRequest) {
        Integer pageNo = listSupplierRequest.getPageNo();
        Integer pageSize = listSupplierRequest.getPageSize();
        String sortBy = listSupplierRequest.getSortBy();
        String sortDir = listSupplierRequest.getSortDir();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Supplier> supplierPage = supplierRepository.findAll(pageable);
        List<Supplier> supplierList = supplierPage.getContent();

        List<SupplierDTO> supplierDTOList = supplierMapper.fromSupplierList(supplierList);

        return ListSupplierResponseDTO.builder()
                .supplierDTOList(supplierDTOList)
                .pageNo(supplierPage.getNumber())
                .pageSize(supplierPage.getSize())
                .totalElements(supplierPage.getTotalElements())
                .totalPages(supplierPage.getTotalPages())
                .last(supplierPage.isLast())
                .build();
    }

    @Override
    public SupplierDTO updateSupplier(String supplierId, UpdateSupplierRequestDTO updateSupplierRequest) {
        Supplier supplier = getSupplier(supplierId);
        UrbanVillage urbanVillage = urbanVillageRepository.findById(updateSupplierRequest.getUrbanVillageId())
                .orElseThrow(() -> new UrbanVillageNotFoundException("id", updateSupplierRequest.getUrbanVillageId()));

        SupplierAddress supplierAddress = supplier.getSupplierAddress();
        supplierAddress.setFullAddress(updateSupplierRequest.getStreet());
        supplierAddress.setUrbanVillage(urbanVillage);

        supplier.setName(updateSupplierRequest.getName());
        supplier.setEmail(updateSupplierRequest.getEmail());
        supplier.setGender(updateSupplierRequest.getGender());
        supplier.setMobilePhone(updateSupplierRequest.getMobilePhone());
        supplier.setSupplierAddress(supplierAddress);
        supplier.setCreatedAt(Instant.now());

        supplierRepository.save(supplier);
        return supplierMapper.fromSupplier(supplier);
    }

    @Override
    public void deleteSupplier(String supplierId) {
        Optional<Supplier> supplier = supplierRepository.findById(supplierId);
        if (supplier.isEmpty()) {
            throw new SupplierNotFoundException("id", supplierId);
        }
        supplier.get().removeProducts();
        supplierRepository.deleteById(supplier.get().getId());
    }

    @Override
    public SupplierDTO getSupplierByName(String name) {
        Supplier supplier = supplierRepository.findAllByNameIgnoreCase(name).orElseThrow(() -> new SupplierNotFoundException("name", name));
        return supplierMapper.fromSupplier(supplier);
    }

    @Override
    public SupplierDTO getSupplierByEmail(String email) {
        Supplier supplier = supplierRepository.findAllByEmail(email).orElseThrow(() -> new SupplierNotFoundException("email", email));
        return supplierMapper.fromSupplier(supplier);
    }

    @Override
    public List<SupplierDTO> getSupplierByNameContains(String name) {
        List<Supplier> supplierList = supplierRepository.findAllByNameContainingIgnoreCase(name);
        return supplierMapper.fromSupplierList(supplierList);
    }

    @Override
    public List<SupplierDTO> getAllSuppliersByProductId(String productId) {
        List<Supplier> supplierList = supplierRepository.findAllByProductsId(productId);
        return supplierMapper.fromSupplierList(supplierList);
    }

    @Override
    public SupplierDTO addProductToSupplier(String supplierId, String productId) {
        Product product = getProduct(productId);
        Supplier supplier = getSupplier(supplierId);
        supplier.addProduct(product);
        supplierRepository.save(supplier);
        return supplierMapper.fromSupplier(supplier);
    }

    private Supplier getSupplier(String supplierId) {
        return supplierRepository.findById(supplierId).orElseThrow(() -> new SupplierNotFoundException("id", supplierId));
    }

    private Product getProduct(String productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("id", productId));
    }
}