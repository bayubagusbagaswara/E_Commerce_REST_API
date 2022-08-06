package com.ecommerce.service.impl.region;

import com.ecommerce.dto.region.district.*;
import com.ecommerce.exception.DistrictNotFoundException;
import com.ecommerce.service.region.DistrictService;
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
        "classpath:/sql/delete-data-kota.sql",
        "classpath:/sql/delete-data-provinsi.sql",
        "classpath:/sql/sample-data-provinsi.sql",
        "classpath:/sql/sample-data-kota.sql"
})
class DistrictServiceImplTest {

    private final static Logger log = LoggerFactory.getLogger(DistrictServiceImplTest.class);

    @Autowired
    DistrictService districtService;

    @Test
    @Order(1)
    void createKota() {
        CreateDistrictRequestDTO requestDto = new CreateDistrictRequestDTO();
        requestDto.setCode("35123");
        requestDto.setName("Kota Test");
        // provinsi Jawa Timur
        requestDto.setProvinceId("35");

        DistrictDTO district = districtService.createDistrict(requestDto);
        assertNotNull(district.getId());
        assertNotNull(district.getCreatedAt());
        assertEquals(requestDto.getName(), district.getName());
    }

    @Test
    @Order(2)
    void getDistrictById() {
        // id kota Kediri, Jawa Timur
        String id = "3571";
        DistrictDTO district = districtService.getDistrictById(id);
        assertEquals(id, district.getId());
        log.info("ID: {}", district.getId());
    }

    @Test
    @Order(3)
    void getAllDistricts() {
        // total sample kota 7
        int totalSample = 7;
        int pageNo = 0;
        int pageSize = 3;
        String sortBy = "name";
        String sortDir = "asc";

        ListDistrictRequestDTO requestDto = new ListDistrictRequestDTO(pageNo, pageSize, sortBy, sortDir);

        ListDistrictResponseDTO allDistricts = districtService.getAllDistricts(requestDto);
        assertEquals(totalSample, allDistricts.getTotalElements());
        assertEquals(pageSize, allDistricts.getDistrictDTOList().size());
    }

    @Test
    @Order(4)
    void updateDistrict() {
        // update kota Denpasar pindah ke provinsi jawa timur
        String id = "5171";
        UpdateDistrictRequestDTO requestDto = new UpdateDistrictRequestDTO();
        requestDto.setCode("35123");
        requestDto.setName("KOTA DENPASAR Update");
        requestDto.setProvinceId("35");

        DistrictDTO districtDTO = districtService.updateDistrict(id, requestDto);
        assertEquals(id, districtDTO.getId());
        assertNotNull(districtDTO.getUpdatedAt());
        assertNotEquals(districtDTO.getCreatedAt(), districtDTO.getUpdatedAt());
        assertEquals(requestDto.getName(), districtDTO.getName());
    }

    @Test
    @Order(5)
    void deleteDistrictById() {
        // delete kota Denpasar
        String id = "5171";
        districtService.deleteDistrict(id);
        assertThrows(DistrictNotFoundException.class, () -> {
            DistrictDTO districtById = districtService.getDistrictById(id);
        });
    }

    @Test
    @Order(6)
    void getDistrictByName() {
        String name = "kota kediri";
        DistrictDTO district = districtService.getDistrictByName(name);
        assertEquals(name, district.getName().toLowerCase());
        log.info("Name: {}", district.getName());
    }

    @Test
    @Order(7)
    void getDistrictByCode() {
        // code kota kediri
        String code = "3571";
        DistrictDTO district = districtService.getDistrictByCode(code);
        assertEquals(code, district.getCode());
        log.info("Code: {}", district.getCode());
    }

    @Test
    @Order(8)
    void getAllDistrictsByNameContains() {
        // contains name kediri [KAB KEDIRI, KOTA KEDIRI]
        String name = "kediri";
        List<DistrictDTO> districtDTOList = districtService.getDistrictByNameContains(name);
        assertEquals(2, districtDTOList.size());
        for (DistrictDTO districtDTO : districtDTOList) {
            log.info("Name: {}", districtDTO.getName());
            log.info("=======");
        }
    }

    @Test
    @Order(9)
    void getAllDistrictByProvinceId() {
        // provinsi jawa timur [Kota Surabaya, Kota Kediri, Kab Kediri]
        String provinceId = "35";
        List<DistrictDTO> districtDTOList = districtService.getAllDistrictsByProvinceId(provinceId);
        assertEquals(3, districtDTOList.size());
        for (DistrictDTO districtDTO : districtDTOList) {
            log.info("Name: {}", districtDTO.getName());
            log.info("=======");
        }
    }
}