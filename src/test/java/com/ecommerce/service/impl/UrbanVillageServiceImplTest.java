package com.ecommerce.service.impl;

import com.ecommerce.dto.kelurahan.*;
import com.ecommerce.dto.region.urbanVillage.*;
import com.ecommerce.exception.KecamatanNotFoundException;
import com.ecommerce.exception.KelurahanNotFoundException;
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
        "classpath:/sql/delete-data-kelurahan.sql",
        "classpath:/sql/delete-data-kecamatan.sql",
        "classpath:/sql/delete-data-kota.sql",
        "classpath:/sql/delete-data-provinsi.sql",
        "classpath:/sql/sample-data-provinsi.sql",
        "classpath:/sql/sample-data-kota.sql",
        "classpath:/sql/sample-data-kecamatan.sql",
        "classpath:/sql/sample-data-kelurahan.sql"
})
class UrbanVillageServiceImplTest {

    private final static Logger log = LoggerFactory.getLogger(UrbanVillageServiceImplTest.class);

    @Autowired
    UrbanVillageServiceImpl kelurahanService;

    @Test
    @Order(1)
    void createKelurahan() throws KecamatanNotFoundException {
        CreateUrbanVillageRequestDTO requestDto = new CreateUrbanVillageRequestDTO();
        requestDto.setCode("3571038899");
        requestDto.setName("Kelurahan di Pesantren");
        // id kecamatan Pesantren
        requestDto.setKecamatanId("357103");

        final UrbanVillageDTO responseDto = kelurahanService.createKelurahan(requestDto);
        assertNotNull(responseDto.getId());
        assertNotNull(responseDto.getCreatedDate());
        assertEquals(requestDto.getName(), responseDto.getName());
    }

    @Test
    @Order(2)
    void getKelurahanById() throws KelurahanNotFoundException {
        // id kelurahan Singonegaran, Kecamatan Pesantren
        String id = "3571031005";
        final UrbanVillageDTO responseDto = kelurahanService.getKelurahanById(id);
        assertEquals(id, responseDto.getId());
    }

    @Test
    @Order(3)
    void getAllKelurahan() {
        int totalSampleKelurahan = 18;
        int pageNo = 0;
        int pageSize = 5;
        String sortBy = "name";
        String sortDir = "asc";

        ListUrbanVillageRequestDTO requestDto = new ListUrbanVillageRequestDTO();
        requestDto.setPageNo(pageNo);
        requestDto.setPageSize(pageSize);
        requestDto.setSortBy(sortBy);
        requestDto.setSortDir(sortDir);

        final ListKelurahanResponseDto responseDto = kelurahanService.getAllKelurahan(requestDto);
        assertEquals(totalSampleKelurahan, responseDto.getTotalElements());
        assertEquals(pageSize, responseDto.getKelurahanList().size());
    }

    @Test
    @Order(4)
    void updateKelurahan() throws KelurahanNotFoundException, KecamatanNotFoundException {
        // id kelurahan balowerti
        String id = "3571021002";
        UpdateKelurahanRequestDto requestDto = new UpdateKelurahanRequestDto();
        requestDto.setCode("3571021002");
        requestDto.setName("Balowerti Update");
        // pindah ke kecamatan Pesantren
        requestDto.setKecamatanId("357103");

        final UrbanVillageDTO responseDto = kelurahanService.updateKelurahan(id, requestDto);

        assertEquals(id, responseDto.getId());
        assertNotNull(responseDto.getUpdatedDate());
        assertNotEquals(responseDto.getCreatedDate(), responseDto.getUpdatedDate());
        assertEquals(requestDto.getName(), responseDto.getName());
    }

    @Test
    @Order(5)
    void deleteKelurahan() throws KelurahanNotFoundException {
        // id kelurahan Ngronggo
        String id = "3571021014";
        kelurahanService.deleteKelurahan(id);
        assertThrows(KelurahanNotFoundException.class, () -> {
            final UrbanVillageDTO kelurahan = kelurahanService.getKelurahanById(id);
        });
    }

    @Test
    @Order(6)
    void getKelurahanByName() throws KelurahanNotFoundException {
        // kelurahan singonegaran
        String name = "singonegaran";
        final UrbanVillageDTO kelurahan = kelurahanService.getKelurahanByName(name);
        assertEquals(name, kelurahan.getName().toLowerCase());
        log.info("Name: {}", kelurahan.getName());
    }

    @Test
    @Order(7)
    void getKelurahanByCode() throws KelurahanNotFoundException {
        // code singonegaran
        String code = "3571031005";
        final UrbanVillageDTO kelurahan = kelurahanService.getKelurahanByCode(code);
        assertEquals(code, kelurahan.getCode());
        log.info("Code: {}", kelurahan.getCode());
    }

    @Test
    @Order(8)
    void getKelurahanByNameContains() {
        // name "ma" [Manggarai, Kemayoran, Manyar Sabrangan]
        String name = "ma";
        final List<UrbanVillageDTO> kelurahanList = kelurahanService.getKelurahanByNameContains(name);
        assertEquals(3, kelurahanList.size());
        for (UrbanVillageDTO kelurahan : kelurahanList) {
            log.info("Name: {}", kelurahan.getName());
            log.info("========");
        }
    }

    @Test
    @Order(9)
    void getKelurahanByKecamatanId() {
        // id kecamatan Pesantren
        String kecamatanId = "357103";
        final List<UrbanVillageDTO> kelurahanList = kelurahanService.getKelurahanByKecamatanId(kecamatanId);
        assertEquals(2, kelurahanList.size());
        for (UrbanVillageDTO kelurahan : kelurahanList) {
            log.info("Name: {}", kelurahan.getName());
            log.info("==========");
        }
    }
}