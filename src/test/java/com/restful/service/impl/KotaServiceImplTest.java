package com.restful.service.impl;

import com.restful.dto.kota.*;
import com.restful.exception.KotaNotFoundException;
import com.restful.exception.ProvinsiNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.function.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Sql(scripts = {
        "classpath:/sql/delete-data-kota.sql",
        "classpath:/sql/delete-data-provinsi.sql",
        "classpath:/sql/sample-data-provinsi.sql",
        "classpath:/sql/sample-data-kota.sql"
})
class KotaServiceImplTest {

    private final static Logger log = LoggerFactory.getLogger(KotaServiceImplTest.class);

    @Autowired
    KotaServiceImpl kotaService;

    @Test
    @Order(1)
    void createKota() throws ProvinsiNotFoundException {
        CreateKotaRequestDto requestDto = new CreateKotaRequestDto();
        requestDto.setCode("35123");
        requestDto.setName("Kota Test");
        // provinsi Jawa Timur
        requestDto.setProvinsiId("35");

        final KotaResponseDto kota = kotaService.createKota(requestDto);
        assertNotNull(kota.getId());
        assertNotNull(kota.getCreatedAt());
        assertEquals(requestDto.getName(), kota.getName());

        log.info("ID: {}", kota.getId());
        log.info("Name: {}", kota.getName());
    }

    @Test
    @Order(2)
    void getKotaById() throws KotaNotFoundException {
        // id kota Kediri, Jawa Timur
        String id = "3571";
        final KotaResponseDto kota = kotaService.getKotaById(id);
        assertEquals(id, kota.getId());
        log.info("ID: {}", kota.getId());
    }

    @Test
    @Order(3)
    void getAllKota() {
        // total sample kota 7
        int totalSampleKota = 7;
        int pageNo = 0;
        int pageSize = 3;
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
    @Order(4)
    void updateKota() throws ProvinsiNotFoundException, KotaNotFoundException {
        // update kota Denpasar pindah ke provinsi jawa timur
        String id = "5171";
        UpdateKotaRequestDto requestDto = new UpdateKotaRequestDto();
        requestDto.setCode("35123");
        requestDto.setName("KOTA DENPASAR Update");
        requestDto.setProvinsiId("35");

        final KotaResponseDto responseDto = kotaService.updateKota(id, requestDto);
        assertEquals(id, responseDto.getId());
        assertNotNull(responseDto.getUpdatedAt());
        assertNotEquals(responseDto.getCreatedAt(), responseDto.getUpdatedAt());
        assertEquals(requestDto.getName(), responseDto.getName());
    }

    @Test
    @Order(5)
    void deleteKota() {
        // delete kota Denpasar
        String id = "5171";
        kotaService.deleteKota(id);
        assertThrows(KotaNotFoundException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                final KotaResponseDto kota = kotaService.getKotaById(id);
            }
        });
    }

    @Test
    @Order(6)
    void getKotaByName() throws KotaNotFoundException {
        String name = "kota kediri";
        final KotaResponseDto kota = kotaService.getKotaByName(name);
        assertEquals(name, kota.getName().toLowerCase());
        log.info("Name: {}", kota.getName());
    }

    @Test
    @Order(7)
    void getKotaByCode() throws KotaNotFoundException {
        // code kota kediri
        String code = "3571";
        final KotaResponseDto kota = kotaService.getKotaByCode(code);
        assertEquals(code, kota.getCode());
        log.info("Code: {}", kota.getCode());
    }

    @Test
    @Order(8)
    void getKotaByNameContains() {
        // contains name kediri [KAB KEDIRI, KOTA KEDIRI]
        String name = "kediri";
        final List<KotaResponseDto> kotaList = kotaService.getKotaByNameContains(name);
        assertEquals(2, kotaList.size());
        for (KotaResponseDto kota : kotaList) {
            log.info("Name: {}", kota.getName());
            log.info("=======");
        }
    }

    @Test
    @Order(9)
    void getKotaByProvinsiId() {
        // provinsi jawa timur [Kota Surabaya, Kota Kediri, Kab Kediri]
        String provinsiId = "35";
        final List<KotaResponseDto> kotaList = kotaService.getKotaByProvinsiId(provinsiId);
        assertEquals(3, kotaList.size());
        for (KotaResponseDto kota : kotaList) {
            log.info("Name: {}", kota.getName());
            log.info("=======");
        }
    }
}