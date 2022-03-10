package com.restful.service.impl;

import com.restful.dto.SupplierMapper;
import com.restful.dto.product.ProductResponseDto;
import com.restful.dto.supplier.*;
import com.restful.entity.Address;
import com.restful.entity.Product;
import com.restful.entity.Supplier;
import com.restful.entity.wilayah.Kelurahan;
import com.restful.exception.KelurahanNotFoundException;
import com.restful.exception.ProductNotFoundException;
import com.restful.exception.SupplierNotFoundException;
import com.restful.repository.AddressRepository;
import com.restful.repository.KelurahanRepository;
import com.restful.repository.ProductRepository;
import com.restful.repository.SupplierRepository;
import com.restful.service.SupplierService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;
    private final KelurahanRepository kelurahanRepository;
    private final AddressRepository addressRepository;
    private final SupplierMapper supplierMapper;

    public SupplierServiceImpl(SupplierRepository supplierRepository, ProductRepository productRepository, KelurahanRepository kelurahanRepository, AddressRepository addressRepository, SupplierMapper supplierMapper) {
        this.supplierRepository = supplierRepository;
        this.productRepository = productRepository;
        this.kelurahanRepository = kelurahanRepository;
        this.addressRepository = addressRepository;
        this.supplierMapper = supplierMapper;
    }

    @Override
    public ListSupplierResponseDto getAllSuppliers(ListSupplierRequestDto listSupplierRequestDto) {
        Integer pageNo = listSupplierRequestDto.getPageNo();
        Integer pageSize = listSupplierRequestDto.getPageSize();
        String sortBy = listSupplierRequestDto.getSortBy();
        String sortDir = listSupplierRequestDto.getSortDir();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Supplier> supplierPage = supplierRepository.findAll(pageable);
        List<Supplier> supplierList = supplierPage.getContent();

        List<SupplierResponseDto> supplierResponseDtoList = supplierMapper.mapSupplierListToSupplierResponseDtoList(supplierList);

        ListSupplierResponseDto dto = new ListSupplierResponseDto();
        dto.setSupplierResponseDtoList(supplierResponseDtoList);
        dto.setPageNo(supplierPage.getNumber());
        dto.setPageSize(supplierPage.getSize());
        dto.setTotalElements(supplierPage.getTotalElements());
        dto.setTotalPages(supplierPage.getTotalPages());
        dto.setLast(supplierPage.isLast());
        return dto;
    }

    @Override
    public SupplierResponseDto getSupplierByName(String name) throws SupplierNotFoundException {
        Supplier supplier = supplierRepository.findAllByNameIgnoreCase(name)
                .orElseThrow(() -> new SupplierNotFoundException("Supplier name [" + name + "] not found"));
        return supplierMapper.mapSupplierToSupplierResponseDto(supplier);
    }

    @Override
    public SupplierResponseDto createSupplier(CreateSupplierRequestDto createSupplierRequestDto) throws KelurahanNotFoundException {
        // ambil data kelurahan
        Kelurahan kelurahan = kelurahanRepository.findById(createSupplierRequestDto.getKelurahanId())
                .orElseThrow(() -> new KelurahanNotFoundException("Kelurahan ID [" + createSupplierRequestDto.getKelurahanId() + "] not found"));

        // create address
        Address address = new Address();
        address.setStreet(createSupplierRequestDto.getStreet());
        address.setPostalCode(createSupplierRequestDto.getPostalCode());
        address.setKelurahan(kelurahan);

        // create supplier
        Supplier supplier = new Supplier();
        supplier.setName(createSupplierRequestDto.getName());
        supplier.setEmail(createSupplierRequestDto.getEmail());
        supplier.setGender(createSupplierRequestDto.getGender());
        supplier.setMobilePhone(createSupplierRequestDto.getMobilePhone());
        supplier.setAddress(address);
        supplier.setCreatedAt(LocalDateTime.now());

        // save
        addressRepository.save(address);
        supplierRepository.save(supplier);

        return supplierMapper.mapSupplierToSupplierResponseDto(supplier);
    }

    @Override
    public SupplierResponseDto getSupplierById(String supplierId) throws SupplierNotFoundException {
        Supplier supplier = getSupplier(supplierId);
        return supplierMapper.mapSupplierToSupplierResponseDto(supplier);
    }

    @Override
    public SupplierResponseDto updateSupplier(String supplierId, UpdateSupplierRequestDto updateSupplierRequestDto) throws SupplierNotFoundException, KelurahanNotFoundException {
        // ambil data kelurahan
        Kelurahan kelurahan = kelurahanRepository.findById(updateSupplierRequestDto.getKelurahanId())
                .orElseThrow(() -> new KelurahanNotFoundException("Kelurahan ID [" + updateSupplierRequestDto.getKelurahanId() + "] not found"));

        // kita address
        Address address = new Address();
        address.setStreet(updateSupplierRequestDto.getStreet());
        address.setPostalCode(updateSupplierRequestDto.getPostalCode());
        address.setKelurahan(kelurahan);

        // get Supplier By ID
        Supplier supplier = getSupplier(supplierId);
        supplier.setName(updateSupplierRequestDto.getName());
        supplier.setEmail(updateSupplierRequestDto.getEmail());
        supplier.setGender(updateSupplierRequestDto.getGender());
        supplier.setMobilePhone(updateSupplierRequestDto.getMobilePhone());
        supplier.setAddress(address);
        supplier.setUpdatedAt(LocalDateTime.now());

        // save
        addressRepository.save(address);
        supplierRepository.save(supplier);

        return supplierMapper.mapSupplierToSupplierResponseDto(supplier);
    }

    @Override
    public void deleteSupplier(String supplierId) throws SupplierNotFoundException {
        Optional<Supplier> supplier = supplierRepository.findById(supplierId);
        if (supplier.isEmpty()) {
            throw new SupplierNotFoundException("Supplier ID: [" + supplierId + "] not found");
        }
        supplier.get().removeProducts();
        supplierRepository.deleteById(supplierId);
    }

    @Override
    public SupplierResponseDto getSupplierByEmail(String email) throws SupplierNotFoundException {
        Supplier supplier = supplierRepository.findAllByEmail(email)
                .orElseThrow(() -> new SupplierNotFoundException("Supplier email [" + email + "] not found"));
        return supplierMapper.mapSupplierToSupplierResponseDto(supplier);
    }

    @Override
    public List<SupplierResponseDto> getSupplierByNameContains(String name) {
        List<Supplier> supplierList = supplierRepository.findAllByNameContainingIgnoreCase(name);
        return supplierMapper.mapSupplierListToSupplierResponseDtoList(supplierList);
    }

    @Override
    public List<SupplierResponseDto> getSupplierByProductsId(String productId) {
        List<Supplier> supplierList = supplierRepository.findAllByProductsId(productId);
        return supplierMapper.mapSupplierListToSupplierResponseDtoList(supplierList);
    }

    @Override
    public SupplierResponseDto addProductToSupplier(String supplierId, String productId) throws SupplierNotFoundException, ProductNotFoundException {
        Product product = getProduct(productId);
        Supplier supplier = getSupplier(supplierId);
        supplier.getProducts().add(product);
        supplierRepository.save(supplier);
        return supplierMapper.mapSupplierToSupplierResponseDto(supplier);
    }

    private Supplier getSupplier(String supplierId) throws SupplierNotFoundException {
        return supplierRepository.findById(supplierId).orElseThrow(() -> new SupplierNotFoundException("Supplier ID [" + supplierId + "] not found"));
    }

    private Product getProduct(String productId) throws ProductNotFoundException {
        return productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product ID [" + productId + "] not found"));
    }
}