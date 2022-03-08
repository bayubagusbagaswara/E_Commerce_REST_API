package com.restful.service.impl;

import com.restful.dto.WilayahMapper;
import com.restful.dto.kelurahan.*;
import com.restful.entity.address.Kecamatan;
import com.restful.entity.address.Kelurahan;
import com.restful.exception.KecamatanNotFoundException;
import com.restful.exception.KelurahanNotFoundException;
import com.restful.repository.KecamatanRepository;
import com.restful.repository.KelurahanRepository;
import com.restful.service.KelurahanService;
import com.restful.service.ProvinsiService;
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

    public KelurahanServiceImpl(KelurahanRepository kelurahanRepository, KecamatanRepository kecamatanRepository) {
        this.kelurahanRepository = kelurahanRepository;
        this.kecamatanRepository = kecamatanRepository;
    }

    @Override
    public KelurahanResponseDto createKelurahan(CreateKelurahanRequestDto createKelurahanRequestDto) throws KecamatanNotFoundException {
        Kecamatan kecamatan = kecamatanRepository.findById(createKelurahanRequestDto.getKecamatanId()).orElseThrow(() -> new KecamatanNotFoundException("Kecamatan ID ["+createKelurahanRequestDto.getKecamatanId()+"] not found"));
        Kelurahan kelurahan = new Kelurahan();
        kelurahan.setCode(createKelurahanRequestDto.getCode());
        kelurahan.setName(createKelurahanRequestDto.getName());
        kelurahan.setCreatedAt(LocalDateTime.now());
        kelurahan.setKecamatan(kecamatan);
        kelurahanRepository.save(kelurahan);
        return WilayahMapper.mapKelurahanToKelurahanResponseDto(kelurahan);
    }

    @Override
    public KelurahanResponseDto getKelurahanById(String kelurahanId) throws KelurahanNotFoundException {
        Kelurahan kelurahan = getKelurahan(kelurahanId);
        return WilayahMapper.mapKelurahanToKelurahanResponseDto(kelurahan);
    }

    @Override
    public KelurahanResponseDto updateKelurahan(String kelurahanId, UpdateKelurahanRequestDto updateKelurahanRequestDto) throws KecamatanNotFoundException, KelurahanNotFoundException {
        Kecamatan kecamatan = kecamatanRepository.findById(updateKelurahanRequestDto.getKecamatanId()).orElseThrow(() -> new KecamatanNotFoundException("Kecamatan ID ["+updateKelurahanRequestDto.getKecamatanId()+"] not found"));
        Kelurahan kelurahan = getKelurahan(kelurahanId);
        kelurahan.setCode(updateKelurahanRequestDto.getCode());
        kelurahan.setName(updateKelurahanRequestDto.getName());
        kelurahan.setKecamatan(kecamatan);
        kelurahan.setUpdatedAt(LocalDateTime.now());
        kelurahanRepository.save(kelurahan);
        return WilayahMapper.mapKelurahanToKelurahanResponseDto(kelurahan);
    }

    @Override
    public ListKelurahanResponseDto getAllKelurahan(ListKelurahanRequestDto listKelurahanRequestDto) {
        int pageNo = listKelurahanRequestDto.getPageNo();
        int pageSize = listKelurahanRequestDto.getPageSize();
        String sortBy = listKelurahanRequestDto.getSortBy();
        String sortDir = listKelurahanRequestDto.getSortDir();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Kelurahan> kelurahanPage = kelurahanRepository.findAll(pageable);
        List<Kelurahan> kelurahanList = kelurahanPage.getContent();
        List<KelurahanResponseDto> kelurahanResponseDtoList = WilayahMapper.mapKelurahanListToKelurahanDtoList(kelurahanList);

        ListKelurahanResponseDto dto = new ListKelurahanResponseDto();
        dto.setKelurahanResponses(kelurahanResponseDtoList);
        dto.setPageNo(kelurahanPage.getNumber());
        dto.setPageSize(kelurahanPage.getSize());
        dto.setTotalElements(kelurahanPage.getTotalElements());
        dto.setTotalPages(kelurahanPage.getTotalPages());
        dto.setLast(kelurahanPage.isLast());
        return dto;
    }

    @Override
    public void deleteKelurahan(String kelurahanId) {
        kelurahanRepository.deleteById(kelurahanId);
    }

    @Override
    public KelurahanResponseDto getKelurahanByName(String name) throws KelurahanNotFoundException {
        Kelurahan kelurahan = kelurahanRepository.findAllByNameIgnoreCase(name).orElseThrow(() -> new KelurahanNotFoundException("Kelurahan name ["+name+"] not found"));
        return WilayahMapper.mapKelurahanToKelurahanResponseDto(kelurahan);
    }

    @Override
    public KelurahanResponseDto getKelurahanByCode(String code) throws KelurahanNotFoundException {
        Kelurahan kelurahan = kelurahanRepository.findAllByCode(code).orElseThrow(() -> new KelurahanNotFoundException("Kelurahan code ["+code+"] not found"));
        return WilayahMapper.mapKelurahanToKelurahanResponseDto(kelurahan);
    }

    @Override
    public List<KelurahanResponseDto> getKelurahanByNameContains(String name) {
        List<Kelurahan> kelurahanList = kelurahanRepository.findAllByNameContainingIgnoreCase(name);
        return WilayahMapper.mapKelurahanListToKelurahanDtoList(kelurahanList);
    }

    @Override
    public List<KelurahanResponseDto> getKelurahanByKecamatanId(String kecamatanId) {
        List<Kelurahan> kelurahanList = kelurahanRepository.findAllByKecamatanId(kecamatanId);
        return WilayahMapper.mapKelurahanListToKelurahanDtoList(kelurahanList);
    }

    private Kelurahan getKelurahan(String kelurahanId) throws KelurahanNotFoundException {
        return kelurahanRepository.findById(kelurahanId).orElseThrow(() -> new KelurahanNotFoundException("Kelurahan ID ["+ kelurahanId +"] not found"));
    }
}

