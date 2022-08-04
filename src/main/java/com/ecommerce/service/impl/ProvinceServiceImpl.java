package com.ecommerce.service.impl;

import com.ecommerce.entity.region.Province;
import com.ecommerce.mapper.RegionMapper;
import com.ecommerce.dto.provinsi.*;
import com.ecommerce.dto.region.province.*;
import com.ecommerce.exception.ProvinsiNotFoundException;
import com.ecommerce.repository.ProvinceRepository;
import com.ecommerce.service.region.ProvinceService;
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
public class ProvinceServiceImpl implements ProvinceService {

    private final ProvinceRepository provinceRepository;
    private final RegionMapper regionMapper;

    public ProvinceServiceImpl(ProvinceRepository provinceRepository, RegionMapper regionMapper) {
        this.provinceRepository = provinceRepository;
        this.regionMapper = regionMapper;
    }

    @Override
    public ProvinceDTO createProvinsi(CreateProvinceRequestDTO createProvinsiRequest) {
        Province province = new Province();
        province.setCode(createProvinsiRequest.getCode());
        province.setName(createProvinsiRequest.getName());
        province.setCreatedDate(LocalDateTime.now());
        provinceRepository.save(province);
        return regionMapper.mapToProvinsiResponse(province);
    }

    @Override
    public ProvinceDTO getProvinsiById(String id) throws ProvinsiNotFoundException {
        Province province = provinceRepository.findById(id)
                .orElseThrow(() -> new ProvinsiNotFoundException("Provinsi ID ["+ id +"] not found"));
        return regionMapper.mapToProvinsiResponse(province);
    }

    @Override
    public ListProvinceResponseDTO getAllProvinsi(ListProvinceRequestDTO listProvinsiRequest) {

        // ada parameter searchBy
        Integer pageNo = listProvinsiRequest.getPageNo();
        Integer pageSize = listProvinsiRequest.getPageSize();
        String sortBy = listProvinsiRequest.getSortBy();
        String sortDir = listProvinsiRequest.getSortDir();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        // intinya disini
        // karena findAll maka kita akan mendapatkan semua data nya
        // tapi kita ingin find ini bergantung terhadap nilai dari properti searchBy
        // jadi pencarian data berdasarkan query where ...


        // if (searchByName.isExist) {
        // maka provinsiRepository.findAllByName
        // }
        Page<Province> provinsiPage = provinceRepository.findAll(pageable);


        List<Province> provinceList = provinsiPage.getContent();

        List<ProvinceDTO> provinsiResponseList = regionMapper.mapToProvinsiResponseList(provinceList);

        ListProvinceResponseDTO listProvinsiResponse = new ListProvinceResponseDTO();
        listProvinsiResponse.setProvinsiList(provinsiResponseList);
        listProvinsiResponse.setPageNo(provinsiPage.getNumber());
        listProvinsiResponse.setPageSize(provinsiPage.getSize());
        listProvinsiResponse.setTotalElements(provinsiPage.getTotalElements());
        listProvinsiResponse.setTotalPages(provinsiPage.getTotalPages());
        listProvinsiResponse.setLast(provinsiPage.isLast());
        return listProvinsiResponse;
    }

    @Override
    public ProvinceDTO updateProvinsi(String id, UpdateProvinceRequestDTO updateProvinsiRequest) throws ProvinsiNotFoundException {
        Province province = provinceRepository.findById(id)
                .orElseThrow(() -> new ProvinsiNotFoundException("Provinsi ID ["+ id +"] not found"));
        province.setCode(updateProvinsiRequest.getCode());
        province.setName(updateProvinsiRequest.getName());
        province.setUpdatedDate(LocalDateTime.now());
        provinceRepository.save(province);
        return regionMapper.mapToProvinsiResponse(province);
    }

    @Override
    public void deleteProvinsi(String id) throws ProvinsiNotFoundException {
        final Province province = provinceRepository.findById(id)
                .orElseThrow(() -> new ProvinsiNotFoundException("Provinsi ID [" + id + "] not found"));
        provinceRepository.delete(province);
    }

    @Override
    public ProvinceDTO getProvinsiByName(String name) throws ProvinsiNotFoundException {
        Province province = provinceRepository.findAllByNameIgnoreCase(name).orElseThrow(() -> new ProvinsiNotFoundException("Provinsi name [" + name + "] not found"));
        return regionMapper.mapToProvinsiResponse(province);
    }

    @Override
    public ProvinceDTO getProvinsiByCode(String code) throws ProvinsiNotFoundException {
        Province province = provinceRepository.findAllByCode(code).orElseThrow(() -> new ProvinsiNotFoundException("Provinsi code [" + code + "] not found"));
        return regionMapper.mapToProvinsiResponse(province);
    }

    @Override
    public List<ProvinceDTO> getProvinsiByNameContains(String name) {
        List<Province> provinceList = provinceRepository.findAllByNameContainingIgnoreCase(name);
        return regionMapper.mapToProvinsiResponseList(provinceList);
    }

}
