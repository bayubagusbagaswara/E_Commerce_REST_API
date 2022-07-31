package com.restful.service.impl;

import com.restful.mapper.WilayahMapper;
import com.restful.dto.kota.*;
import com.restful.dto.region.kota.*;
import com.restful.entity.wilayah.Kota;
import com.restful.entity.wilayah.Provinsi;
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
    private final WilayahMapper wilayahMapper;

    public KotaServiceImpl(KotaRepository kotaRepository, ProvinsiRepository provinsiRepository, WilayahMapper wilayahMapper) {
        this.kotaRepository = kotaRepository;
        this.provinsiRepository = provinsiRepository;
        this.wilayahMapper = wilayahMapper;
    }

    @Override
    public KotaResponseDto createKota(CreateKotaRequestDto createKotaRequest) throws ProvinsiNotFoundException {
        Provinsi provinsi = provinsiRepository.findById(createKotaRequest.getProvinsiId())
                .orElseThrow(() -> new ProvinsiNotFoundException("Provinsi ID [" + createKotaRequest.getProvinsiId() + "] not found"));
        Kota kota = new Kota();
        kota.setCode(createKotaRequest.getCode());
        kota.setName(createKotaRequest.getName());
        kota.setProvinsi(provinsi);
        kota.setCreatedDate(LocalDateTime.now());
        kotaRepository.save(kota);
        return wilayahMapper.mapToKotaResponse(kota);
    }

    @Override
    public KotaResponseDto getKotaById(String id) throws KotaNotFoundException {
        Kota kota = kotaRepository.findById(id)
                .orElseThrow(() -> new KotaNotFoundException("Kota ID [" + id + "] not found"));
        return wilayahMapper.mapToKotaResponse(kota);
    }

    @Override
    public KotaResponseDto updateKota(String id, UpdateKotaRequestDto updateKotaRequest) throws KotaNotFoundException, ProvinsiNotFoundException {
        Provinsi provinsi = provinsiRepository.findById(updateKotaRequest.getProvinsiId())
                .orElseThrow(() -> new ProvinsiNotFoundException("Provinsi ID [" + updateKotaRequest.getProvinsiId() + "] not found"));
        Kota kota = kotaRepository.findById(id)
                .orElseThrow(() -> new KotaNotFoundException("Kota ID [" + id + "] not found"));
        kota.setCode(updateKotaRequest.getCode());
        kota.setName(updateKotaRequest.getName());
        kota.setProvinsi(provinsi);
        kota.setUpdatedDate(LocalDateTime.now());
        kotaRepository.save(kota);
        return wilayahMapper.mapToKotaResponse(kota);
    }

    @Override
    public void deleteKota(String id) throws KotaNotFoundException {
        final Kota kota = kotaRepository.findById(id)
                .orElseThrow(() -> new KotaNotFoundException("Kota ID [" + id + "] not found"));
        kotaRepository.delete(kota);
    }

    @Override
    public ListKotaResponseDto getAllKota(ListKotaRequestDto listKotaRequest) {
        Integer pageNo = listKotaRequest.getPageNo();
        Integer pageSize = listKotaRequest.getPageSize();
        String sortBy = listKotaRequest.getSortBy();
        String sortDir = listKotaRequest.getSortDir();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Kota> kotaPage = kotaRepository.findAll(pageable);
        List<Kota> kotaList = kotaPage.getContent();

        List<KotaResponseDto> kotaResponseList = wilayahMapper.mapToKotaResponseList(kotaList);

        ListKotaResponseDto listKotaResponse = new ListKotaResponseDto();
        listKotaResponse.setKotaList(kotaResponseList);
        listKotaResponse.setPageNo(kotaPage.getNumber());
        listKotaResponse.setPageSize(kotaPage.getSize());
        listKotaResponse.setTotalElements(kotaPage.getTotalElements());
        listKotaResponse.setTotalPages(kotaPage.getTotalPages());
        listKotaResponse.setLast(kotaPage.isLast());
        return listKotaResponse;
    }

    @Override
    public KotaResponseDto getKotaByName(String name) throws KotaNotFoundException {
        Kota kota = kotaRepository.findAllByNameIgnoreCase(name).orElseThrow(() -> new KotaNotFoundException("Kota name [" + name + "] not found"));
        return wilayahMapper.mapToKotaResponse(kota);
    }

    @Override
    public KotaResponseDto getKotaByCode(String code) throws KotaNotFoundException {
        Kota kota = kotaRepository.findAllByCode(code).orElseThrow(() -> new KotaNotFoundException("Kota code [" + code + "] not found"));
        return wilayahMapper.mapToKotaResponse(kota);
    }

    @Override
    public List<KotaResponseDto> getKotaByNameContains(String name) {
        List<Kota> kotaList = kotaRepository.findAllByNameContainingIgnoreCase(name);
        return wilayahMapper.mapToKotaResponseList(kotaList);
    }

    @Override
    public List<KotaResponseDto> getKotaByProvinsiId(String provinsiId) {
        List<Kota> kotaList = kotaRepository.findAllByProvinsiId(provinsiId);
        return wilayahMapper.mapToKotaResponseList(kotaList);
    }

}
