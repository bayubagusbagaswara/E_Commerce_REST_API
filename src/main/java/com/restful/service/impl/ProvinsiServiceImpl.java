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
    private final WilayahMapper wilayahMapper;

    public ProvinsiServiceImpl(ProvinsiRepository provinsiRepository, WilayahMapper wilayahMapper) {
        this.provinsiRepository = provinsiRepository;
        this.wilayahMapper = wilayahMapper;
    }

    @Override
    public ProvinsiResponseDto createProvinsi(CreateProvinsiRequestDto createProvinsiRequest) {
        Provinsi provinsi = new Provinsi();
        provinsi.setCode(createProvinsiRequest.getCode());
        provinsi.setName(createProvinsiRequest.getName());
        provinsi.setCreatedDate(LocalDateTime.now());
        provinsiRepository.save(provinsi);
        return wilayahMapper.mapToProvinsiResponse(provinsi);
    }

    @Override
    public ProvinsiResponseDto getProvinsiById(String id) throws ProvinsiNotFoundException {
        Provinsi provinsi = provinsiRepository.findById(id)
                .orElseThrow(() -> new ProvinsiNotFoundException("Provinsi ID ["+ id +"] not found"));
        return wilayahMapper.mapToProvinsiResponse(provinsi);
    }

    @Override
    public ListProvinsiResponseDto getAllProvinsi(ListProvinsiRequestDto listProvinsiRequest) {

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
        Page<Provinsi> provinsiPage = provinsiRepository.findAll(pageable);


        List<Provinsi> provinsiList = provinsiPage.getContent();

        List<ProvinsiResponseDto> provinsiResponseList = wilayahMapper.mapToProvinsiResponseList(provinsiList);

        ListProvinsiResponseDto listProvinsiResponse = new ListProvinsiResponseDto();
        listProvinsiResponse.setProvinsiList(provinsiResponseList);
        listProvinsiResponse.setPageNo(provinsiPage.getNumber());
        listProvinsiResponse.setPageSize(provinsiPage.getSize());
        listProvinsiResponse.setTotalElements(provinsiPage.getTotalElements());
        listProvinsiResponse.setTotalPages(provinsiPage.getTotalPages());
        listProvinsiResponse.setLast(provinsiPage.isLast());
        return listProvinsiResponse;
    }

    @Override
    public ProvinsiResponseDto updateProvinsi(String id, UpdateProvinsiRequestDto updateProvinsiRequest) throws ProvinsiNotFoundException {
        Provinsi provinsi = provinsiRepository.findById(id)
                .orElseThrow(() -> new ProvinsiNotFoundException("Provinsi ID ["+ id +"] not found"));
        provinsi.setCode(updateProvinsiRequest.getCode());
        provinsi.setName(updateProvinsiRequest.getName());
        provinsi.setUpdatedDate(LocalDateTime.now());
        provinsiRepository.save(provinsi);
        return wilayahMapper.mapToProvinsiResponse(provinsi);
    }

    @Override
    public void deleteProvinsi(String id) throws ProvinsiNotFoundException {
        final Provinsi provinsi = provinsiRepository.findById(id)
                .orElseThrow(() -> new ProvinsiNotFoundException("Provinsi ID [" + id + "] not found"));
        provinsiRepository.delete(provinsi);
    }

    @Override
    public ProvinsiResponseDto getProvinsiByName(String name) throws ProvinsiNotFoundException {
        Provinsi provinsi = provinsiRepository.findAllByNameIgnoreCase(name).orElseThrow(() -> new ProvinsiNotFoundException("Provinsi name [" + name + "] not found"));
        return wilayahMapper.mapToProvinsiResponse(provinsi);
    }

    @Override
    public ProvinsiResponseDto getProvinsiByCode(String code) throws ProvinsiNotFoundException {
        Provinsi provinsi = provinsiRepository.findAllByCode(code).orElseThrow(() -> new ProvinsiNotFoundException("Provinsi code [" + code + "] not found"));
        return wilayahMapper.mapToProvinsiResponse(provinsi);
    }

    @Override
    public List<ProvinsiResponseDto> getProvinsiByNameContains(String name) {
        List<Provinsi> provinsiList = provinsiRepository.findAllByNameContainingIgnoreCase(name);
        return wilayahMapper.mapToProvinsiResponseList(provinsiList);
    }

}
