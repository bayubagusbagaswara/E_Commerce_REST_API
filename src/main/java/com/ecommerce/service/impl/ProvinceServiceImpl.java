package com.ecommerce.service.impl;

import com.ecommerce.entity.region.Province;
import com.ecommerce.dto.region.province.*;
import com.ecommerce.exception.ProvinceNotFoundException;
import com.ecommerce.mapper.region.ProvinceMapper;
import com.ecommerce.repository.region.ProvinceRepository;
import com.ecommerce.service.region.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;;
import java.util.List;

@Service
@Transactional
public class ProvinceServiceImpl implements ProvinceService {

    private final ProvinceRepository provinceRepository;
    private final ProvinceMapper provinceMapper;

    @Autowired
    public ProvinceServiceImpl(ProvinceRepository provinceRepository, ProvinceMapper provinceMapper) {
        this.provinceRepository = provinceRepository;
        this.provinceMapper = provinceMapper;
    }

    @Override
    public ProvinceDTO createProvince(CreateProvinceRequestDTO dto) {
        Province province = new Province();
        province.setCode(dto.getCode());
        province.setName(dto.getName());
        province.setCreatedAt(Instant.now());
        provinceRepository.save(province);
        return provinceMapper.fromProvince(province);
    }

    @Override
    public ProvinceDTO getProvinceById(String provinceId) {
        Province province = provinceRepository.findById(provinceId).orElseThrow(() -> new ProvinceNotFoundException("id", provinceId));
        return provinceMapper.fromProvince(province);
    }

    @Override
    public ListProvinceResponseDTO getAllProvinces(ListProvinceRequestDTO requestDTO) {
        Integer pageNo = requestDTO.getPageNo();
        Integer pageSize = requestDTO.getPageSize();
        String sortBy = requestDTO.getSortBy();
        String sortDir = requestDTO.getSortDir();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Province> provincePage = provinceRepository.findAll(pageable);
        List<Province> provinceList = provincePage.getContent();

        List<ProvinceDTO> provinceDTOList = provinceMapper.fromProvinceList(provinceList);

        return new ListProvinceResponseDTO(
                provinceDTOList, provincePage.getNumber(), provincePage.getSize(),
                provincePage.getTotalElements(), provincePage.getTotalPages(), provincePage.isLast()
        );
    }

    @Override
    public ProvinceDTO updateProvince(String provinceId, UpdateProvinceRequestDTO provinceDTO) {
        Province province = provinceRepository.findById(provinceId).orElseThrow(() -> new ProvinceNotFoundException("id", provinceId));
        province.setCode(provinceDTO.getCode());
        province.setName(provinceDTO.getName());
        province.setUpdatedAt(Instant.now());
        provinceRepository.save(province);
        return provinceMapper.fromProvince(province);
    }

    @Override
    public void deleteProvince(String provinceId) {
        final Province province = provinceRepository.findById(provinceId).orElseThrow(() -> new ProvinceNotFoundException("id", provinceId));
        provinceRepository.delete(province);
    }

    @Override
    public ProvinceDTO getProvinceByName(String name) {
        Province province = provinceRepository.findAllByNameIgnoreCase(name).orElseThrow(() -> new ProvinceNotFoundException("name", name));
        return provinceMapper.fromProvince(province);
    }

    @Override
    public ProvinceDTO getProvinceByCode(String code) {
        Province province = provinceRepository.findAllByCode(code).orElseThrow(() -> new ProvinceNotFoundException("code", code));
        return provinceMapper.fromProvince(province);
    }

    @Override
    public List<ProvinceDTO> getProvinceByNameContains(String name) {
        List<Province> provinceList = provinceRepository.findAllByNameContainingIgnoreCase(name);
        return provinceMapper.fromProvinceList(provinceList);
    }

}
