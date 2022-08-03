package com.ecommerce.service.impl;

import com.ecommerce.dto.kecamatan.*;
import com.ecommerce.dto.region.subDistrict.*;
import com.ecommerce.exception.KecamatanNotFoundException;
import com.ecommerce.exception.KotaNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
        "classpath:/sql/delete-data-kecamatan.sql",
        "classpath:/sql/delete-data-kota.sql",
        "classpath:/sql/delete-data-provinsi.sql",
        "classpath:/sql/sample-data-provinsi.sql",
        "classpath:/sql/sample-data-kota.sql",
        "classpath:/sql/sample-data-kecamatan.sql"
})
class SubDistrictServiceImplTest {

    private final static Logger log = LoggerFactory.getLogger(SubDistrictServiceImplTest.class);

    @Autowired
    SubDistrictServiceImpl kecamatanService;

    @Test
    @Order(1)
    void createKecamatan() throws KotaNotFoundException {
        CreateSubDistrictRequestDTO requestDto = new CreateSubDistrictRequestDTO();
        requestDto.setCode("3571890");
        requestDto.setName("Kecamatan Kediri");
        // kota Kediri id 3571
        requestDto.setKotaId("3571");

        final SubDistrictDTO responseDto = kecamatanService.createKecamatan(requestDto);
        assertNotNull(responseDto.getId());
        assertNotNull(responseDto.getCreatedDate());
        assertEquals(requestDto.getName(), responseDto.getName());
    }

    @Test
    @Order(2)
    void getKecamatanById() throws KecamatanNotFoundException {
        // id kecamatan Pesantren, Kota Kediri
        String id = "357103";
        final SubDistrictDTO responseDto = kecamatanService.getKecamatanById(id);
        assertEquals(id, responseDto.getId());
    }

    @Test
    @Order(3)
    void getAllKecamatan() {
        int totalSampleKecamatan = 18;
        int pageNo = 0;
        int pageSize = 5;
        String sortBy = "name";
        String sortDir = "asc";

        ListSubDistrictRequestDTO requestDto = new ListSubDistrictRequestDTO();
        requestDto.setPageNo(pageNo);
        requestDto.setPageSize(pageSize);
        requestDto.setSortBy(sortBy);
        requestDto.setSortDir(sortDir);

        final ListKecamatanResponseDto responseDto = kecamatanService.getAllKecamatan(requestDto);
        assertEquals(totalSampleKecamatan, responseDto.getTotalElements());
        assertEquals(pageSize, responseDto.getKecamatanList().size());
    }

    @Test
    @Order(4)
    void updateKecamatan() throws KecamatanNotFoundException, KotaNotFoundException {
        // update kecamatan Mojoroto id 357101
        String id = "357101";
        UpdateKecamatanRequestDto requestDto = new UpdateKecamatanRequestDto();
        requestDto.setCode("357101");
        requestDto.setName("Mojoroto Update");
        // pindah ke kota Surabaya id 3578
        requestDto.setKotaId("3578");

        final SubDistrictDTO responseDto = kecamatanService.updateKecamatan(id, requestDto);
        assertEquals(id, responseDto.getId());
        assertNotNull(responseDto.getUpdatedDate());
        assertNotEquals(responseDto.getCreatedDate(), responseDto.getUpdatedDate());
        assertEquals(requestDto.getName(), responseDto.getName());
    }

    @Test
    @Order(5)
    void deleteKecamatan() throws KecamatanNotFoundException {
        // delete kecamatan Mojoroto
        String id = "357101";
        kecamatanService.deleteKecamatan(id);
        assertThrows(KecamatanNotFoundException.class, () -> {
            final SubDistrictDTO kecamatan = kecamatanService.getKecamatanById(id);
        });
    }

    @Test
    @Order(6)
    void getKecamatanByName() throws KecamatanNotFoundException {
        String name = "pesantren";
        final SubDistrictDTO kecamatan = kecamatanService.getKecamatanByName(name);
        assertEquals(name, kecamatan.getName().toLowerCase());
        log.info("Name: {}", kecamatan.getName());
    }

    @Test
    @Order(7)
    void getKecamatanByCode() throws KecamatanNotFoundException {
        // code kecamatan Pesantren 357103
        String code = "357103";
        final SubDistrictDTO kecamatan = kecamatanService.getKecamatanByCode(code);
        assertEquals(code, kecamatan.getCode());
        log.info("Code: {}", kecamatan.getCode());
    }

    @Test
    @Order(8)
    void getKecamatanByNameContains() {
        // name 'ke' [Kemayoran, Kebayoran Lama, Kebayoran Baru, Simokerto, Kenjeran, Sambikerep]
        String name = "ke";
        final List<SubDistrictDTO> kecamatanList = kecamatanService.getKecamatanByNameContains(name);
        assertEquals(6, kecamatanList.size());
        for (SubDistrictDTO kecamatan : kecamatanList) {
            log.info("Name: {}", kecamatan.getName());
            log.info("==========");
        }
    }

    @Test
    @Order(9)
    void getKecamatanByKotaId() {
        // id kota Kediri [Kota, Pesantren, Mojoroto]
        String kotaId = "3571";
        final List<SubDistrictDTO> kecamatanList = kecamatanService.getKecamatanByKotaId(kotaId);
        assertEquals(3, kecamatanList.size());
        for (SubDistrictDTO kecamatan : kecamatanList) {
            log.info("Name: {}", kecamatan.getName());
            log.info("==========");
        }
    }
}