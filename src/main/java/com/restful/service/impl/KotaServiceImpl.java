package com.restful.service.impl;

import com.restful.dto.WilayahMapper;
import com.restful.dto.kota.*;
import com.restful.entity.address.Kota;
import com.restful.entity.address.Provinsi;
import com.restful.exception.KotaNotFoundException;
import com.restful.exception.ProvinsiNotFoundException;
import com.restful.repository.KotaRepository;
import com.restful.repository.ProvinsiRepository;
import com.restful.service.KotaService;
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
public class KotaServiceImpl implements KotaService {

    private final KotaRepository kotaRepository;
    private final ProvinsiRepository provinsiRepository;

    public KotaServiceImpl(KotaRepository kotaRepository, ProvinsiRepository provinsiRepository) {
        this.kotaRepository = kotaRepository;
        this.provinsiRepository = provinsiRepository;
    }

    @Override
    public KotaResponseDto createKota(CreateKotaRequestDto createKotaRequestDto) throws ProvinsiNotFoundException {
        Provinsi provinsi = provinsiRepository.findById(createKotaRequestDto.getProvinsiId()).orElseThrow(() -> new ProvinsiNotFoundException("Provinsi ID ["+createKotaRequestDto.getProvinsiId()+"] not found"));
        Kota kota = new Kota();
        kota.setCode(createKotaRequestDto.getCode());
        kota.setName(createKotaRequestDto.getName());
        kota.setProvinsi(provinsi);
        kota.setCreatedAt(LocalDateTime.now());
        kotaRepository.save(kota);
        return WilayahMapper.mapKotaToKotaResponseDto(kota);
    }

    @Override
    public KotaResponseDto getKotaById(String kotaId) throws KotaNotFoundException {
        Kota kota = getKota(kotaId);
        return WilayahMapper.mapKotaToKotaResponseDto(kota);
    }

    @Override
    public KotaResponseDto updateKota(String kotaId, UpdateKotaRequestDto updateKotaRequestDto) throws KotaNotFoundException, ProvinsiNotFoundException {
        Provinsi provinsi = provinsiRepository.findById(updateKotaRequestDto.getProvinsiId()).orElseThrow(() -> new ProvinsiNotFoundException("Provinsi ID ["+updateKotaRequestDto.getProvinsiId()+"] Not Found"));
        Kota kota = getKota(kotaId);
        kota.setCode(updateKotaRequestDto.getCode());
        kota.setName(updateKotaRequestDto.getName());
        kota.setProvinsi(provinsi);
        kota.setUpdatedAt(LocalDateTime.now());
        kotaRepository.save(kota);
        return WilayahMapper.mapKotaToKotaResponseDto(kota);
    }

    @Override
    public void deleteKota(String kotaId) {
        kotaRepository.deleteById(kotaId);
    }

    @Override
    public ListKotaResponseDto getAllKota(ListKotaRequestDto listKotaRequestDto) {
        int pageNo = listKotaRequestDto.getPageNo();
        int pageSize = listKotaRequestDto.getPageSize();
        String sortBy = listKotaRequestDto.getSortBy();
        String sortDir = listKotaRequestDto.getSortDir();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Kota> kotaPage = kotaRepository.findAll(pageable);
        List<Kota> kotaList = kotaPage.getContent();

        List<KotaResponseDto> kotaResponseDtoList = WilayahMapper.mapKotaListToKotaResponseDtoList(kotaList);

        ListKotaResponseDto dto = new ListKotaResponseDto();
        dto.setKotaResponses(kotaResponseDtoList);
        dto.setPageNo(kotaPage.getNumber());
        dto.setPageSize(kotaPage.getSize());
        dto.setTotalElements(kotaPage.getTotalElements());
        dto.setTotalPages(kotaPage.getTotalPages());
        dto.setLast(kotaPage.isLast());
        return dto;
    }

    @Override
    public KotaResponseDto getKotaByName(String name) throws KotaNotFoundException {
        Kota kota = kotaRepository.findAllByNameIgnoreCase(name).orElseThrow(() -> new KotaNotFoundException("Kota name ["+name+"] not found"));
        return WilayahMapper.mapKotaToKotaResponseDto(kota);
    }

    @Override
    public KotaResponseDto getKotaByCode(String code) throws KotaNotFoundException {
        Kota kota = kotaRepository.findAllByCode(code).orElseThrow(() -> new KotaNotFoundException("Kota code ["+code+"] not found"));
        return WilayahMapper.mapKotaToKotaResponseDto(kota);
    }

    @Override
    public List<KotaResponseDto> getKotaByNameContains(String name) {
        List<Kota> kotaList = kotaRepository.findAllByNameContainingIgnoreCase(name);
        return WilayahMapper.mapKotaListToKotaResponseDtoList(kotaList);
    }

    @Override
    public List<KotaResponseDto> getKotaByProvinsiId(String provinsiId) {
        List<Kota> kotaList = kotaRepository.findAllByProvinsiId(provinsiId);
        return WilayahMapper.mapKotaListToKotaResponseDtoList(kotaList);
    }

    private Kota getKota(String kotaId) throws KotaNotFoundException {
        return kotaRepository.findById(kotaId).orElseThrow(() -> new KotaNotFoundException("Kota ID ["+ kotaId +"] Not Found"));
    }
}
