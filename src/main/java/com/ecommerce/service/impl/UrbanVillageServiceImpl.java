package com.ecommerce.service.impl;

import com.ecommerce.entity.region.SubDistrict;
import com.ecommerce.entity.region.UrbanVillage;
import com.ecommerce.dto.kelurahan.*;
import com.ecommerce.dto.region.urbanVillage.*;
import com.ecommerce.exception.SubDistrictNotFoundException;
import com.ecommerce.exception.UrbanVillageNotFoundException;
import com.ecommerce.repository.SubDistrictRepository;
import com.ecommerce.repository.UrbanVillageRepository;
import com.ecommerce.service.region.UrbanVillageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class UrbanVillageServiceImpl implements UrbanVillageService {

    private final UrbanVillageRepository urbanVillageRepository;
    private final SubDistrictRepository subDistrictRepository;
    private final RegionMapper regionMapper;

    public UrbanVillageServiceImpl(UrbanVillageRepository urbanVillageRepository, SubDistrictRepository subDistrictRepository, RegionMapper regionMapper) {
        this.urbanVillageRepository = urbanVillageRepository;
        this.subDistrictRepository = subDistrictRepository;
        this.regionMapper = regionMapper;
    }

    @Override
    public UrbanVillageDTO createKelurahan(CreateUrbanVillageRequestDTO createKelurahanRequest) throws SubDistrictNotFoundException {
        SubDistrict subDistrict = subDistrictRepository.findById(createKelurahanRequest.getKecamatanId())
                .orElseThrow(() -> new SubDistrictNotFoundException("Kecamatan ID [" + createKelurahanRequest.getKecamatanId() + "] not found"));
        UrbanVillage urbanVillage = new UrbanVillage();
        urbanVillage.setCode(createKelurahanRequest.getCode());
        urbanVillage.setName(createKelurahanRequest.getName());
        urbanVillage.setCreatedDate(LocalDateTime.now());
        urbanVillage.setSubDistrict(subDistrict);
        urbanVillageRepository.save(urbanVillage);
        return regionMapper.mapToKelurahanResponse(urbanVillage);
    }

    @Override
    public UrbanVillageDTO getKelurahanById(String id) throws UrbanVillageNotFoundException {
        UrbanVillage urbanVillage = urbanVillageRepository.findById(id)
                .orElseThrow(() -> new UrbanVillageNotFoundException("Kelurahan ID [" + id + "] not found"));
        return regionMapper.mapToKelurahanResponse(urbanVillage);
    }

    @Override
    public UrbanVillageDTO updateKelurahan(String id, UpdateUrbanVillageRequestDTO updateKelurahanRequest) throws SubDistrictNotFoundException, UrbanVillageNotFoundException {
        SubDistrict subDistrict = subDistrictRepository.findById(updateKelurahanRequest.getKecamatanId())
                .orElseThrow(() -> new SubDistrictNotFoundException("Kecamatan ID [" + updateKelurahanRequest.getKecamatanId() + "] not found"));
        UrbanVillage urbanVillage = urbanVillageRepository.findById(id)
                .orElseThrow(() -> new UrbanVillageNotFoundException("Kelurahan ID [" + id + "] not found"));
        urbanVillage.setCode(updateKelurahanRequest.getCode());
        urbanVillage.setName(updateKelurahanRequest.getName());
        urbanVillage.setSubDistrict(subDistrict);
        urbanVillage.setUpdatedDate(LocalDateTime.now());
        urbanVillageRepository.save(urbanVillage);
        return regionMapper.mapToKelurahanResponse(urbanVillage);
    }

    @Override
    public ListUrbanVillageResponseDTO getAllKelurahan(ListUrbanVillageRequestDTO listKelurahanRequest) {
        Integer pageNo = listKelurahanRequest.getPageNo();
        Integer pageSize = listKelurahanRequest.getPageSize();
        String sortBy = listKelurahanRequest.getSortBy();
        String sortDir = listKelurahanRequest.getSortDir();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<UrbanVillage> kelurahanPage = urbanVillageRepository.findAll(pageable);
        List<UrbanVillage> urbanVillageList = kelurahanPage.getContent();
        List<UrbanVillageDTO> kelurahanResponseList = regionMapper.mapToKelurahanResponseList(urbanVillageList);

        ListUrbanVillageResponseDTO listKelurahanResponse = new ListUrbanVillageResponseDTO();
        listKelurahanResponse.setKelurahanList(kelurahanResponseList);
        listKelurahanResponse.setPageNo(kelurahanPage.getNumber());
        listKelurahanResponse.setPageSize(kelurahanPage.getSize());
        listKelurahanResponse.setTotalElements(kelurahanPage.getTotalElements());
        listKelurahanResponse.setTotalPages(kelurahanPage.getTotalPages());
        listKelurahanResponse.setLast(kelurahanPage.isLast());
        return listKelurahanResponse;
    }

    @Override
    public void deleteKelurahan(String id) throws UrbanVillageNotFoundException {
        final UrbanVillage urbanVillage = urbanVillageRepository.findById(id)
                .orElseThrow(() -> new UrbanVillageNotFoundException("Kelurahan ID [" + id + "] not found"));
        urbanVillageRepository.delete(urbanVillage);
    }

    @Override
    public UrbanVillageDTO getKelurahanByName(String name) throws UrbanVillageNotFoundException {
        UrbanVillage urbanVillage = urbanVillageRepository.findAllByNameIgnoreCase(name).orElseThrow(() -> new UrbanVillageNotFoundException("Kelurahan name [" + name + "] not found"));
        return regionMapper.mapToKelurahanResponse(urbanVillage);
    }

    @Override
    public UrbanVillageDTO getKelurahanByCode(String code) throws UrbanVillageNotFoundException {
        UrbanVillage urbanVillage = urbanVillageRepository.findAllByCode(code).orElseThrow(() -> new UrbanVillageNotFoundException("Kelurahan code [" + code + "] not found"));
        return regionMapper.mapToKelurahanResponse(urbanVillage);
    }

    @Override
    public List<UrbanVillageDTO> getKelurahanByNameContains(String name) {
        List<UrbanVillage> urbanVillageList = urbanVillageRepository.findAllByNameContainingIgnoreCase(name);
        return regionMapper.mapToKelurahanResponseList(urbanVillageList);
    }

    @Override
    public List<UrbanVillageDTO> getKelurahanByKecamatanId(String kecamatanId) {
        List<UrbanVillage> urbanVillageList = urbanVillageRepository.findAllByKecamatanId(kecamatanId);
        return regionMapper.mapToKelurahanResponseList(urbanVillageList);
    }
}

