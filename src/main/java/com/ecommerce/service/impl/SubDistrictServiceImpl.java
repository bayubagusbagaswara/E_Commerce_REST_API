package com.ecommerce.service.impl;

import com.ecommerce.entity.region.District;
import com.ecommerce.entity.region.SubDistrict;
import com.ecommerce.mapper.WilayahMapper;
import com.ecommerce.dto.kecamatan.*;
import com.ecommerce.dto.region.subDistrict.*;
import com.ecommerce.exception.KecamatanNotFoundException;
import com.ecommerce.exception.KotaNotFoundException;
import com.ecommerce.repository.UrbanVillageRepository;
import com.ecommerce.repository.KotaRepository;
import com.ecommerce.service.region.SubDistrictService;
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
public class SubDistrictServiceImpl implements SubDistrictService {

    private final UrbanVillageRepository urbanVillageRepository;
    private final KotaRepository kotaRepository;
    private final WilayahMapper wilayahMapper;

    public SubDistrictServiceImpl(UrbanVillageRepository urbanVillageRepository, KotaRepository kotaRepository, WilayahMapper wilayahMapper) {
        this.urbanVillageRepository = urbanVillageRepository;
        this.kotaRepository = kotaRepository;
        this.wilayahMapper = wilayahMapper;
    }

    @Override
    public KecamatanResponseDto createKecamatan(CreateKecamatanRequestDto createKecamatanRequest) throws KotaNotFoundException {
        District district = kotaRepository.findById(createKecamatanRequest.getKotaId())
                .orElseThrow(() -> new KotaNotFoundException("Kota ID [" + createKecamatanRequest.getKotaId() + "] not found"));
        SubDistrict subDistrict = new SubDistrict();
        subDistrict.setCode(createKecamatanRequest.getCode());
        subDistrict.setName(createKecamatanRequest.getName());
        subDistrict.setDistrict(district);
        subDistrict.setCreatedDate(LocalDateTime.now());
        urbanVillageRepository.save(subDistrict);
        return wilayahMapper.mapToKecamatanResponse(subDistrict);
    }

    @Override
    public KecamatanResponseDto getKecamatanById(String id) throws KecamatanNotFoundException {
        SubDistrict subDistrict = urbanVillageRepository.findById(id)
                .orElseThrow(() -> new KecamatanNotFoundException("Kecamatan ID [" + id + "] not found"));
        return wilayahMapper.mapToKecamatanResponse(subDistrict);
    }

    @Override
    public KecamatanResponseDto updateKecamatan(String id, UpdateKecamatanRequestDto updateKecamatanRequest) throws KotaNotFoundException, KecamatanNotFoundException {
        District district = kotaRepository.findById(updateKecamatanRequest.getKotaId())
                .orElseThrow(() -> new KotaNotFoundException("Kota ID [" + updateKecamatanRequest.getKotaId() + "] not found"));
        SubDistrict subDistrict = urbanVillageRepository.findById(id)
                .orElseThrow(() -> new KecamatanNotFoundException("Kecamatan ID [" + id + "] not found"));
        subDistrict.setCode(updateKecamatanRequest.getCode());
        subDistrict.setName(updateKecamatanRequest.getName());
        subDistrict.setDistrict(district);
        subDistrict.setUpdatedDate(LocalDateTime.now());
        urbanVillageRepository.save(subDistrict);
        return wilayahMapper.mapToKecamatanResponse(subDistrict);
    }

    @Override
    public void deleteKecamatan(String id) throws KecamatanNotFoundException {
        final SubDistrict subDistrict = urbanVillageRepository.findById(id)
                .orElseThrow(() -> new KecamatanNotFoundException("Kecamatan ID [" + id + "] not found"));
        urbanVillageRepository.delete(subDistrict);
    }

    @Override
    public ListKecamatanResponseDto getAllKecamatan(ListKecamatanRequestDto listKecamatanRequest) {
        Integer pageNo = listKecamatanRequest.getPageNo();
        Integer pageSize = listKecamatanRequest.getPageSize();
        String sortBy = listKecamatanRequest.getSortBy();
        String sortDir = listKecamatanRequest.getSortDir();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<SubDistrict> kecamatanPage = urbanVillageRepository.findAll(pageable);
        List<SubDistrict> subDistrictList = kecamatanPage.getContent();

        List<KecamatanResponseDto> kecamatanResponseList = wilayahMapper.mapToKecamatanResponseList(subDistrictList);

        ListKecamatanResponseDto listKecamatanResponse = new ListKecamatanResponseDto();
        listKecamatanResponse.setKecamatanList(kecamatanResponseList);
        listKecamatanResponse.setPageNo(kecamatanPage.getNumber());
        listKecamatanResponse.setPageSize(kecamatanPage.getSize());
        listKecamatanResponse.setTotalElements(kecamatanPage.getTotalElements());
        listKecamatanResponse.setTotalPages(kecamatanPage.getTotalPages());
        listKecamatanResponse.setLast(kecamatanPage.isLast());
        return listKecamatanResponse;
    }

    @Override
    public KecamatanResponseDto getKecamatanByName(String name) throws KecamatanNotFoundException {
        SubDistrict subDistrict = urbanVillageRepository.findAllByNameIgnoreCase(name).orElseThrow(() -> new KecamatanNotFoundException("Kecamatan name [" + name + "] not found"));
        return wilayahMapper.mapToKecamatanResponse(subDistrict);
    }

    @Override
    public KecamatanResponseDto getKecamatanByCode(String code) throws KecamatanNotFoundException {
        SubDistrict subDistrict = urbanVillageRepository.findAllByCode(code).orElseThrow(() -> new KecamatanNotFoundException("Kecamatan code [" + code + "] not found"));
        return wilayahMapper.mapToKecamatanResponse(subDistrict);
    }

    @Override
    public List<KecamatanResponseDto> getKecamatanByNameContains(String name) {
        List<SubDistrict> subDistrictList = urbanVillageRepository.findAllByNameContainingIgnoreCase(name);
        return wilayahMapper.mapToKecamatanResponseList(subDistrictList);
    }

    @Override
    public List<KecamatanResponseDto> getKecamatanByKotaId(String kotaId) {
        List<SubDistrict> subDistrictList = urbanVillageRepository.findAllByKotaId(kotaId);
        return wilayahMapper.mapToKecamatanResponseList(subDistrictList);
    }
}
