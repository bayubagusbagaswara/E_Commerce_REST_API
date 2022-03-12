package com.restful.service.impl;

import com.restful.dto.WilayahMapper;
import com.restful.dto.kelurahan.*;
import com.restful.entity.wilayah.Kecamatan;
import com.restful.entity.wilayah.Kelurahan;
import com.restful.exception.KecamatanNotFoundException;
import com.restful.exception.KelurahanNotFoundException;
import com.restful.repository.KecamatanRepository;
import com.restful.repository.KelurahanRepository;
import com.restful.service.KelurahanService;
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
public class KelurahanServiceImpl implements KelurahanService {

    private final KelurahanRepository kelurahanRepository;
    private final KecamatanRepository kecamatanRepository;
    private final WilayahMapper wilayahMapper;

    public KelurahanServiceImpl(KelurahanRepository kelurahanRepository, KecamatanRepository kecamatanRepository, WilayahMapper wilayahMapper) {
        this.kelurahanRepository = kelurahanRepository;
        this.kecamatanRepository = kecamatanRepository;
        this.wilayahMapper = wilayahMapper;
    }

    @Override
    public KelurahanResponseDto createKelurahan(CreateKelurahanRequestDto createKelurahanRequest) throws KecamatanNotFoundException {
        Kecamatan kecamatan = kecamatanRepository.findById(createKelurahanRequest.getKecamatanId())
                .orElseThrow(() -> new KecamatanNotFoundException("Kecamatan ID [" + createKelurahanRequest.getKecamatanId() + "] not found"));
        Kelurahan kelurahan = new Kelurahan();
        kelurahan.setCode(createKelurahanRequest.getCode());
        kelurahan.setName(createKelurahanRequest.getName());
        kelurahan.setCreatedDate(LocalDateTime.now());
        kelurahan.setKecamatan(kecamatan);
        kelurahanRepository.save(kelurahan);
        return wilayahMapper.mapToKelurahanResponse(kelurahan);
    }

    @Override
    public KelurahanResponseDto getKelurahanById(String id) throws KelurahanNotFoundException {
        Kelurahan kelurahan = getKelurahan(id);
        return wilayahMapper.mapToKelurahanResponse(kelurahan);
    }

    @Override
    public KelurahanResponseDto updateKelurahan(String id, UpdateKelurahanRequestDto updateKelurahanRequest) throws KecamatanNotFoundException, KelurahanNotFoundException {
        Kecamatan kecamatan = kecamatanRepository.findById(updateKelurahanRequest.getKecamatanId())
                .orElseThrow(() -> new KecamatanNotFoundException("Kecamatan ID [" + updateKelurahanRequest.getKecamatanId() + "] not found"));
        Kelurahan kelurahan = getKelurahan(id);
        kelurahan.setCode(updateKelurahanRequest.getCode());
        kelurahan.setName(updateKelurahanRequest.getName());
        kelurahan.setKecamatan(kecamatan);
        kelurahan.setUpdatedDate(LocalDateTime.now());
        kelurahanRepository.save(kelurahan);
        return wilayahMapper.mapToKelurahanResponse(kelurahan);
    }

    @Override
    public ListKelurahanResponseDto getAllKelurahan(ListKelurahanRequestDto listKelurahanRequest) {
        Integer pageNo = listKelurahanRequest.getPageNo();
        Integer pageSize = listKelurahanRequest.getPageSize();
        String sortBy = listKelurahanRequest.getSortBy();
        String sortDir = listKelurahanRequest.getSortDir();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Kelurahan> kelurahanPage = kelurahanRepository.findAll(pageable);
        List<Kelurahan> kelurahanList = kelurahanPage.getContent();
        List<KelurahanResponseDto> kelurahanResponseList = wilayahMapper.mapToKelurahanResponseList(kelurahanList);

        ListKelurahanResponseDto listKelurahanResponse = new ListKelurahanResponseDto();
        listKelurahanResponse.setKelurahanList(kelurahanResponseList);
        listKelurahanResponse.setPageNo(kelurahanPage.getNumber());
        listKelurahanResponse.setPageSize(kelurahanPage.getSize());
        listKelurahanResponse.setTotalElements(kelurahanPage.getTotalElements());
        listKelurahanResponse.setTotalPages(kelurahanPage.getTotalPages());
        listKelurahanResponse.setLast(kelurahanPage.isLast());
        return listKelurahanResponse;
    }

    @Override
    public void deleteKelurahan(String id) {
        kelurahanRepository.deleteById(id);
    }

    @Override
    public KelurahanResponseDto getKelurahanByName(String name) throws KelurahanNotFoundException {
        Kelurahan kelurahan = kelurahanRepository.findAllByNameIgnoreCase(name).orElseThrow(() -> new KelurahanNotFoundException("Kelurahan name [" + name + "] not found"));
        return wilayahMapper.mapToKelurahanResponse(kelurahan);
    }

    @Override
    public KelurahanResponseDto getKelurahanByCode(String code) throws KelurahanNotFoundException {
        Kelurahan kelurahan = kelurahanRepository.findAllByCode(code).orElseThrow(() -> new KelurahanNotFoundException("Kelurahan code [" + code + "] not found"));
        return wilayahMapper.mapToKelurahanResponse(kelurahan);
    }

    @Override
    public List<KelurahanResponseDto> getKelurahanByNameContains(String name) {
        List<Kelurahan> kelurahanList = kelurahanRepository.findAllByNameContainingIgnoreCase(name);
        return wilayahMapper.mapToKelurahanResponseList(kelurahanList);
    }

    @Override
    public List<KelurahanResponseDto> getKelurahanByKecamatanId(String kecamatanId) {
        List<Kelurahan> kelurahanList = kelurahanRepository.findAllByKecamatanId(kecamatanId);
        return wilayahMapper.mapToKelurahanResponseList(kelurahanList);
    }

    private Kelurahan getKelurahan(String id) throws KelurahanNotFoundException {
        return kelurahanRepository.findById(id).orElseThrow(() -> new KelurahanNotFoundException("Kelurahan ID [" + id + "] not found"));
    }
}

