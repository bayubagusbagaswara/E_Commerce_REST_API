package com.ecommerce.service.impl;

import com.ecommerce.dto.region.subDistrict.*;
import com.ecommerce.exception.SubDistrictNotFoundException;
import com.ecommerce.service.region.SubDistrictService;
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
    SubDistrictService subDistrictService;

    @Test
    @Order(1)
    void createSubDistrict() {
        CreateSubDistrictRequestDTO requestDto = new CreateSubDistrictRequestDTO();
        requestDto.setCode("3571890");
        requestDto.setName("Kecamatan Kediri");
        // kota Kediri id 3571
        requestDto.setDistrictId("3571");

        SubDistrictDTO subDistrict = subDistrictService.createSubDistrict(requestDto);
        assertNotNull(subDistrict.getId());
        assertNotNull(subDistrict.getCreatedAt());
        assertEquals(requestDto.getName(), subDistrict.getName());
    }

    @Test
    @Order(2)
    void getSubDistrictById() {
        // id kecamatan Pesantren, Kota Kediri
        String id = "357103";
        SubDistrictDTO subDistrictDTO = subDistrictService.getSubDistrictById(id);
        assertEquals(id, subDistrictDTO.getId());
    }

    @Test
    @Order(3)
    void getAllSubDistricts() {
        int totalSample = 18;
        int pageNo = 0;
        int pageSize = 5;
        String sortBy = "name";
        String sortDir = "asc";

        ListSubDistrictRequestDTO requestDto = new ListSubDistrictRequestDTO(pageNo, pageSize, sortBy, sortDir);

        ListSubDistrictResponseDTO allSubDistricts = subDistrictService.getAllSubDistricts(requestDto);
        assertEquals(totalSample, allSubDistricts.getTotalElements());
        assertEquals(pageSize, allSubDistricts.getSubDistrictDTOList().size());
    }

    @Test
    @Order(4)
    void updateSubDistrict() {
        // update kecamatan Mojoroto id 357101
        String id = "357101";
        UpdateSubDistrictRequestDTO requestDto = new UpdateSubDistrictRequestDTO();
        requestDto.setCode("357101");
        requestDto.setName("Mojoroto Update");
        // pindah ke kota Surabaya id 3578
        requestDto.setDistrictId("3578");

        SubDistrictDTO subDistrictDTO = subDistrictService.updateSubDistrict(id, requestDto);
        assertEquals(id, subDistrictDTO.getId());
        assertNotNull(subDistrictDTO.getUpdatedAt());
        assertNotEquals(subDistrictDTO.getCreatedAt(), subDistrictDTO.getUpdatedAt());
        assertEquals(requestDto.getName(), subDistrictDTO.getName());
    }

    @Test
    @Order(5)
    void deleteSubDistrict() {
        // delete kecamatan Mojoroto
        String id = "357101";
        subDistrictService.deleteSubDistrict(id);
        assertThrows(SubDistrictNotFoundException.class, () -> {
            SubDistrictDTO subDistrictById = subDistrictService.getSubDistrictById(id);
        });
    }

    @Test
    @Order(6)
    void getSubDistrictByName() {
        String name = "pesantren";
        SubDistrictDTO subDistrictDTO = subDistrictService.getSubDistrictByName(name);
        assertEquals(name, subDistrictDTO.getName().toLowerCase());
        log.info("Name: {}", subDistrictDTO.getName());
    }

    @Test
    @Order(7)
    void getSubDistrictByCode() {
        // code kecamatan Pesantren 357103
        String code = "357103";
        SubDistrictDTO subDistrictDTO = subDistrictService.getSubDistrictByCode(code);
        assertEquals(code, subDistrictDTO.getCode());
        log.info("Code: {}", subDistrictDTO.getCode());
    }

    @Test
    @Order(8)
    void getSubDistrictByNameContains() {
        // name 'ke' [Kemayoran, Kebayoran Lama, Kebayoran Baru, Simokerto, Kenjeran, Sambikerep]
        String name = "ke";
        List<SubDistrictDTO> subDistrictDTOList = subDistrictService.getSubDistrictByNameContains(name);
        assertEquals(6, subDistrictDTOList.size());
        for (SubDistrictDTO subDistrictDTO : subDistrictDTOList) {
            log.info("Name: {}", subDistrictDTO.getName());
            log.info("==========");
        }
    }

    @Test
    @Order(9)
    void getAllSubDistrictsByDistrictId() {
        // id kota Kediri [Kota, Pesantren, Mojoroto]
        String districtId = "3571";
        List<SubDistrictDTO> subDistrictDTOList = subDistrictService.getAllSubDistrictsByDistrictId(districtId);
        assertEquals(3, subDistrictDTOList.size());
        for (SubDistrictDTO subDistrictDTO : subDistrictDTOList) {
            log.info("Name: {}", subDistrictDTO.getName());
            log.info("==========");
        }
    }
}