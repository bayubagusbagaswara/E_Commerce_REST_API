package com.ecommerce.service.impl.region;

import com.ecommerce.entity.region.SubDistrict;
import com.ecommerce.entity.region.UrbanVillage;
import com.ecommerce.dto.region.urbanVillage.*;
import com.ecommerce.exception.SubDistrictNotFoundException;
import com.ecommerce.exception.UrbanVillageNotFoundException;
import com.ecommerce.mapper.region.UrbanVillageMapper;
import com.ecommerce.repository.region.SubDistrictRepository;
import com.ecommerce.repository.region.UrbanVillageRepository;
import com.ecommerce.service.region.UrbanVillageService;
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
public class UrbanVillageServiceImpl implements UrbanVillageService {

    private final UrbanVillageRepository urbanVillageRepository;
    private final SubDistrictRepository subDistrictRepository;
    private final UrbanVillageMapper urbanVillageMapper;

    @Autowired
    public UrbanVillageServiceImpl(UrbanVillageRepository urbanVillageRepository, SubDistrictRepository subDistrictRepository, UrbanVillageMapper urbanVillageMapper) {
        this.urbanVillageRepository = urbanVillageRepository;
        this.subDistrictRepository = subDistrictRepository;
        this.urbanVillageMapper = urbanVillageMapper;
    }


    @Override
    public UrbanVillageDTO createUrbanVillage(CreateUrbanVillageRequestDTO createUrbanVillageRequestDTO) {
        SubDistrict subDistrict = subDistrictRepository.findById(createUrbanVillageRequestDTO.getSubDistrictId())
                .orElseThrow(() -> new SubDistrictNotFoundException("id", createUrbanVillageRequestDTO.getSubDistrictId()));
        UrbanVillage urbanVillage = new UrbanVillage();
        urbanVillage.setCode(createUrbanVillageRequestDTO.getCode());
        urbanVillage.setName(createUrbanVillageRequestDTO.getName());
        urbanVillage.setCreatedAt(Instant.now());
        urbanVillage.setSubDistrict(subDistrict);
        urbanVillageRepository.save(urbanVillage);
        return urbanVillageMapper.fromUrbanVillage(urbanVillage);
    }

    @Override
    public UrbanVillageDTO getUrbanVillageById(String urbanVillageId) {
        UrbanVillage urbanVillage = urbanVillageRepository.findById(urbanVillageId).orElseThrow(() -> new UrbanVillageNotFoundException("id", urbanVillageId));
        return urbanVillageMapper.fromUrbanVillage(urbanVillage);
    }

    @Override
    public ListUrbanVillageResponseDTO getAllUrbanVillages(ListUrbanVillageRequestDTO listUrbanVillageRequestDTO) {
        Integer pageNo = listUrbanVillageRequestDTO.getPageNo();
        Integer pageSize = listUrbanVillageRequestDTO.getPageSize();
        String sortBy = listUrbanVillageRequestDTO.getSortBy();
        String sortDir = listUrbanVillageRequestDTO.getSortDir();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<UrbanVillage> urbanVillagePage = urbanVillageRepository.findAll(pageable);
        List<UrbanVillage> urbanVillageList = urbanVillagePage.getContent();

        List<UrbanVillageDTO> urbanVillageDTOS = urbanVillageMapper.fromUrbanVillageList(urbanVillageList);

        return new ListUrbanVillageResponseDTO(
                urbanVillageDTOS, urbanVillagePage.getNumber(), urbanVillagePage.getSize(),
                urbanVillagePage.getTotalElements(), urbanVillagePage.getTotalPages(), urbanVillagePage.isLast()
        );
    }

    @Override
    public UrbanVillageDTO updateUrbanVillage(String urbanVillageId, UpdateUrbanVillageRequestDTO updateUrbanVillageRequestDTO) {
        SubDistrict subDistrict = subDistrictRepository.findById(updateUrbanVillageRequestDTO.getSubDistrictId()).orElseThrow(() -> new SubDistrictNotFoundException("id", updateUrbanVillageRequestDTO.getSubDistrictId()));
        UrbanVillage urbanVillage = urbanVillageRepository.findById(urbanVillageId).orElseThrow(() -> new UrbanVillageNotFoundException("id", urbanVillageId));
        urbanVillage.setCode(updateUrbanVillageRequestDTO.getCode());
        urbanVillage.setName(updateUrbanVillageRequestDTO.getName());
        urbanVillage.setSubDistrict(subDistrict);
        urbanVillage.setUpdatedAt(Instant.now());
        urbanVillageRepository.save(urbanVillage);
        return urbanVillageMapper.fromUrbanVillage(urbanVillage);
    }

    @Override
    public void deleteUrbanVillage(String urbanVillageId) {
        final UrbanVillage urbanVillage = urbanVillageRepository.findById(urbanVillageId).orElseThrow(() -> new UrbanVillageNotFoundException("id", urbanVillageId));
        urbanVillageRepository.delete(urbanVillage);
    }

    @Override
    public UrbanVillageDTO getUrbanVillageByName(String name) {
        UrbanVillage urbanVillage = urbanVillageRepository.findAllByNameIgnoreCase(name).orElseThrow(() -> new UrbanVillageNotFoundException("name", name));
        return urbanVillageMapper.fromUrbanVillage(urbanVillage);
    }

    @Override
    public List<UrbanVillageDTO> getUrbanVillageByNameContains(String name) {
        List<UrbanVillage> urbanVillageList = urbanVillageRepository.findAllByNameContainingIgnoreCase(name);
        return urbanVillageMapper.fromUrbanVillageList(urbanVillageList);
    }

    @Override
    public UrbanVillageDTO getUrbanVillageByCode(String code) {
        UrbanVillage urbanVillage = urbanVillageRepository.findAllByCode(code).orElseThrow(() -> new UrbanVillageNotFoundException("code", code));
        return urbanVillageMapper.fromUrbanVillage(urbanVillage);
    }

    @Override
    public List<UrbanVillageDTO> getAllUrbanVillagesBySubDistrictId(String subDistrictId) {
        List<UrbanVillage> urbanVillageList = urbanVillageRepository.findAllBySubDistrictId(subDistrictId);
        return urbanVillageMapper.fromUrbanVillageList(urbanVillageList);
    }
}

