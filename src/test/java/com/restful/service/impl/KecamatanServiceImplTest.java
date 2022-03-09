package com.restful.service.impl;

import com.restful.dto.kecamatan.*;
import com.restful.exception.KecamatanNotFoundException;
import com.restful.exception.KotaNotFoundException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class KecamatanServiceImplTest {

    private final static Logger log = LoggerFactory.getLogger(KecamatanServiceImplTest.class);

    @Autowired
    KecamatanServiceImpl kecamatanService;

    @Test
    void createKecamatan() throws KotaNotFoundException {
        CreateKecamatanRequestDto requestDto = new CreateKecamatanRequestDto();
        requestDto.setCode("");
        requestDto.setName("");
        requestDto.setKotaId("");

        final KecamatanResponseDto responseDto = kecamatanService.createKecamatan(requestDto);
        assertNotNull(responseDto.getId());
        assertNotNull(responseDto.getCreatedAt());
        assertEquals(requestDto.getName(), responseDto.getName());
    }

    @Test
    void getKecamatanById() throws KecamatanNotFoundException {
        String id = "";
        final KecamatanResponseDto responseDto = kecamatanService.getKecamatanById(id);
        assertEquals(id, responseDto.getId());
    }

    @Test
    void getAllKecamatan() {
        int totalSampleKecamatan = 0;
        int pageNo = 0;
        int pageSize = 5;
        String sortBy = "name";
        String sortDir = "asc";

        ListKecamatanRequestDto requestDto = new ListKecamatanRequestDto();
        requestDto.setPageNo(pageNo);
        requestDto.setPageSize(pageSize);
        requestDto.setSortBy(sortBy);
        requestDto.setSortDir(sortDir);

        final ListKecamatanResponseDto responseDto = kecamatanService.getAllKecamatan(requestDto);
        assertEquals(totalSampleKecamatan, responseDto.getTotalElements());
        assertEquals(pageSize, responseDto.getKecamatanResponses().size());
    }

    @Test
    void updateKecamatan() throws KecamatanNotFoundException, KotaNotFoundException {
        String id = "";
        UpdateKecamatanRequestDto requestDto = new UpdateKecamatanRequestDto();
        requestDto.setCode("");
        requestDto.setName("");
        requestDto.setKotaId("");

        final KecamatanResponseDto responseDto = kecamatanService.updateKecamatan(id, requestDto);
        assertEquals(id, responseDto.getId());
        assertNotNull(responseDto.getUpdatedAt());
        assertNotEquals(responseDto.getCreatedAt(), responseDto.getUpdatedAt());
        assertEquals(requestDto.getName(), responseDto.getName());
    }

    @Test
    void deleteKecamatan() {
        String id = "";
        kecamatanService.deleteKecamatan(id);
        assertThrows(KecamatanNotFoundException.class, () -> {
            final KecamatanResponseDto kecamatan = kecamatanService.getKecamatanById(id);
        });
    }

    @Test
    void getKecamatanByName() throws KecamatanNotFoundException {
        String name = "";
        final KecamatanResponseDto kecamatan = kecamatanService.getKecamatanByName(name);
        assertEquals(name, kecamatan.getName().toLowerCase());
        log.info("Name: {}", kecamatan.getName());
    }

    @Test
    void getKecamatanByCode() throws KecamatanNotFoundException {
        String code = "";
        final KecamatanResponseDto kecamatan = kecamatanService.getKecamatanByCode(code);
        assertEquals(code, kecamatan.getCode());
        log.info("Code: {}", kecamatan.getCode());
    }

    @Test
    void getKecamatanByNameContains() {
        String name = "";
        final List<KecamatanResponseDto> kecamatanList = kecamatanService.getKecamatanByNameContains(name);
        assertEquals(3, kecamatanList.size());
        for (KecamatanResponseDto kecamatan : kecamatanList) {
            log.info("Name: {}", kecamatan.getName());
            log.info("==========");
        }
    }

    @Test
    void getKecamatanByKotaId() {
        String kotaId = "";
        final List<KecamatanResponseDto> kecamatanList = kecamatanService.getKecamatanByKotaId(kotaId);
        assertEquals(5, kecamatanList.size());
        for (KecamatanResponseDto kecamatan : kecamatanList) {
            log.info("Name: {}", kecamatan.getName());
            log.info("==========");
        }
    }
}