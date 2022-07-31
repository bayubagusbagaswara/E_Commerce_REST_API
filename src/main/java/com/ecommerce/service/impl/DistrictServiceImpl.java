package com.ecommerce.service.impl;

import com.ecommerce.entity.region.Province;
import com.ecommerce.mapper.WilayahMapper;
import com.ecommerce.dto.kota.*;
import com.ecommerce.dto.region.district.*;
import com.ecommerce.entity.region.District;
import com.ecommerce.exception.KotaNotFoundException;
import com.ecommerce.exception.ProvinsiNotFoundException;
import com.ecommerce.repository.KotaRepository;
import com.ecommerce.repository.ProvinsiRepository;
import com.ecommerce.service.region.DistrictService;
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
public class DistrictServiceImpl implements DistrictService {

    private final KotaRepository kotaRepository;
    private final ProvinsiRepository provinsiRepository;
    private final WilayahMapper wilayahMapper;

    public DistrictServiceImpl(KotaRepository kotaRepository, ProvinsiRepository provinsiRepository, WilayahMapper wilayahMapper) {
        this.kotaRepository = kotaRepository;
        this.provinsiRepository = provinsiRepository;
        this.wilayahMapper = wilayahMapper;
    }

    @Override
    public KotaResponseDto createKota(CreateKotaRequestDto createKotaRequest) throws ProvinsiNotFoundException {
        Province province = provinsiRepository.findById(createKotaRequest.getProvinsiId())
                .orElseThrow(() -> new ProvinsiNotFoundException("Provinsi ID [" + createKotaRequest.getProvinsiId() + "] not found"));
        District district = new District();
        district.setCode(createKotaRequest.getCode());
        district.setName(createKotaRequest.getName());
        district.setProvince(province);
        district.setCreatedDate(LocalDateTime.now());
        kotaRepository.save(district);
        return wilayahMapper.mapToKotaResponse(district);
    }

    @Override
    public KotaResponseDto getKotaById(String id) throws KotaNotFoundException {
        District district = kotaRepository.findById(id)
                .orElseThrow(() -> new KotaNotFoundException("Kota ID [" + id + "] not found"));
        return wilayahMapper.mapToKotaResponse(district);
    }

    @Override
    public KotaResponseDto updateKota(String id, UpdateKotaRequestDto updateKotaRequest) throws KotaNotFoundException, ProvinsiNotFoundException {
        Province province = provinsiRepository.findById(updateKotaRequest.getProvinsiId())
                .orElseThrow(() -> new ProvinsiNotFoundException("Provinsi ID [" + updateKotaRequest.getProvinsiId() + "] not found"));
        District district = kotaRepository.findById(id)
                .orElseThrow(() -> new KotaNotFoundException("Kota ID [" + id + "] not found"));
        district.setCode(updateKotaRequest.getCode());
        district.setName(updateKotaRequest.getName());
        district.setProvince(province);
        district.setUpdatedDate(LocalDateTime.now());
        kotaRepository.save(district);
        return wilayahMapper.mapToKotaResponse(district);
    }

    @Override
    public void deleteKota(String id) throws KotaNotFoundException {
        final District district = kotaRepository.findById(id)
                .orElseThrow(() -> new KotaNotFoundException("Kota ID [" + id + "] not found"));
        kotaRepository.delete(district);
    }

    @Override
    public ListKotaResponseDto getAllKota(ListKotaRequestDto listKotaRequest) {
        Integer pageNo = listKotaRequest.getPageNo();
        Integer pageSize = listKotaRequest.getPageSize();
        String sortBy = listKotaRequest.getSortBy();
        String sortDir = listKotaRequest.getSortDir();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<District> kotaPage = kotaRepository.findAll(pageable);
        List<District> districtList = kotaPage.getContent();

        List<KotaResponseDto> kotaResponseList = wilayahMapper.mapToKotaResponseList(districtList);

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
        District district = kotaRepository.findAllByNameIgnoreCase(name).orElseThrow(() -> new KotaNotFoundException("Kota name [" + name + "] not found"));
        return wilayahMapper.mapToKotaResponse(district);
    }

    @Override
    public KotaResponseDto getKotaByCode(String code) throws KotaNotFoundException {
        District district = kotaRepository.findAllByCode(code).orElseThrow(() -> new KotaNotFoundException("Kota code [" + code + "] not found"));
        return wilayahMapper.mapToKotaResponse(district);
    }

    @Override
    public List<KotaResponseDto> getKotaByNameContains(String name) {
        List<District> districtList = kotaRepository.findAllByNameContainingIgnoreCase(name);
        return wilayahMapper.mapToKotaResponseList(districtList);
    }

    @Override
    public List<KotaResponseDto> getKotaByProvinsiId(String provinsiId) {
        List<District> districtList = kotaRepository.findAllByProvinsiId(provinsiId);
        return wilayahMapper.mapToKotaResponseList(districtList);
    }

}
