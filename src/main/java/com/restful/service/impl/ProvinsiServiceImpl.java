package com.restful.service.impl;

import com.restful.dto.WilayahMapper;
import com.restful.dto.provinsi.*;
import com.restful.entity.wilayah.Provinsi;
import com.restful.exception.ProvinsiNotFoundException;
import com.restful.repository.ProvinsiRepository;
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
public class ProvinsiServiceImpl implements ProvinsiService {

    private final ProvinsiRepository provinsiRepository;

    public ProvinsiServiceImpl(ProvinsiRepository provinsiRepository) {
        this.provinsiRepository = provinsiRepository;
    }

    @Override
    public ProvinsiResponseDto createProvinsi(CreateProvinsiRequestDto createProvinsiRequestDto) {
        Provinsi provinsi = new Provinsi();
        provinsi.setCode(createProvinsiRequestDto.getCode());
        provinsi.setName(createProvinsiRequestDto.getName());
        provinsi.setCreatedDate(LocalDateTime.now());
        provinsiRepository.save(provinsi);
        return WilayahMapper.mapProvinsiToProvinsiResponseDto(provinsi);
    }

    @Override
    public ProvinsiResponseDto getProvinsiById(String provinsiId) throws ProvinsiNotFoundException {
        Provinsi provinsi = getProvinsi(provinsiId);
        return WilayahMapper.mapProvinsiToProvinsiResponseDto(provinsi);
    }

    @Override
    public ListProvinsiResponseDto getAllProvinsi(ListProvinsiRequestDto listProvinsiRequestDto) {
        int pageNo = listProvinsiRequestDto.getPageNo();
        int pageSize = listProvinsiRequestDto.getPageSize();
        String sortBy = listProvinsiRequestDto.getSortBy();
        String sortDir = listProvinsiRequestDto.getSortDir();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Provinsi> provinsiPage = provinsiRepository.findAll(pageable);
        List<Provinsi> provinsiList = provinsiPage.getContent();

        List<ProvinsiResponseDto> provinsiResponseList = WilayahMapper.mapProvinsiListToProvinsiResponseDtoList(provinsiList);

        ListProvinsiResponseDto dto = new ListProvinsiResponseDto();
        dto.setProvinsiResponses(provinsiResponseList);
        dto.setPageNo(provinsiPage.getNumber());
        dto.setPageSize(provinsiPage.getSize());
        dto.setTotalElements(provinsiPage.getTotalElements());
        dto.setTotalPages(provinsiPage.getTotalPages());
        dto.setLast(provinsiPage.isLast());
        return dto;
    }

    @Override
    public ProvinsiResponseDto updateProvinsi(String provinsiId, UpdateProvinsiRequestDto updateProvinsiRequestDto) throws ProvinsiNotFoundException {
        Provinsi provinsi = getProvinsi(provinsiId);
        provinsi.setCode(updateProvinsiRequestDto.getCode());
        provinsi.setName(updateProvinsiRequestDto.getName());
        provinsi.setUpdatedDate(LocalDateTime.now());
        provinsiRepository.save(provinsi);
        return WilayahMapper.mapProvinsiToProvinsiResponseDto(provinsi);
    }

    @Override
    public void deleteProvinsi(String provinsiId) {
        provinsiRepository.deleteById(provinsiId);
    }

    @Override
    public ProvinsiResponseDto getProvinsiByName(String name) throws ProvinsiNotFoundException {
        Provinsi provinsi = provinsiRepository.findAllByNameIgnoreCase(name).orElseThrow(() -> new ProvinsiNotFoundException("Provinsi name ["+name+"] Not Found"));
        return WilayahMapper.mapProvinsiToProvinsiResponseDto(provinsi);
    }

    @Override
    public ProvinsiResponseDto getProvinsiByCode(String code) throws ProvinsiNotFoundException {
        Provinsi provinsi = provinsiRepository.findAllByCode(code).orElseThrow(() -> new ProvinsiNotFoundException("Provinsi code ["+code+"] Not Found"));
        return WilayahMapper.mapProvinsiToProvinsiResponseDto(provinsi);
    }

    @Override
    public List<ProvinsiResponseDto> getProvinsiByNameContains(String name) {
        List<Provinsi> provinsiList = provinsiRepository.findAllByNameContainingIgnoreCase(name);
        return WilayahMapper.mapProvinsiListToProvinsiResponseDtoList(provinsiList);
    }

    private Provinsi getProvinsi(String provinsiId) throws ProvinsiNotFoundException {
        return provinsiRepository.findById(provinsiId).orElseThrow(() -> new ProvinsiNotFoundException("Provinsi ID ["+ provinsiId +"] Not Found"));
    }
}
