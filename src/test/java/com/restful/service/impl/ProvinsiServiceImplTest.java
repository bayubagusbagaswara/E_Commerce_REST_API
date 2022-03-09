package com.restful.service.impl;

import com.restful.dto.provinsi.*;
import com.restful.exception.ProvinsiNotFoundException;
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
class ProvinsiServiceImplTest {

    private static final Logger log = LoggerFactory.getLogger(ProvinsiServiceImplTest.class);

    @Autowired
    ProvinsiServiceImpl provinsiService;

    @Test
    @Order(1)
    void createProvinsi() {
        CreateProvinsiRequestDto requestDto = new CreateProvinsiRequestDto();
        requestDto.setCode("123");
        requestDto.setName("Provinsi Test");

        ProvinsiResponseDto provinsi = provinsiService.createProvinsi(requestDto);

        assertNotNull(provinsi.getId());
        assertNotNull(provinsi.getCreatedAt());
        assertEquals("123", provinsi.getCode());

        log.info("ID: {}", provinsi.getId());
        log.info("Code: {}", provinsi.getCode());
    }

    @Test
    @Order(2)
    void getProvinsiById() throws ProvinsiNotFoundException {
        // id provinsi Jawa Timur
        String id = "35";
        ProvinsiResponseDto provinsi = provinsiService.getProvinsiById(id);
        assertEquals(id, provinsi.getId());
        log.info("Name: {}", provinsi.getName());
    }

    @Test
    @Order(3)
    void getAllProvinsi() {
        // total sample data is 5
        int totalSampleData = 5;
        int pageNo = 0;
        int pageSize = 3;
        String sortBy = "name";
        String sortDir = "asc";

        ListProvinsiRequestDto requestDto = new ListProvinsiRequestDto();
        requestDto.setPageNo(pageNo);
        requestDto.setPageSize(pageSize);
        requestDto.setSortBy(sortBy);
        requestDto.setSortDir(sortDir);

        ListProvinsiResponseDto responseDto = provinsiService.getAllProvinsi(requestDto);
        assertEquals(totalSampleData, responseDto.getTotalElements());
        assertEquals(pageSize, responseDto.getProvinsiResponses().size());
    }

    @Test
    @Order(4)
    void updateProvinsi() throws ProvinsiNotFoundException {
        // id provinsi Jakarta
        String id = "31";

        UpdateProvinsiRequestDto requestDto = new UpdateProvinsiRequestDto();
        requestDto.setCode("31");
        requestDto.setName("DKI Jakarta update");

        ProvinsiResponseDto responseDto = provinsiService.updateProvinsi(id, requestDto);

        assertEquals(id, responseDto.getId());
        assertNotNull(responseDto.getUpdatedAt());
        assertNotEquals(responseDto.getCreatedAt(), responseDto.getUpdatedAt());
    }

    @Test
    @Order(5)
    void deleteProvinsi() {
        // id provinsi Bali
        String id = "51";
        provinsiService.deleteProvinsi(id);
        assertThrows(ProvinsiNotFoundException.class, () -> {
            ProvinsiResponseDto provinsi = provinsiService.getProvinsiById(id);
        });
    }

    @Test
    @Order(6)
    void getProvinsiByName() throws ProvinsiNotFoundException {
        // name provinsi jawa timur
        String name = "jawa timur";
        final ProvinsiResponseDto provinsi = provinsiService.getProvinsiByName(name);
        assertEquals(name, provinsi.getName().toLowerCase());
        log.info("Name: {}", provinsi.getName());
    }

    @Test
    @Order(7)
    void getProvinsiByCode() throws ProvinsiNotFoundException {
        // code provinsi jawa timur
        String code = "35";
        final ProvinsiResponseDto provinsi = provinsiService.getProvinsiByCode(code);
        assertEquals(code, provinsi.getCode());
        log.info("Code: {}", provinsi.getCode());
    }

    @Test
    @Order(8)
    void getProvinsiByNameContains() {
        // contains name ja [Jakarta, Jawa Barat, Jawa Timur, Jawa Tengah]
        String name = "ja";
        final List<ProvinsiResponseDto> provinsiList = provinsiService.getProvinsiByNameContains(name);
        assertEquals(4, provinsiList.size());
        for (ProvinsiResponseDto provinsi : provinsiList) {
            log.info("Name: {}", provinsi.getName());
            log.info("=========");
        }
    }
}