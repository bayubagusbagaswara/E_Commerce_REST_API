package com.ecommerce.service.impl;

import com.ecommerce.entity.region.SubDistrict;
import com.ecommerce.entity.region.UrbanVillage;
import com.ecommerce.mapper.WilayahMapper;
import com.ecommerce.dto.kelurahan.*;
import com.ecommerce.dto.region.kelurahan.*;
import com.ecommerce.exception.KecamatanNotFoundException;
import com.ecommerce.exception.KelurahanNotFoundException;
import com.ecommerce.repository.KecamatanRepository;
import com.ecommerce.repository.KelurahanRepository;
import com.ecommerce.service.region.KelurahanService;
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
public class KelurahanServiceImpl implements KelurahanService {

    private final KelurahanRepository kelurahanRepository;
    private final KecamatanRepository kecamatanRepository;
    private final WilayahMapper wilayahMapper;

    public KelurahanServiceImpl(KelurahanRepository kelurahanRepository, KecamatanRepository kecamatanRepository, WilayahMapper wilayahMapper) {
        this.kelurahanRepository = kelurahanRepository;
        this.kecamatanRepository = kecamatanRepository;
        this.wilayahMapper = wilayahMapper;
    }

    @Override
    public KelurahanResponseDto createKelurahan(CreateKelurahanRequestDto createKelurahanRequest) throws KecamatanNotFoundException {
        SubDistrict subDistrict = kecamatanRepository.findById(createKelurahanRequest.getKecamatanId())
                .orElseThrow(() -> new KecamatanNotFoundException("Kecamatan ID [" + createKelurahanRequest.getKecamatanId() + "] not found"));
        UrbanVillage urbanVillage = new UrbanVillage();
        urbanVillage.setCode(createKelurahanRequest.getCode());
        urbanVillage.setName(createKelurahanRequest.getName());
        urbanVillage.setCreatedDate(LocalDateTime.now());
        urbanVillage.setSubDistrict(subDistrict);
        kelurahanRepository.save(urbanVillage);
        return wilayahMapper.mapToKelurahanResponse(urbanVillage);
    }

    @Override
    public KelurahanResponseDto getKelurahanById(String id) throws KelurahanNotFoundException {
        UrbanVillage urbanVillage = kelurahanRepository.findById(id)
                .orElseThrow(() -> new KelurahanNotFoundException("Kelurahan ID [" + id + "] not found"));
        return wilayahMapper.mapToKelurahanResponse(urbanVillage);
    }

    @Override
    public KelurahanResponseDto updateKelurahan(String id, UpdateKelurahanRequestDto updateKelurahanRequest) throws KecamatanNotFoundException, KelurahanNotFoundException {
        SubDistrict subDistrict = kecamatanRepository.findById(updateKelurahanRequest.getKecamatanId())
                .orElseThrow(() -> new KecamatanNotFoundException("Kecamatan ID [" + updateKelurahanRequest.getKecamatanId() + "] not found"));
        UrbanVillage urbanVillage = kelurahanRepository.findById(id)
                .orElseThrow(() -> new KelurahanNotFoundException("Kelurahan ID [" + id + "] not found"));
        urbanVillage.setCode(updateKelurahanRequest.getCode());
        urbanVillage.setName(updateKelurahanRequest.getName());
        urbanVillage.setSubDistrict(subDistrict);
        urbanVillage.setUpdatedDate(LocalDateTime.now());
        kelurahanRepository.save(urbanVillage);
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

        Page<UrbanVillage> kelurahanPage = kelurahanRepository.findAll(pageable);
        List<UrbanVillage> urbanVillageList = kelurahanPage.getContent();
        List<KelurahanResponseDto> kelurahanResponseList = wilayahMapper.mapToKelurahanResponseList(urbanVillageList);

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
        final UrbanVillage urbanVillage = kelurahanRepository.findById(id)
                .orElseThrow(() -> new KelurahanNotFoundException("Kelurahan ID [" + id + "] not found"));
        kelurahanRepository.delete(urbanVillage);
    }

    @Override
    public KelurahanResponseDto getKelurahanByName(String name) throws KelurahanNotFoundException {
        UrbanVillage urbanVillage = kelurahanRepository.findAllByNameIgnoreCase(name).orElseThrow(() -> new KelurahanNotFoundException("Kelurahan name [" + name + "] not found"));
        return wilayahMapper.mapToKelurahanResponse(urbanVillage);
    }

    @Override
    public KelurahanResponseDto getKelurahanByCode(String code) throws KelurahanNotFoundException {
        UrbanVillage urbanVillage = kelurahanRepository.findAllByCode(code).orElseThrow(() -> new KelurahanNotFoundException("Kelurahan code [" + code + "] not found"));
        return wilayahMapper.mapToKelurahanResponse(urbanVillage);
    }

    @Override
    public List<KelurahanResponseDto> getKelurahanByNameContains(String name) {
        List<UrbanVillage> urbanVillageList = kelurahanRepository.findAllByNameContainingIgnoreCase(name);
        return wilayahMapper.mapToKelurahanResponseList(urbanVillageList);
    }

    @Override
    public List<KelurahanResponseDto> getKelurahanByKecamatanId(String kecamatanId) {
        List<UrbanVillage> urbanVillageList = kelurahanRepository.findAllByKecamatanId(kecamatanId);
        return wilayahMapper.mapToKelurahanResponseList(urbanVillageList);
    }
}

