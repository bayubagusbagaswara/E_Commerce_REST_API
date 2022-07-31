package com.restful.service.impl;

import com.restful.entity.region.District;
import com.restful.mapper.WilayahMapper;
import com.restful.dto.kecamatan.*;
import com.restful.dto.region.kecamatan.*;
import com.restful.entity.region.Kecamatan;
import com.restful.exception.KecamatanNotFoundException;
import com.restful.exception.KotaNotFoundException;
import com.restful.repository.KecamatanRepository;
import com.restful.repository.KotaRepository;
import com.restful.service.KecamatanService;
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
public class KecamatanServiceImpl implements KecamatanService {

    private final KecamatanRepository kecamatanRepository;
    private final KotaRepository kotaRepository;
    private final WilayahMapper wilayahMapper;

    public KecamatanServiceImpl(KecamatanRepository kecamatanRepository, KotaRepository kotaRepository, WilayahMapper wilayahMapper) {
        this.kecamatanRepository = kecamatanRepository;
        this.kotaRepository = kotaRepository;
        this.wilayahMapper = wilayahMapper;
    }

    @Override
    public KecamatanResponseDto createKecamatan(CreateKecamatanRequestDto createKecamatanRequest) throws KotaNotFoundException {
        District district = kotaRepository.findById(createKecamatanRequest.getKotaId())
                .orElseThrow(() -> new KotaNotFoundException("Kota ID [" + createKecamatanRequest.getKotaId() + "] not found"));
        Kecamatan kecamatan = new Kecamatan();
        kecamatan.setCode(createKecamatanRequest.getCode());
        kecamatan.setName(createKecamatanRequest.getName());
        kecamatan.setDistrict(district);
        kecamatan.setCreatedDate(LocalDateTime.now());
        kecamatanRepository.save(kecamatan);
        return wilayahMapper.mapToKecamatanResponse(kecamatan);
    }

    @Override
    public KecamatanResponseDto getKecamatanById(String id) throws KecamatanNotFoundException {
        Kecamatan kecamatan = kecamatanRepository.findById(id)
                .orElseThrow(() -> new KecamatanNotFoundException("Kecamatan ID [" + id + "] not found"));
        return wilayahMapper.mapToKecamatanResponse(kecamatan);
    }

    @Override
    public KecamatanResponseDto updateKecamatan(String id, UpdateKecamatanRequestDto updateKecamatanRequest) throws KotaNotFoundException, KecamatanNotFoundException {
        District district = kotaRepository.findById(updateKecamatanRequest.getKotaId())
                .orElseThrow(() -> new KotaNotFoundException("Kota ID [" + updateKecamatanRequest.getKotaId() + "] not found"));
        Kecamatan kecamatan = kecamatanRepository.findById(id)
                .orElseThrow(() -> new KecamatanNotFoundException("Kecamatan ID [" + id + "] not found"));
        kecamatan.setCode(updateKecamatanRequest.getCode());
        kecamatan.setName(updateKecamatanRequest.getName());
        kecamatan.setDistrict(district);
        kecamatan.setUpdatedDate(LocalDateTime.now());
        kecamatanRepository.save(kecamatan);
        return wilayahMapper.mapToKecamatanResponse(kecamatan);
    }

    @Override
    public void deleteKecamatan(String id) throws KecamatanNotFoundException {
        final Kecamatan kecamatan = kecamatanRepository.findById(id)
                .orElseThrow(() -> new KecamatanNotFoundException("Kecamatan ID [" + id + "] not found"));
        kecamatanRepository.delete(kecamatan);
    }

    @Override
    public ListKecamatanResponseDto getAllKecamatan(ListKecamatanRequestDto listKecamatanRequest) {
        Integer pageNo = listKecamatanRequest.getPageNo();
        Integer pageSize = listKecamatanRequest.getPageSize();
        String sortBy = listKecamatanRequest.getSortBy();
        String sortDir = listKecamatanRequest.getSortDir();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Kecamatan> kecamatanPage = kecamatanRepository.findAll(pageable);
        List<Kecamatan> kecamatanList = kecamatanPage.getContent();

        List<KecamatanResponseDto> kecamatanResponseList = wilayahMapper.mapToKecamatanResponseList(kecamatanList);

        ListKecamatanResponseDto listKecamatanResponse = new ListKecamatanResponseDto();
        listKecamatanResponse.setKecamatanList(kecamatanResponseList);
        listKecamatanResponse.setPageNo(kecamatanPage.getNumber());
        listKecamatanResponse.setPageSize(kecamatanPage.getSize());
        listKecamatanResponse.setTotalElements(kecamatanPage.getTotalElements());
        listKecamatanResponse.setTotalPages(kecamatanPage.getTotalPages());
        listKecamatanResponse.setLast(kecamatanPage.isLast());
        return listKecamatanResponse;
    }

    @Override
    public KecamatanResponseDto getKecamatanByName(String name) throws KecamatanNotFoundException {
        Kecamatan kecamatan = kecamatanRepository.findAllByNameIgnoreCase(name).orElseThrow(() -> new KecamatanNotFoundException("Kecamatan name [" + name + "] not found"));
        return wilayahMapper.mapToKecamatanResponse(kecamatan);
    }

    @Override
    public KecamatanResponseDto getKecamatanByCode(String code) throws KecamatanNotFoundException {
        Kecamatan kecamatan = kecamatanRepository.findAllByCode(code).orElseThrow(() -> new KecamatanNotFoundException("Kecamatan code [" + code + "] not found"));
        return wilayahMapper.mapToKecamatanResponse(kecamatan);
    }

    @Override
    public List<KecamatanResponseDto> getKecamatanByNameContains(String name) {
        List<Kecamatan> kecamatanList = kecamatanRepository.findAllByNameContainingIgnoreCase(name);
        return wilayahMapper.mapToKecamatanResponseList(kecamatanList);
    }

    @Override
    public List<KecamatanResponseDto> getKecamatanByKotaId(String kotaId) {
        List<Kecamatan> kecamatanList = kecamatanRepository.findAllByKotaId(kotaId);
        return wilayahMapper.mapToKecamatanResponseList(kecamatanList);
    }
}
