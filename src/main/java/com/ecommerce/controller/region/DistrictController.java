package com.ecommerce.controller.region;

import com.ecommerce.dto.WebResponseDTO;
import com.ecommerce.dto.region.district.*;
import com.ecommerce.service.region.DistrictService;
import com.ecommerce.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/districts")
public class DistrictController {

    private final DistrictService districtService;

    @Autowired
    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<DistrictDTO>> createDistrict(@RequestBody CreateDistrictRequestDTO createDistrictRequestDTO) {
        DistrictDTO district = districtService.createDistrict(createDistrictRequestDTO);
        return new ResponseEntity<>(new WebResponseDTO<>(201, "CREATED", district), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{districtId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<DistrictDTO>> getDistrictById(@PathVariable(name = "districtId") String id) {
        DistrictDTO district = districtService.getDistrictById(id);
        return new ResponseEntity<>(new WebResponseDTO<>(200, "OK", district), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<ListDistrictResponseDTO>> getAllDistricts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {

        ListDistrictRequestDTO listDistrictRequestDTO = new ListDistrictRequestDTO(pageNo, pageSize, sortBy, sortDir);
        ListDistrictResponseDTO allDistricts = districtService.getAllDistricts(listDistrictRequestDTO);
        return new ResponseEntity<>(new WebResponseDTO<>(200, "OK", allDistricts), HttpStatus.OK);
    }

    @PutMapping(value = "/{districtId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<DistrictDTO>> updateKota(@PathVariable(name = "districtId") String id, @RequestBody UpdateDistrictRequestDTO updateDistrictRequestDTO) {
        DistrictDTO district = districtService.updateDistrict(id, updateDistrictRequestDTO);
        return new ResponseEntity<>(new WebResponseDTO<>(200, "OK", district), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{districtId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<String>> deleteKota(@PathVariable(name = "districtId") String id) {
        districtService.deleteDistrict(id);
        return new ResponseEntity<>(new WebResponseDTO<>(200, "OK", "Successfully deleted"), HttpStatus.OK);
    }

    @GetMapping(value = "/name", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<DistrictDTO>> getDistrictByName(@RequestParam(name = "name") String name) {
        DistrictDTO district = districtService.getDistrictByName(name);
        return new ResponseEntity<>(new WebResponseDTO<>(200, "OK", district), HttpStatus.OK);
    }

    @GetMapping(value = "/name/contains")
    public ResponseEntity<WebResponseDTO<List<DistrictDTO>>> getDistrictsByNameContaining(@RequestParam("name") String name) {
        List<DistrictDTO> district = districtService.getDistrictByNameContains(name);
        return new ResponseEntity<>(new WebResponseDTO<>(200, "OK", district), HttpStatus.OK);
    }

    @GetMapping(value = "/code", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<DistrictDTO>> getDistrictByCode(@RequestParam("code") String code) {
        DistrictDTO district = districtService.getDistrictByCode(code);
        return new ResponseEntity<>(new WebResponseDTO<>(200, "OK", district), HttpStatus.OK);
    }
}
