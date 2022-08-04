package com.ecommerce.service.impl;

import com.ecommerce.entity.SupplierAddress;
import com.ecommerce.entity.region.UrbanVillage;
import com.ecommerce.mapper.SupplierMapper;
import com.ecommerce.dto.supplier.*;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.Supplier;
import com.ecommerce.exception.KelurahanNotFoundException;
import com.ecommerce.exception.ProductNotFoundException;
import com.ecommerce.exception.SupplierNotFoundException;
import com.ecommerce.repository.AddressRepository;
import com.ecommerce.repository.UrbanVillageRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.SupplierRepository;
import com.ecommerce.service.SupplierService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;
    private final UrbanVillageRepository urbanVillageRepository;
    private final AddressRepository addressRepository;
    private final SupplierMapper supplierMapper;

    public SupplierServiceImpl(SupplierRepository supplierRepository, ProductRepository productRepository, UrbanVillageRepository urbanVillageRepository, AddressRepository addressRepository, SupplierMapper supplierMapper) {
        this.supplierRepository = supplierRepository;
        this.productRepository = productRepository;
        this.urbanVillageRepository = urbanVillageRepository;
        this.addressRepository = addressRepository;
        this.supplierMapper = supplierMapper;
    }

    @Override
    public SupplierDTO createSupplier(CreateSupplierRequestDTO createSupplierRequest) throws KelurahanNotFoundException {

        UrbanVillage urbanVillage = urbanVillageRepository.findById(createSupplierRequest.getKelurahanId())
                .orElseThrow(() -> new KelurahanNotFoundException("Kelurahan ID [" + createSupplierRequest.getKelurahanId() + "] not found"));

        SupplierAddress supplierAddress = new SupplierAddress();
        supplierAddress.setStreet(createSupplierRequest.getStreet());
        supplierAddress.setPostalCode(createSupplierRequest.getPostalCode());
        supplierAddress.setUrbanVillage(urbanVillage);

        Supplier supplier = new Supplier();
        supplier.setName(createSupplierRequest.getName());
        supplier.setEmail(createSupplierRequest.getEmail());
        supplier.setGender(createSupplierRequest.getGender());
        supplier.setMobilePhone(createSupplierRequest.getMobilePhone());
        supplier.setSupplierAddress(supplierAddress);
        supplier.setCreatedDate(LocalDateTime.now());

        addressRepository.save(supplierAddress);
        supplierRepository.save(supplier);
        return supplierMapper.mapToSupplierResponse(supplier);
    }

    @Override
    public SupplierDTO getSupplierById(String id) throws SupplierNotFoundException {
        Supplier supplier = getSupplier(id);
        return supplierMapper.mapToSupplierResponse(supplier);
    }

    @Override
    public ListSupplierResponseDto getAllSuppliers(ListSupplierRequestDTO listSupplierRequest) {
        Integer pageNo = listSupplierRequest.getPageNo();
        Integer pageSize = listSupplierRequest.getPageSize();
        String sortBy = listSupplierRequest.getSortBy();
        String sortDir = listSupplierRequest.getSortDir();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Supplier> supplierPage = supplierRepository.findAll(pageable);
        List<Supplier> supplierList = supplierPage.getContent();

        List<SupplierDTO> supplierResponseList = supplierMapper.mapToSupplierResponseList(supplierList);

        ListSupplierResponseDto listSupplierResponse = new ListSupplierResponseDto();
        listSupplierResponse.setSupplierList(supplierResponseList);
        listSupplierResponse.setPageNo(supplierPage.getNumber());
        listSupplierResponse.setPageSize(supplierPage.getSize());
        listSupplierResponse.setTotalElements(supplierPage.getTotalElements());
        listSupplierResponse.setTotalPages(supplierPage.getTotalPages());
        listSupplierResponse.setLast(supplierPage.isLast());
        return listSupplierResponse;
    }

    @Override
    public SupplierDTO updateSupplier(String id, UpdateSupplierRequestDto updateSupplierRequest) throws SupplierNotFoundException, KelurahanNotFoundException {

        Supplier supplier = getSupplier(id);
        UrbanVillage urbanVillage = urbanVillageRepository.findById(updateSupplierRequest.getKelurahanId())
                .orElseThrow(() -> new KelurahanNotFoundException("Kelurahan ID [" + updateSupplierRequest.getKelurahanId() + "] not found"));

        SupplierAddress supplierAddress = supplier.getSupplierAddress();
        supplierAddress.setStreet(updateSupplierRequest.getStreet());
        supplierAddress.setPostalCode(updateSupplierRequest.getPostalCode());
        supplierAddress.setUrbanVillage(urbanVillage);

        supplier.setName(updateSupplierRequest.getName());
        supplier.setEmail(updateSupplierRequest.getEmail());
        supplier.setGender(updateSupplierRequest.getGender());
        supplier.setMobilePhone(updateSupplierRequest.getMobilePhone());
        supplier.setSupplierAddress(supplierAddress);
        supplier.setUpdatedDate(LocalDateTime.now());

        addressRepository.save(supplierAddress);
        supplierRepository.save(supplier);
        return supplierMapper.mapToSupplierResponse(supplier);
    }

    @Override
    public void deleteSupplier(String id) throws SupplierNotFoundException {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        if (supplier.isEmpty()) {
            throw new SupplierNotFoundException("Supplier ID: [" + id + "] not found");
        }
        supplier.get().removeProducts();
        supplierRepository.deleteById(supplier.get().getId());
    }

    @Override
    public SupplierDTO getSupplierByName(String name) throws SupplierNotFoundException {
        Supplier supplier = supplierRepository.findAllByNameIgnoreCase(name)
                .orElseThrow(() -> new SupplierNotFoundException("Supplier name [" + name + "] not found"));
        return supplierMapper.mapToSupplierResponse(supplier);
    }

    @Override
    public SupplierDTO getSupplierByEmail(String email) throws SupplierNotFoundException {
        Supplier supplier = supplierRepository.findAllByEmail(email)
                .orElseThrow(() -> new SupplierNotFoundException("Supplier email [" + email + "] not found"));
        return supplierMapper.mapToSupplierResponse(supplier);
    }

    @Override
    public List<SupplierDTO> getSupplierByNameContains(String name) {
        List<Supplier> supplierList = supplierRepository.findAllByNameContainingIgnoreCase(name);
        return supplierMapper.mapToSupplierResponseList(supplierList);
    }

    @Override
    public List<SupplierDTO> getSupplierByProductsId(String productId) {
        List<Supplier> supplierList = supplierRepository.findAllByProductsId(productId);
        return supplierMapper.mapToSupplierResponseList(supplierList);
    }

    @Override
    public SupplierDTO addProductToSupplier(String supplierId, String productId) throws SupplierNotFoundException, ProductNotFoundException {
        Product product = getProduct(productId);
        Supplier supplier = getSupplier(supplierId);
        supplier.addProduct(product);
        supplierRepository.save(supplier);
        return supplierMapper.mapToSupplierResponse(supplier);
    }

    private Supplier getSupplier(String supplierId) throws SupplierNotFoundException {
        return supplierRepository.findById(supplierId).orElseThrow(() -> new SupplierNotFoundException("Supplier ID [" + supplierId + "] not found"));
    }

    private Product getProduct(String productId) throws ProductNotFoundException {
        return productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product ID [" + productId + "] not found"));
    }
}