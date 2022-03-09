package com.restful.service.impl;

import com.restful.dto.kelurahan.*;
import com.restful.exception.KecamatanNotFoundException;
import com.restful.exception.KelurahanNotFoundException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class KelurahanServiceImplTest {

    private final static Logger log = LoggerFactory.getLogger(KelurahanServiceImplTest.class);

    @Autowired
    KelurahanServiceImpl kelurahanService;

    @Test
    void createKelurahan() throws KecamatanNotFoundException {
        CreateKelurahanRequestDto requestDto = new CreateKelurahanRequestDto();
        requestDto.setCode("");
        requestDto.setName("");
        requestDto.setKecamatanId("");

        final KelurahanResponseDto responseDto = kelurahanService.createKelurahan(requestDto);
        assertNotNull(responseDto.getId());
        assertNotNull(responseDto.getCreatedAt());
        assertEquals(requestDto.getName(), responseDto.getName());
    }

    @Test
    void getKelurahanById() throws KelurahanNotFoundException {
        String id = "";
        final KelurahanResponseDto responseDto = kelurahanService.getKelurahanById(id);
        assertEquals(id, responseDto.getId());
    }

    @Test
    void getAllKelurahan() {
        int totalSampleKelurahan = 0;
        int pageNo = 0;
        int pageSize = 5;
        String sortBy = "name";
        String sortDir = "asc";

        ListKelurahanRequestDto requestDto = new ListKelurahanRequestDto();
        requestDto.setPageNo(pageNo);
        requestDto.setPageSize(pageSize);
        requestDto.setSortBy(sortBy);
        requestDto.setSortDir(sortDir);

        final ListKelurahanResponseDto responseDto = kelurahanService.getAllKelurahan(requestDto);
        assertEquals(totalSampleKelurahan, responseDto.getTotalElements());
        assertEquals(pageSize, responseDto.getKelurahanResponses().size());
    }

    @Test
    void updateKelurahan() throws KelurahanNotFoundException, KecamatanNotFoundException {
        String id = "";
        UpdateKelurahanRequestDto requestDto = new UpdateKelurahanRequestDto();
        requestDto.setCode("");
        requestDto.setName("");
        requestDto.setKecamatanId("");

        final KelurahanResponseDto responseDto = kelurahanService.updateKelurahan(id, requestDto);

        assertEquals(id, responseDto.getId());
        assertNotNull(responseDto.getUpdatedAt());
        assertNotEquals(responseDto.getCreatedAt(), responseDto.getUpdatedAt());
        assertEquals(requestDto.getName(), responseDto.getName());
    }

    @Test
    void deleteKelurahan() {
        String id = "";
        kelurahanService.deleteKelurahan(id);
        assertThrows(KelurahanNotFoundException.class, () -> {
            final KelurahanResponseDto kelurahan = kelurahanService.getKelurahanById(id);
        });
    }

    @Test
    void getKelurahanByName() throws KelurahanNotFoundException {
        String name = "";
        final KelurahanResponseDto kelurahan = kelurahanService.getKelurahanByName(name);
        assertEquals(name, kelurahan.getName().toLowerCase());
        log.info("Name: {}", kelurahan.getName());
    }

    @Test
    void getKelurahanByCode() throws KelurahanNotFoundException {
        String code = "";
        final KelurahanResponseDto kelurahan = kelurahanService.getKelurahanByCode(code);
        assertEquals(code, kelurahan.getCode());
        log.info("Code: {}", kelurahan.getCode());
    }

    @Test
    void getKelurahanByNameContains() {
        String name = "";
        final List<KelurahanResponseDto> kelurahanList = kelurahanService.getKelurahanByNameContains(name);
        assertEquals(5, kelurahanList.size());
        for (KelurahanResponseDto kelurahan : kelurahanList) {
            log.info("Name: {}", kelurahan.getName());
            log.info("========");
        }
    }

    @Test
    void getKelurahanByKecamatanId() {
        String kecamatanId = "";
        final List<KelurahanResponseDto> kelurahanList = kelurahanService.getKelurahanByKecamatanId(kecamatanId);
        assertEquals(5, kelurahanList.size());
        for (KelurahanResponseDto kelurahan : kelurahanList) {
            log.info("Name: {}", kelurahan.getName());
            log.info("==========");
        }
    }
}