package com.ecommerce.service.impl.region;

import com.ecommerce.dto.region.urbanVillage.*;
import com.ecommerce.exception.UrbanVillageNotFoundException;
import com.ecommerce.service.region.UrbanVillageService;
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
    UrbanVillageService urbanVillageService;

    @Test
    @Order(1)
    void createUrbanVillage() {
        CreateUrbanVillageRequestDTO requestDto = new CreateUrbanVillageRequestDTO();
        requestDto.setCode("3571038899");
        requestDto.setName("Kelurahan di Pesantren");
        requestDto.setSubDistrictId("357103");

        final UrbanVillageDTO responseDto = urbanVillageService.createUrbanVillage(requestDto);
        assertNotNull(responseDto.getId());
        assertNotNull(responseDto.getCreatedAt());
        assertEquals(requestDto.getName(), responseDto.getName());
    }

    @Test
    @Order(2)
    void getUrbanVillageById() {
        // id kelurahan Singonegaran, Kecamatan Pesantren
        String id = "3571031005";
        final UrbanVillageDTO responseDto = urbanVillageService.getUrbanVillageById(id);
        assertEquals(id, responseDto.getId());
    }

    @Test
    @Order(3)
    void getAllUrbanVillages() {
        int totalSampleData = 18;
        int pageNo = 0;
        int pageSize = 5;
        String sortBy = "name";
        String sortDir = "asc";

        ListUrbanVillageRequestDTO requestDto = new ListUrbanVillageRequestDTO(pageNo, pageSize, sortBy, sortDir);

        ListUrbanVillageResponseDTO allUrbanVillages = urbanVillageService.getAllUrbanVillages(requestDto);
        assertEquals(totalSampleData, allUrbanVillages.getTotalElements());
        assertEquals(pageSize, allUrbanVillages.getUrbanVillageDTOList().size());
    }

    @Test
    @Order(4)
    void updateKelurahan() {
        // id kelurahan balowerti
        String id = "3571021002";
        UpdateUrbanVillageRequestDTO requestDto = new UpdateUrbanVillageRequestDTO();
        requestDto.setCode("3571021002");
        requestDto.setName("Balowerti Update");
        // pindah ke kecamatan Pesantren
        requestDto.setSubDistrictId("357103");

        UrbanVillageDTO responseDto = urbanVillageService.updateUrbanVillage(id, requestDto);

        assertEquals(id, responseDto.getId());
        assertNotNull(responseDto.getUpdatedAt());
        assertNotEquals(responseDto.getCreatedAt(), responseDto.getUpdatedAt());
        assertEquals(requestDto.getName(), responseDto.getName());
    }

    @Test
    @Order(5)
    void deleteUrbanVillage() {
        // id kelurahan Ngronggo
        String id = "3571021014";
        urbanVillageService.deleteUrbanVillage(id);
        assertThrows(UrbanVillageNotFoundException.class, () -> {
            UrbanVillageDTO urbanVillageById = urbanVillageService.getUrbanVillageById(id);
        });
    }

    @Test
    @Order(6)
    void getUrbanVillageByName() {
        // kelurahan singonegaran
        String name = "singonegaran";
        UrbanVillageDTO urbanVillageDTO = urbanVillageService.getUrbanVillageByName(name);
        assertEquals(name, urbanVillageDTO.getName().toLowerCase());
        log.info("Name: {}", urbanVillageDTO.getName());
    }

    @Test
    @Order(7)
    void getUrbanVillageByCode() {
        // code singonegaran
        String code = "3571031005";
        UrbanVillageDTO urbanVillageDTO = urbanVillageService.getUrbanVillageByCode(code);
        assertEquals(code, urbanVillageDTO.getCode());
        log.info("Code: {}", urbanVillageDTO.getCode());
    }

    @Test
    @Order(8)
    void getAllUrbanVillagesByNameContains() {
        // name "ma" [Manggarai, Kemayoran, Manyar Sabrangan]
        String name = "ma";
        List<UrbanVillageDTO> urbanVillageByNameContains = urbanVillageService.getUrbanVillageByNameContains(name);
        assertEquals(3, urbanVillageByNameContains.size());
        for (UrbanVillageDTO urbanVillageDTO : urbanVillageByNameContains) {
            log.info("Name: {}", urbanVillageDTO.getName());
            log.info("========");
        }
    }

    @Test
    @Order(9)
    void getAllUrbanVillagesBySubDistrictId() {
        // id kecamatan Pesantren
        String subDistrictId = "357103";
        List<UrbanVillageDTO> urbanVillageDTOList = urbanVillageService.getAllUrbanVillagesBySubDistrictId(subDistrictId);
        assertEquals(2, urbanVillageDTOList.size());
        for (UrbanVillageDTO urbanVillageDTO : urbanVillageDTOList) {
            log.info("Name: {}", urbanVillageDTO.getName());
            log.info("==========");
        }
    }
}