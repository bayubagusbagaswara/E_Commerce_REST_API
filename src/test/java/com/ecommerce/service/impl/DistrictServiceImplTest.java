package com.ecommerce.service.impl;

import com.ecommerce.dto.kota.*;
import com.ecommerce.dto.region.district.*;
import com.ecommerce.exception.DistrictNotFoundException;
import com.ecommerce.exception.ProvinceNotFoundException;
import com.ecommerce.service.impl.region.DistrictServiceImpl;
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
class DistrictServiceImplTest {

    private final static Logger log = LoggerFactory.getLogger(DistrictServiceImplTest.class);

    @Autowired
    DistrictServiceImpl kotaService;

    @Test
    @Order(1)
    void createKota() throws ProvinceNotFoundException {
        CreateDistrictRequestDTO requestDto = new CreateDistrictRequestDTO();
        requestDto.setCode("35123");
        requestDto.setName("Kota Test");
        // provinsi Jawa Timur
        requestDto.setProvinsiId("35");

        final DistrictDTO kota = kotaService.createKota(requestDto);
        assertNotNull(kota.getId());
        assertNotNull(kota.getCreatedDate());
        assertEquals(requestDto.getName(), kota.getName());

        log.info("ID: {}", kota.getId());
        log.info("Name: {}", kota.getName());
    }

    @Test
    @Order(2)
    void getKotaById() throws DistrictNotFoundException {
        // id kota Kediri, Jawa Timur
        String id = "3571";
        final DistrictDTO kota = kotaService.getKotaById(id);
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

        ListDistrictRequestDTO requestDto = new ListDistrictRequestDTO();
        requestDto.setPageNo(pageNo);
        requestDto.setPageSize(pageSize);
        requestDto.setSortBy(sortBy);
        requestDto.setSortDir(sortDir);

        final ListDistrictResponseDTO responseDto = kotaService.getAllKota(requestDto);
        assertEquals(totalSampleKota, responseDto.getTotalElements());
        assertEquals(pageSize, responseDto.getKotaList().size());
    }

    @Test
    @Order(4)
    void updateKota() throws ProvinceNotFoundException, DistrictNotFoundException {
        // update kota Denpasar pindah ke provinsi jawa timur
        String id = "5171";
        UpdateDistrictRequestDTO requestDto = new UpdateDistrictRequestDTO();
        requestDto.setCode("35123");
        requestDto.setName("KOTA DENPASAR Update");
        requestDto.setProvinsiId("35");

        final DistrictDTO responseDto = kotaService.updateKota(id, requestDto);
        assertEquals(id, responseDto.getId());
        assertNotNull(responseDto.getUpdatedDate());
        assertNotEquals(responseDto.getCreatedDate(), responseDto.getUpdatedDate());
        assertEquals(requestDto.getName(), responseDto.getName());
    }

    @Test
    @Order(5)
    void deleteKota() throws DistrictNotFoundException {
        // delete kota Denpasar
        String id = "5171";
        kotaService.deleteKota(id);
        assertThrows(DistrictNotFoundException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                final DistrictDTO kota = kotaService.getKotaById(id);
            }
        });
    }

    @Test
    @Order(6)
    void getKotaByName() throws DistrictNotFoundException {
        String name = "kota kediri";
        final DistrictDTO kota = kotaService.getKotaByName(name);
        assertEquals(name, kota.getName().toLowerCase());
        log.info("Name: {}", kota.getName());
    }

    @Test
    @Order(7)
    void getKotaByCode() throws DistrictNotFoundException {
        // code kota kediri
        String code = "3571";
        final DistrictDTO kota = kotaService.getKotaByCode(code);
        assertEquals(code, kota.getCode());
        log.info("Code: {}", kota.getCode());
    }

    @Test
    @Order(8)
    void getKotaByNameContains() {
        // contains name kediri [KAB KEDIRI, KOTA KEDIRI]
        String name = "kediri";
        final List<DistrictDTO> kotaList = kotaService.getKotaByNameContains(name);
        assertEquals(2, kotaList.size());
        for (DistrictDTO kota : kotaList) {
            log.info("Name: {}", kota.getName());
            log.info("=======");
        }
    }

    @Test
    @Order(9)
    void getKotaByProvinsiId() {
        // provinsi jawa timur [Kota Surabaya, Kota Kediri, Kab Kediri]
        String provinsiId = "35";
        final List<DistrictDTO> kotaList = kotaService.getKotaByProvinsiId(provinsiId);
        assertEquals(3, kotaList.size());
        for (DistrictDTO kota : kotaList) {
            log.info("Name: {}", kota.getName());
            log.info("=======");
        }
    }
}