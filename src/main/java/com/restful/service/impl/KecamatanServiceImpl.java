package com.restful.service.impl;

import com.restful.dto.WilayahMapper;
import com.restful.dto.kecamatan.*;
import com.restful.entity.address.Kecamatan;
import com.restful.entity.address.Kota;
import com.restful.exception.KecamatanNotFoundException;
import com.restful.exception.KotaNotFoundException;
import com.restful.repository.KecamatanRepository;
import com.restful.repository.KotaRepository;
import com.restful.service.KecamatanService;
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
public class KecamatanServiceImpl implements KecamatanService {

    private final KecamatanRepository kecamatanRepository;
    private final KotaRepository kotaRepository;

    public KecamatanServiceImpl(KecamatanRepository kecamatanRepository, KotaRepository kotaRepository) {
        this.kecamatanRepository = kecamatanRepository;
        this.kotaRepository = kotaRepository;
    }

    @Override
    public KecamatanResponseDto createKecamatan(CreateKecamatanRequestDto createKecamatanRequestDto) throws KotaNotFoundException {
        Kota kota = kotaRepository.findById(createKecamatanRequestDto.getKotaId()).orElseThrow(() -> new KotaNotFoundException("Kota ID ["+createKecamatanRequestDto.getKotaId()+" not found"));
        Kecamatan kecamatan = new Kecamatan();
        kecamatan.setCode(createKecamatanRequestDto.getCode());
        kecamatan.setName(createKecamatanRequestDto.getName());
        kecamatan.setKota(kota);
        kecamatan.setCreatedAt(LocalDateTime.now());
        kecamatanRepository.save(kecamatan);
        return WilayahMapper.mapKecamatanToKecamatanResponseDto(kecamatan);
    }

    @Override
    public KecamatanResponseDto getKecamatanById(String kecamatanId) throws KecamatanNotFoundException {
        Kecamatan kecamatan = getKecamatan(kecamatanId);
        return WilayahMapper.mapKecamatanToKecamatanResponseDto(kecamatan);
    }

    @Override
    public KecamatanResponseDto updateKecamatan(String kecamatanId, UpdateKecamatanRequestDto updateKecamatanRequestDto) throws KotaNotFoundException, KecamatanNotFoundException {
        Kota kota = kotaRepository.findById(updateKecamatanRequestDto.getKotaId()).orElseThrow(() -> new KotaNotFoundException("Kota ID ["+updateKecamatanRequestDto.getKotaId()+"] not found"));
        Kecamatan kecamatan = getKecamatan(kecamatanId);
        kecamatan.setCode(updateKecamatanRequestDto.getCode());
        kecamatan.setName(updateKecamatanRequestDto.getName());
        kecamatan.setKota(kota);
        kecamatan.setUpdatedAt(LocalDateTime.now());
        kecamatanRepository.save(kecamatan);
        return WilayahMapper.mapKecamatanToKecamatanResponseDto(kecamatan);
    }

    @Override
    public void deleteKecamatan(String kecamatanId) {
        kecamatanRepository.deleteById(kecamatanId);
    }

    @Override
    public ListKecamatanResponseDto getAllKecamatan(ListKecamatanRequestDto listKecamatanRequestDto) {
        int pageNo = listKecamatanRequestDto.getPageNo();
        int pageSize = listKecamatanRequestDto.getPageSize();
        String sortBy = listKecamatanRequestDto.getSortBy();
        String sortDir = listKecamatanRequestDto.getSortDir();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Kecamatan> kecamatanPage = kecamatanRepository.findAll(pageable);
        List<Kecamatan> kecamatanList = kecamatanPage.getContent();

        List<KecamatanResponseDto> kecamatanResponseDtoList = WilayahMapper.mapKecamatanListToKecamatanDtoList(kecamatanList);

        ListKecamatanResponseDto dto = new ListKecamatanResponseDto();
        dto.setKecamatanResponses(kecamatanResponseDtoList);
        dto.setPageNo(kecamatanPage.getNumber());
        dto.setPageSize(kecamatanPage.getSize());
        dto.setTotalElements(kecamatanPage.getTotalElements());
        dto.setTotalPages(kecamatanPage.getTotalPages());
        dto.setLast(kecamatanPage.isLast());
        return dto;
    }

    @Override
    public KecamatanResponseDto getKecamatanByName(String name) throws KecamatanNotFoundException {
        Kecamatan kecamatan = kecamatanRepository.findAllByNameIgnoreCase(name).orElseThrow(() -> new KecamatanNotFoundException("Kecamatan name ["+name+"] not found"));
        return WilayahMapper.mapKecamatanToKecamatanResponseDto(kecamatan);
    }

    @Override
    public KecamatanResponseDto getKecamatanByCode(String code) throws KecamatanNotFoundException {
        Kecamatan kecamatan = kecamatanRepository.findAllByCode(code).orElseThrow(() -> new KecamatanNotFoundException("Kecamatan code ["+code+"] not found"));
        return WilayahMapper.mapKecamatanToKecamatanResponseDto(kecamatan);
    }

    @Override
    public List<KecamatanResponseDto> getKecamatanByNameContains(String name) {
        List<Kecamatan> kecamatanList = kecamatanRepository.findAllByNameContainingIgnoreCase(name);
        return WilayahMapper.mapKecamatanListToKecamatanDtoList(kecamatanList);
    }

    @Override
    public List<KecamatanResponseDto> getKecamatanByKotaId(String kotaId) {
        List<Kecamatan> kecamatanList = kecamatanRepository.findAllByKotaId(kotaId);
        return WilayahMapper.mapKecamatanListToKecamatanDtoList(kecamatanList);
    }

    private Kecamatan getKecamatan(String kecamatanId) throws KecamatanNotFoundException {
        return kecamatanRepository.findById(kecamatanId).orElseThrow(() -> new KecamatanNotFoundException("Kecamatan ID ["+ kecamatanId +"] not found"));
    }
}
