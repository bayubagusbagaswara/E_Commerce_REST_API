package com.ecommerce.service.impl;

import com.ecommerce.entity.region.SubDistrict;
import com.ecommerce.entity.region.UrbanVillage;
import com.ecommerce.mapper.WilayahMapper;
import com.ecommerce.dto.kelurahan.*;
import com.ecommerce.dto.region.urbanVillage.*;
import com.ecommerce.exception.KecamatanNotFoundException;
import com.ecommerce.exception.KelurahanNotFoundException;
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
    private final WilayahMapper wilayahMapper;

    public UrbanVillageServiceImpl(UrbanVillageRepository urbanVillageRepository, SubDistrictRepository subDistrictRepository, WilayahMapper wilayahMapper) {
        this.urbanVillageRepository = urbanVillageRepository;
        this.subDistrictRepository = subDistrictRepository;
        this.wilayahMapper = wilayahMapper;
    }

    @Override
    public UrbanVillageDTO createKelurahan(CreateUrbanVillageRequestDTO createKelurahanRequest) throws KecamatanNotFoundException {
        SubDistrict subDistrict = subDistrictRepository.findById(createKelurahanRequest.getKecamatanId())
                .orElseThrow(() -> new KecamatanNotFoundException("Kecamatan ID [" + createKelurahanRequest.getKecamatanId() + "] not found"));
        UrbanVillage urbanVillage = new UrbanVillage();
        urbanVillage.setCode(createKelurahanRequest.getCode());
        urbanVillage.setName(createKelurahanRequest.getName());
        urbanVillage.setCreatedDate(LocalDateTime.now());
        urbanVillage.setSubDistrict(subDistrict);
        urbanVillageRepository.save(urbanVillage);
        return wilayahMapper.mapToKelurahanResponse(urbanVillage);
    }

    @Override
    public UrbanVillageDTO getKelurahanById(String id) throws KelurahanNotFoundException {
        UrbanVillage urbanVillage = urbanVillageRepository.findById(id)
                .orElseThrow(() -> new KelurahanNotFoundException("Kelurahan ID [" + id + "] not found"));
        return wilayahMapper.mapToKelurahanResponse(urbanVillage);
    }

    @Override
    public UrbanVillageDTO updateKelurahan(String id, UpdateKelurahanRequestDto updateKelurahanRequest) throws KecamatanNotFoundException, KelurahanNotFoundException {
        SubDistrict subDistrict = subDistrictRepository.findById(updateKelurahanRequest.getKecamatanId())
                .orElseThrow(() -> new KecamatanNotFoundException("Kecamatan ID [" + updateKelurahanRequest.getKecamatanId() + "] not found"));
        UrbanVillage urbanVillage = urbanVillageRepository.findById(id)
                .orElseThrow(() -> new KelurahanNotFoundException("Kelurahan ID [" + id + "] not found"));
        urbanVillage.setCode(updateKelurahanRequest.getCode());
        urbanVillage.setName(updateKelurahanRequest.getName());
        urbanVillage.setSubDistrict(subDistrict);
        urbanVillage.setUpdatedDate(LocalDateTime.now());
        urbanVillageRepository.save(urbanVillage);
        return wilayahMapper.mapToKelurahanResponse(urbanVillage);
    }

    @Override
    public ListKelurahanResponseDto getAllKelurahan(ListKelurahanRequestDto listKelurahanRequest) {
        Integer pageNo = listKelurahanRequest.getPageNo();
        Integer pageSize = listKelurahanRequest.getPageSize();
        String sortBy = listKelurahanRequest.getSortBy();
        String sortDir = listKelurahanRequest.getSortDir();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<UrbanVillage> kelurahanPage = urbanVillageRepository.findAll(pageable);
        List<UrbanVillage> urbanVillageList = kelurahanPage.getContent();
        List<UrbanVillageDTO> kelurahanResponseList = wilayahMapper.mapToKelurahanResponseList(urbanVillageList);

        ListKelurahanResponseDto listKelurahanResponse = new ListKelurahanResponseDto();
        listKelurahanResponse.setKelurahanList(kelurahanResponseList);
        listKelurahanResponse.setPageNo(kelurahanPage.getNumber());
        listKelurahanResponse.setPageSize(kelurahanPage.getSize());
        listKelurahanResponse.setTotalElements(kelurahanPage.getTotalElements());
        listKelurahanResponse.setTotalPages(kelurahanPage.getTotalPages());
        listKelurahanResponse.setLast(kelurahanPage.isLast());
        return listKelurahanResponse;
    }

    @Override
    public void deleteKelurahan(String id) throws KelurahanNotFoundException {
        final UrbanVillage urbanVillage = urbanVillageRepository.findById(id)
                .orElseThrow(() -> new KelurahanNotFoundException("Kelurahan ID [" + id + "] not found"));
        urbanVillageRepository.delete(urbanVillage);
    }

    @Override
    public UrbanVillageDTO getKelurahanByName(String name) throws KelurahanNotFoundException {
        UrbanVillage urbanVillage = urbanVillageRepository.findAllByNameIgnoreCase(name).orElseThrow(() -> new KelurahanNotFoundException("Kelurahan name [" + name + "] not found"));
        return wilayahMapper.mapToKelurahanResponse(urbanVillage);
    }

    @Override
    public UrbanVillageDTO getKelurahanByCode(String code) throws KelurahanNotFoundException {
        UrbanVillage urbanVillage = urbanVillageRepository.findAllByCode(code).orElseThrow(() -> new KelurahanNotFoundException("Kelurahan code [" + code + "] not found"));
        return wilayahMapper.mapToKelurahanResponse(urbanVillage);
    }

    @Override
    public List<UrbanVillageDTO> getKelurahanByNameContains(String name) {
        List<UrbanVillage> urbanVillageList = urbanVillageRepository.findAllByNameContainingIgnoreCase(name);
        return wilayahMapper.mapToKelurahanResponseList(urbanVillageList);
    }

    @Override
    public List<UrbanVillageDTO> getKelurahanByKecamatanId(String kecamatanId) {
        List<UrbanVillage> urbanVillageList = urbanVillageRepository.findAllByKecamatanId(kecamatanId);
        return wilayahMapper.mapToKelurahanResponseList(urbanVillageList);
    }
}

