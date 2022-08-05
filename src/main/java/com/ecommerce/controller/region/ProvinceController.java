package com.ecommerce.controller.region;

import com.ecommerce.dto.WebResponseDTO;
import com.ecommerce.dto.region.province.*;
import com.ecommerce.service.region.ProvinceService;
import com.ecommerce.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/provinces")
public class ProvinceController {

    private final ProvinceService provinceService;

    @Autowired
    public ProvinceController(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<ProvinceDTO>> createProvince(@RequestBody CreateProvinceRequestDTO createProvinceRequestDTO) {
        ProvinceDTO province = provinceService.createProvince(createProvinceRequestDTO);
        return new ResponseEntity<>(new WebResponseDTO<>(201, "CREATED", province), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{provinceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<ProvinceDTO>> getProvinceById(@PathVariable(name = "provinceId") String id) {
        ProvinceDTO province = provinceService.getProvinceById(id);
        return new ResponseEntity<>(new WebResponseDTO<>(200, "OK", province), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<ListProvinceResponseDTO>> getAllProvinces(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {

        ListProvinceRequestDTO listProvinceRequestDTO = new ListProvinceRequestDTO(pageNo, pageSize, sortBy, sortDir);
        ListProvinceResponseDTO provinces = provinceService.getAllProvinces(listProvinceRequestDTO);
        return new ResponseEntity<>(new WebResponseDTO<>(200, "OK", provinces), HttpStatus.OK);
    }

    @PutMapping(value = "/{provinceId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<ProvinceDTO>> updateProvince(@PathVariable(name = "provinceId") String id, UpdateProvinceRequestDTO updateProvinceRequestDTO) {
        ProvinceDTO province = provinceService.updateProvince(id, updateProvinceRequestDTO);
        return new ResponseEntity<>(new WebResponseDTO<>(201, "CREATED", province), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{provinceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<String>> deleteProvince(@PathVariable(name = "provinceId") String id) {
        provinceService.deleteProvince(id);
        return new ResponseEntity<>(new WebResponseDTO<>(200, "OK", "Successfully deleted"), HttpStatus.OK);
    }

    @GetMapping(value = "/name", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<ProvinceDTO>> getProvinceByName(@RequestParam(name = "name") String name) {
        ProvinceDTO province = provinceService.getProvinceByName(name);
        return new ResponseEntity<>(new WebResponseDTO<>(200, "OK", province), HttpStatus.OK);
    }

    @GetMapping(value = "/name/contains", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<List<ProvinceDTO>>> getProvinceByNameContaining(@RequestParam(name = "name") String name) {
        List<ProvinceDTO> province = provinceService.getProvinceByNameContains(name);
        return new ResponseEntity<>(new WebResponseDTO<>(200, "OK", province), HttpStatus.OK);
    }

    @GetMapping(value = "/code", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<ProvinceDTO>> getProvinceByCode(@RequestParam(name = "code") String code) {
        ProvinceDTO province = provinceService.getProvinceByCode(code);
        return new ResponseEntity<>(new WebResponseDTO<>(200, "OK", province), HttpStatus.OK);
    }
}
