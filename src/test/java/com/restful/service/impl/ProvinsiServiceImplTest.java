package com.restful.service.impl;

import com.restful.dto.provinsi.*;
import com.restful.exception.ProvinsiNotFoundException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProvinsiServiceImplTest {

    private static final Logger log = LoggerFactory.getLogger(ProvinsiServiceImplTest.class);

    @Autowired
    ProvinsiServiceImpl provinsiService;

    @Test
    void createProvinsi() {
        // request
        CreateProvinsiRequestDto requestDto = new CreateProvinsiRequestDto();
        requestDto.setCode("12345");
        requestDto.setName("Provinsi Test");

        // send
        ProvinsiResponseDto provinsi = provinsiService.createProvinsi(requestDto);

        assertNotNull(provinsi.getId());
        assertNotNull(provinsi.getCreatedAt());
        assertEquals("12345", provinsi.getCode());

        log.info("ID: {}", provinsi.getId());
        log.info("Code: {}", provinsi.getCode());
    }

    @Test
    void getProvinsiById() throws ProvinsiNotFoundException {
        String id = "";
        ProvinsiResponseDto provinsi = provinsiService.getProvinsiById(id);
        assertEquals(id, provinsi.getId());
        log.info("Name: {}", provinsi.getName());
    }

    @Test
    void getAllProvinsi() {
        int totalSampleData = 12;
        int pageNo = 0;
        int pageSize = 5;
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
    void updateProvinsi() throws ProvinsiNotFoundException {
        String id = "";

        UpdateProvinsiRequestDto requestDto = new UpdateProvinsiRequestDto();
        requestDto.setCode("");
        requestDto.setName("Update Test");

        ProvinsiResponseDto responseDto = provinsiService.updateProvinsi(id, requestDto);

        assertEquals(id, responseDto.getId());
        assertNotNull(responseDto.getUpdatedAt());
        assertNotEquals(responseDto.getCreatedAt(), responseDto.getUpdatedAt());
    }

    @Test
    void deleteProvinsi() {
        String id = "";
        provinsiService.deleteProvinsi(id);
        assertThrows(ProvinsiNotFoundException.class, () -> {
            ProvinsiResponseDto provinsi = provinsiService.getProvinsiById(id);
        });
    }

    @Test
    void getProvinsiByName() throws ProvinsiNotFoundException {
        String name = "";
        final ProvinsiResponseDto provinsi = provinsiService.getProvinsiByName(name);
        assertEquals(name, provinsi.getName().toLowerCase());
        log.info("Name: {}", provinsi.getName());
    }

    @Test
    void getProvinsiByCode() throws ProvinsiNotFoundException {
        String code = "";
        final ProvinsiResponseDto provinsi = provinsiService.getProvinsiByCode(code);
        assertEquals(code, provinsi.getCode());
        log.info("Code: {}", provinsi.getCode());
    }

    @Test
    void getProvinsiByNameContains() {
        String name = "jawa";
        final List<ProvinsiResponseDto> provinsiList = provinsiService.getProvinsiByNameContains(name);
        assertEquals(3, provinsiList.size());
        for (ProvinsiResponseDto provinsi : provinsiList) {
            log.info("Name: {}", provinsi.getName());
            log.info("=========");
        }
    }
}