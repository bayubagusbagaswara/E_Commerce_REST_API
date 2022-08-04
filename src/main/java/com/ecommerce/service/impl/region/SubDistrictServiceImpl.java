package com.ecommerce.service.impl.region;

import com.ecommerce.entity.region.District;
import com.ecommerce.entity.region.SubDistrict;
import com.ecommerce.dto.region.subDistrict.*;
import com.ecommerce.exception.SubDistrictNotFoundException;
import com.ecommerce.exception.DistrictNotFoundException;
import com.ecommerce.mapper.region.SubDistrictMapper;
import com.ecommerce.repository.region.SubDistrictRepository;
import com.ecommerce.repository.region.DistrictRepository;
import com.ecommerce.service.region.SubDistrictService;
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
public class SubDistrictServiceImpl implements SubDistrictService {

    private final SubDistrictRepository subDistrictRepository;
    private final DistrictRepository districtRepository;
    private final SubDistrictMapper subDistrictMapper;

    @Autowired
    public SubDistrictServiceImpl(SubDistrictRepository subDistrictRepository, DistrictRepository districtRepository, SubDistrictMapper subDistrictMapper) {
        this.subDistrictRepository = subDistrictRepository;
        this.districtRepository = districtRepository;
        this.subDistrictMapper = subDistrictMapper;
    }

    @Override
    public SubDistrictDTO createSubDistrict(CreateSubDistrictRequestDTO createSubDistrictRequestDTO) {
        District district = districtRepository.findById(createSubDistrictRequestDTO.getDistrictId())
                .orElseThrow(() -> new DistrictNotFoundException("id", createSubDistrictRequestDTO.getDistrictId()));
        SubDistrict subDistrict = new SubDistrict();
        subDistrict.setCode(createSubDistrictRequestDTO.getCode());
        subDistrict.setName(createSubDistrictRequestDTO.getName());
        subDistrict.setDistrict(district);
        subDistrict.setCreatedAt(Instant.now());
        subDistrictRepository.save(subDistrict);
        return subDistrictMapper.fromSubDistrict(subDistrict);
    }

    @Override
    public SubDistrictDTO getSubDistrictById(String subDistrictId) {
        SubDistrict subDistrict = subDistrictRepository.findById(subDistrictId)
                .orElseThrow(() -> new SubDistrictNotFoundException("id", subDistrictId));
        return subDistrictMapper.fromSubDistrict(subDistrict);
    }

    @Override
    public ListSubDistrictResponseDTO getAllSubDistricts(ListSubDistrictRequestDTO listSubDistrictRequestDTO) {
        Integer pageNo = listSubDistrictRequestDTO.getPageNo();
        Integer pageSize = listSubDistrictRequestDTO.getPageSize();
        String sortBy = listSubDistrictRequestDTO.getSortBy();
        String sortDir = listSubDistrictRequestDTO.getSortDir();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<SubDistrict> subDistrictPage = subDistrictRepository.findAll(pageable);
        List<SubDistrict> subDistrictList = subDistrictPage.getContent();

        List<SubDistrictDTO> subDistrictDTOList = subDistrictMapper.fromSubDistrictList(subDistrictList);

        return new ListSubDistrictResponseDTO(
                subDistrictDTOList, subDistrictPage.getNumber(), subDistrictPage.getSize(),
                subDistrictPage.getTotalElements(), subDistrictPage.getTotalPages(), subDistrictPage.isLast()
        );
    }

    @Override
    public SubDistrictDTO updateSubDistrict(String subDistrictId, UpdateSubDistrictRequestDTO updateSubDistrictRequestDTO) {
        District district = districtRepository.findById(updateSubDistrictRequestDTO.getDistrictId()).orElseThrow(() -> new DistrictNotFoundException("id", updateSubDistrictRequestDTO.getDistrictId()));
        SubDistrict subDistrict = subDistrictRepository.findById(subDistrictId).orElseThrow(() -> new SubDistrictNotFoundException("id", subDistrictId));
        subDistrict.setCode(updateSubDistrictRequestDTO.getCode());
        subDistrict.setName(updateSubDistrictRequestDTO.getName());
        subDistrict.setDistrict(district);
        subDistrict.setUpdatedAt(Instant.now());
        subDistrictRepository.save(subDistrict);
        return subDistrictMapper.fromSubDistrict(subDistrict);
    }

    @Override
    public void deleteSubDistrict(String subDistrictId) {
        final SubDistrict subDistrict = subDistrictRepository.findById(subDistrictId).orElseThrow(() -> new SubDistrictNotFoundException("id", subDistrictId));
        subDistrictRepository.delete(subDistrict);
    }

    @Override
    public SubDistrictDTO getSubDistrictByName(String name) {
        SubDistrict subDistrict = subDistrictRepository.findAllByNameIgnoreCase(name).orElseThrow(() -> new SubDistrictNotFoundException("name", name));
        return subDistrictMapper.fromSubDistrict(subDistrict);
    }

    @Override
    public List<SubDistrictDTO> getSubDistrictByNameContains(String name) {
        List<SubDistrict> subDistrictList = subDistrictRepository.findAllByNameContainingIgnoreCase(name);
        return subDistrictMapper.fromSubDistrictList(subDistrictList);
    }

    @Override
    public SubDistrictDTO getSubDistrictByCode(String code) {
        SubDistrict subDistrict = subDistrictRepository.findAllByCode(code).orElseThrow(() -> new SubDistrictNotFoundException("code", code));
        return subDistrictMapper.fromSubDistrict(subDistrict);
    }

    @Override
    public List<SubDistrictDTO> getAllSubDistrictsByDistrictId(String districtId) {
        List<SubDistrict> subDistrictList = subDistrictRepository.findAllByDistrictId(districtId);
        return subDistrictMapper.fromSubDistrictList(subDistrictList);
    }
}
