package com.restful.service.impl;

import com.restful.dto.kota.*;
import com.restful.exception.KotaNotFoundException;
import com.restful.exception.ProvinsiNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class KotaServiceImplTest {

    private final static Logger log = LoggerFactory.getLogger(KotaServiceImplTest.class);

    @Autowired
    KotaServiceImpl kotaService;

    @Test
    void createKota() throws ProvinsiNotFoundException {
        CreateKotaRequestDto requestDto = new CreateKotaRequestDto();
        requestDto.setCode("");
        requestDto.setName("");
        requestDto.setProvinsiId("");

        final KotaResponseDto kota = kotaService.createKota(requestDto);
        assertNotNull(kota.getId());
        assertNotNull(kota.getCreatedAt());
        assertEquals(requestDto.getName(), kota.getName());

        log.info("ID: {}", kota.getId());
        log.info("Name: {}", kota.getName());
    }

    @Test
    void getKotaById() throws KotaNotFoundException {
        String id = "";
        final KotaResponseDto kota = kotaService.getKotaById(id);
        assertEquals(id, kota.getId());
        log.info("ID: {}", kota.getId());
    }

    @Test
    void getAllKota() {
        int totalSampleKota = 0;
        int pageNo = 0;
        int pageSize = 5;
        String sortBy = "name";
        String sortDir = "asc";

        ListKotaRequestDto requestDto = new ListKotaRequestDto();
        requestDto.setPageNo(pageNo);
        requestDto.setPageSize(pageSize);
        requestDto.setSortBy(sortBy);
        requestDto.setSortDir(sortDir);

        final ListKotaResponseDto responseDto = kotaService.getAllKota(requestDto);
        assertEquals(totalSampleKota, responseDto.getTotalElements());
        assertEquals(pageSize, responseDto.getKotaResponses().size());
    }

    @Test
    void updateKota() throws ProvinsiNotFoundException, KotaNotFoundException {
        String id = "";
        UpdateKotaRequestDto requestDto = new UpdateKotaRequestDto();
        requestDto.setCode("");
        requestDto.setName("");
        requestDto.setProvinsiId("");

        final KotaResponseDto responseDto = kotaService.updateKota(id, requestDto);
        assertEquals(id, responseDto.getId());
        assertNotNull(responseDto.getUpdatedAt());
        assertNotEquals(responseDto.getCreatedAt(), responseDto.getUpdatedAt());
        assertEquals(requestDto.getName(), responseDto.getName());
    }

    @Test
    void deleteKota() {
        String id = "";
        kotaService.deleteKota(id);
        assertThrows(KotaNotFoundException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                final KotaResponseDto kota = kotaService.getKotaById(id);
            }
        });
    }

    @Test
    void getKotaByName() throws KotaNotFoundException {
        String name = "";
        final KotaResponseDto kota = kotaService.getKotaByName(name);
        assertEquals(name, kota.getName().toLowerCase());
        log.info("Name: {}", kota.getName());
    }

    @Test
    void getKotaByCode() throws KotaNotFoundException {
        String code = "";
        final KotaResponseDto kota = kotaService.getKotaByCode(code);
        assertEquals(code, kota.getCode());
        log.info("Code: {}", kota.getCode());
    }

    @Test
    void getKotaByNameContains() {
        String name = "";
        final List<KotaResponseDto> kotaList = kotaService.getKotaByNameContains(name);
        assertEquals(3, kotaList.size());
        for (KotaResponseDto kota : kotaList) {
            log.info("Name: {}", kota.getName());
            log.info("=======");
        }
    }

    @Test
    void getKotaByProvinsiId() {
        String provinsiId = "";
        final List<KotaResponseDto> kotaList = kotaService.getKotaByProvinsiId(provinsiId);
        assertEquals(5, kotaList.size());
        for (KotaResponseDto kota : kotaList) {
            log.info("Name: {}", kota.getName());
            log.info("=======");
        }
    }
}