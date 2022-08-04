package com.ecommerce.service.impl.region;

import com.ecommerce.entity.region.Province;
import com.ecommerce.dto.region.district.*;
import com.ecommerce.entity.region.District;
import com.ecommerce.exception.DistrictNotFoundException;
import com.ecommerce.exception.ProvinceNotFoundException;
import com.ecommerce.mapper.region.DistrictMapper;
import com.ecommerce.repository.region.DistrictRepository;
import com.ecommerce.repository.region.ProvinceRepository;
import com.ecommerce.service.region.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@Transactional
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;
    private final ProvinceRepository provinceRepository;
    private final DistrictMapper districtMapper;

    @Autowired
    public DistrictServiceImpl(DistrictRepository districtRepository, ProvinceRepository provinceRepository, DistrictMapper districtMapper) {
        this.districtRepository = districtRepository;
        this.provinceRepository = provinceRepository;
        this.districtMapper = districtMapper;
    }

    @Override
    public DistrictDTO createDistrict(CreateDistrictRequestDTO createKotaRequest) {
        Province province = provinceRepository.findById(createKotaRequest.getProvinceId())
                .orElseThrow(() -> new ProvinceNotFoundException("id", createKotaRequest.getProvinceId()));
        District district = new District();
        district.setCode(createKotaRequest.getCode());
        district.setName(createKotaRequest.getName());
        district.setProvince(province);
        district.setCreatedAt(Instant.now());
        districtRepository.save(district);
        return districtMapper.fromDistrict(district);
    }

    @Override
    public DistrictDTO getDistrictById(String districtId){
        District district = districtRepository.findById(districtId).orElseThrow(() -> new DistrictNotFoundException("id", districtId));
        return districtMapper.fromDistrict(district);
    }

    @Override
    public DistrictDTO updateDistrict(String districtId, UpdateDistrictRequestDTO updateDistrictDTO) {
        Province province = provinceRepository.findById(updateDistrictDTO.getProvinceId()).orElseThrow(() -> new ProvinceNotFoundException("id", updateDistrictDTO.getProvinceId()));
        District district = districtRepository.findById(districtId).orElseThrow(() -> new DistrictNotFoundException("id", districtId));

        district.setCode(updateDistrictDTO.getCode());
        district.setName(updateDistrictDTO.getName());
        district.setProvince(province);
        district.setUpdatedAt(Instant.now());
        districtRepository.save(district);
        return districtMapper.fromDistrict(district);
    }

    @Override
    public void deleteDistrict(String districtId) {
        final District district = districtRepository.findById(districtId).orElseThrow(() -> new DistrictNotFoundException("id", districtId));
        districtRepository.delete(district);
    }

    @Override
    public ListDistrictResponseDTO getAllDistricts(ListDistrictRequestDTO listDistrictRequest) {
        Integer pageNo = listDistrictRequest.getPageNo();
        Integer pageSize = listDistrictRequest.getPageSize();
        String sortBy = listDistrictRequest.getSortBy();
        String sortDir = listDistrictRequest.getSortDir();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<District> districtPage = districtRepository.findAll(pageable);
        List<District> districtList = districtPage.getContent();

        List<DistrictDTO> districtDTOList = districtMapper.fromDistrictList(districtList);

        return new ListDistrictResponseDTO(
                districtDTOList, districtPage.getNumber(), districtPage.getSize(), districtPage.getTotalElements(),
                districtPage.getTotalPages(), districtPage.isLast()
        );
    }

    @Override
    public DistrictDTO getDistrictByName(String name) {
        District district = districtRepository.findAllByNameIgnoreCase(name).orElseThrow(() -> new DistrictNotFoundException("name", name));
        return districtMapper.fromDistrict(district);
    }

    @Override
    public DistrictDTO getDistrictByCode(String code) {
        District district = districtRepository.findAllByCode(code).orElseThrow(() -> new DistrictNotFoundException("code", code));
        return districtMapper.fromDistrict(district);
    }

    @Override
    public List<DistrictDTO> getDistrictByNameContains(String name) {
        List<District> districtList = districtRepository.findAllByNameContainingIgnoreCase(name);
        return districtMapper.fromDistrictList(districtList);
    }

    @Override
    public List<DistrictDTO> getAllDistrictsByProvinceId(String provinceId) {
        List<District> districtList = districtRepository.findAllByProvinceId(provinceId);
        return districtMapper.fromDistrictList(districtList);
    }

}
