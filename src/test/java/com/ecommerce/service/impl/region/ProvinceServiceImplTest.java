package com.ecommerce.service.impl.region;

import com.ecommerce.dto.region.province.*;
import com.ecommerce.exception.ProvinceNotFoundException;
import com.ecommerce.service.region.ProvinceService;
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
        "classpath:/sql/delete-data-provinsi.sql",
        "classpath:/sql/sample-data-provinsi.sql"
})
class ProvinceServiceImplTest {

    private static final Logger log = LoggerFactory.getLogger(ProvinceServiceImplTest.class);

    @Autowired
    ProvinceService provinceService;

    @Test
    @Order(1)
    void createProvince() {
        CreateProvinceRequestDTO requestDto = new CreateProvinceRequestDTO();
        requestDto.setCode("123");
        requestDto.setName("Provinsi Test");

        ProvinceDTO province = provinceService.createProvince(requestDto);
        assertNotNull(province.getId());
        assertNotNull(province.getCreatedAt());
        assertEquals("123", province.getCode());

        log.info("ID: {}", province.getId());
        log.info("Code: {}", province.getCode());
    }

    @Test
    @Order(2)
    void getProvinceById() {
        // id provinsi Jawa Timur
        String id = "35";
        ProvinceDTO province = provinceService.getProvinceById(id);
        assertEquals(id, province.getId());
        log.info("Name: {}", province.getName());
    }

    @Test
    @Order(3)
    void getAllProvinces() {
        // total sample data is 5
        int totalSampleData = 5;
        int pageNo = 0;
        int pageSize = 3;
        String sortBy = "name";
        String sortDir = "asc";

        ListProvinceRequestDTO requestDto = new ListProvinceRequestDTO(pageNo, pageSize, sortBy, sortDir);

        ListProvinceResponseDTO allProvinces = provinceService.getAllProvinces(requestDto);
        assertEquals(totalSampleData, allProvinces.getTotalElements());
        assertEquals(pageSize, allProvinces.getProvinceDTOList().size());
    }

    @Test
    @Order(4)
    void updateProvince() {
        // id provinsi Jakarta
        String id = "31";

        UpdateProvinceRequestDTO requestDto = new UpdateProvinceRequestDTO();
        requestDto.setCode("31");
        requestDto.setName("DKI Jakarta update");

        ProvinceDTO provinceDTO = provinceService.updateProvince(id, requestDto);
        assertEquals(id, provinceDTO.getId());
        assertNotNull(provinceDTO.getUpdatedAt());
        assertNotEquals(provinceDTO.getCreatedAt(), provinceDTO.getUpdatedAt());
    }

    @Test
    @Order(5)
    void deleteProvince() {
        // id provinsi Bali
        String id = "51";
        provinceService.deleteProvince(id);
        assertThrows(ProvinceNotFoundException.class, () -> {
            ProvinceDTO provinceById = provinceService.getProvinceById(id);
        });
    }

    @Test
    @Order(6)
    void getProvinceByName() {
        // name provinsi jawa timur
        String name = "jawa timur";
        ProvinceDTO province = provinceService.getProvinceByName(name);
        assertEquals(name, province.getName().toLowerCase());
        log.info("Name: {}", province.getName());
    }

    @Test
    @Order(7)
    void getProvinceByCode() {
        // code provinsi jawa timur
        String code = "35";
        ProvinceDTO province = provinceService.getProvinceByCode(code);
        assertEquals(code, province.getCode());
        log.info("Code: {}", province.getCode());
    }

    @Test
    @Order(8)
    void getProvinceByNameContains() {
        // contains name ja [Jakarta, Jawa Barat, Jawa Timur, Jawa Tengah]
        String name = "ja";
        List<ProvinceDTO> provinceDTOList = provinceService.getProvinceByNameContains(name);
        assertEquals(4, provinceDTOList.size());
        for (ProvinceDTO provinceDTO : provinceDTOList) {
            log.info("Name: {}", provinceDTO.getName());
            log.info("=========");
        }
    }
}