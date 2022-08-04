package com.ecommerce.service.impl;

import com.ecommerce.entity.region.Province;
import com.ecommerce.dto.kota.*;
import com.ecommerce.dto.region.district.*;
import com.ecommerce.entity.region.District;
import com.ecommerce.exception.DistrictNotFoundException;
import com.ecommerce.exception.ProvinceNotFoundException;
import com.ecommerce.repository.DistrictRepository;
import com.ecommerce.repository.ProvinceRepository;
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

    private final DistrictRepository districtRepository;
    private final ProvinceRepository provinceRepository;
    private final RegionMapper regionMapper;

    public DistrictServiceImpl(DistrictRepository districtRepository, ProvinceRepository provinceRepository, RegionMapper regionMapper) {
        this.districtRepository = districtRepository;
        this.provinceRepository = provinceRepository;
        this.regionMapper = regionMapper;
    }

    @Override
    public DistrictDTO createKota(CreateDistrictRequestDTO createKotaRequest) throws ProvinceNotFoundException {
        Province province = provinceRepository.findById(createKotaRequest.getProvinsiId())
                .orElseThrow(() -> new ProvinceNotFoundException("Provinsi ID [" + createKotaRequest.getProvinsiId() + "] not found"));
        District district = new District();
        district.setCode(createKotaRequest.getCode());
        district.setName(createKotaRequest.getName());
        district.setProvince(province);
        district.setCreatedDate(LocalDateTime.now());
        districtRepository.save(district);
        return regionMapper.mapToKotaResponse(district);
    }

    @Override
    public DistrictDTO getKotaById(String id) throws DistrictNotFoundException {
        District district = districtRepository.findById(id)
                .orElseThrow(() -> new DistrictNotFoundException("Kota ID [" + id + "] not found"));
        return regionMapper.mapToKotaResponse(district);
    }

    @Override
    public DistrictDTO updateKota(String id, UpdateDistrictRequestDTO updateKotaRequest) throws DistrictNotFoundException, ProvinceNotFoundException {
        Province province = provinceRepository.findById(updateKotaRequest.getProvinsiId())
                .orElseThrow(() -> new ProvinceNotFoundException("Provinsi ID [" + updateKotaRequest.getProvinsiId() + "] not found"));
        District district = districtRepository.findById(id)
                .orElseThrow(() -> new DistrictNotFoundException("Kota ID [" + id + "] not found"));
        district.setCode(updateKotaRequest.getCode());
        district.setName(updateKotaRequest.getName());
        district.setProvince(province);
        district.setUpdatedDate(LocalDateTime.now());
        districtRepository.save(district);
        return regionMapper.mapToKotaResponse(district);
    }

    @Override
    public void deleteKota(String id) throws DistrictNotFoundException {
        final District district = districtRepository.findById(id)
                .orElseThrow(() -> new DistrictNotFoundException("Kota ID [" + id + "] not found"));
        districtRepository.delete(district);
    }

    @Override
    public ListDistrictResponseDTO getAllKota(ListDistrictRequestDTO listKotaRequest) {
        Integer pageNo = listKotaRequest.getPageNo();
        Integer pageSize = listKotaRequest.getPageSize();
        String sortBy = listKotaRequest.getSortBy();
        String sortDir = listKotaRequest.getSortDir();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<District> kotaPage = districtRepository.findAll(pageable);
        List<District> districtList = kotaPage.getContent();

        List<DistrictDTO> kotaResponseList = regionMapper.mapToKotaResponseList(districtList);

        ListDistrictResponseDTO listKotaResponse = new ListDistrictResponseDTO();
        listKotaResponse.setKotaList(kotaResponseList);
        listKotaResponse.setPageNo(kotaPage.getNumber());
        listKotaResponse.setPageSize(kotaPage.getSize());
        listKotaResponse.setTotalElements(kotaPage.getTotalElements());
        listKotaResponse.setTotalPages(kotaPage.getTotalPages());
        listKotaResponse.setLast(kotaPage.isLast());
        return listKotaResponse;
    }

    @Override
    public DistrictDTO getKotaByName(String name) throws DistrictNotFoundException {
        District district = districtRepository.findAllByNameIgnoreCase(name).orElseThrow(() -> new DistrictNotFoundException("Kota name [" + name + "] not found"));
        return regionMapper.mapToKotaResponse(district);
    }

    @Override
    public DistrictDTO getKotaByCode(String code) throws DistrictNotFoundException {
        District district = districtRepository.findAllByCode(code).orElseThrow(() -> new DistrictNotFoundException("Kota code [" + code + "] not found"));
        return regionMapper.mapToKotaResponse(district);
    }

    @Override
    public List<DistrictDTO> getKotaByNameContains(String name) {
        List<District> districtList = districtRepository.findAllByNameContainingIgnoreCase(name);
        return regionMapper.mapToKotaResponseList(districtList);
    }

    @Override
    public List<DistrictDTO> getKotaByProvinsiId(String provinsiId) {
        List<District> districtList = districtRepository.findAllByProvinsiId(provinsiId);
        return regionMapper.mapToKotaResponseList(districtList);
    }

}
