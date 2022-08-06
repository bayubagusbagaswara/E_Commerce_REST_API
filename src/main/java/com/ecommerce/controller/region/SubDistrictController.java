package com.ecommerce.controller.region;

import com.ecommerce.dto.WebResponseDTO;
import com.ecommerce.dto.region.subDistrict.*;
import com.ecommerce.service.region.SubDistrictService;
import com.ecommerce.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sub/districts")
public class SubDistrictController {

    private final SubDistrictService subDistrictService;

    @Autowired
    public SubDistrictController(SubDistrictService subDistrictService) {
        this.subDistrictService = subDistrictService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<SubDistrictDTO>> createSubDistrict(@RequestBody CreateSubDistrictRequestDTO createSubDistrictRequestDTO) {
        SubDistrictDTO subDistrict = subDistrictService.createSubDistrict(createSubDistrictRequestDTO);
        return new ResponseEntity<>(new WebResponseDTO<>(201, "CREATED", subDistrict), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{subDistrictId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<SubDistrictDTO>> getSubDistrictById(@PathVariable(name = "subDistrictId") String id) {
        SubDistrictDTO subDistrict = subDistrictService.getSubDistrictById(id);
        return new ResponseEntity<>(new WebResponseDTO<>(200, "OK", subDistrict), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<ListSubDistrictResponseDTO>> getAllSubDistricts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {

        ListSubDistrictRequestDTO listSubDistrictRequestDTO = new ListSubDistrictRequestDTO(pageNo, pageSize, sortBy, sortDir);
        ListSubDistrictResponseDTO allSubDistricts = subDistrictService.getAllSubDistricts(listSubDistrictRequestDTO);
        return new ResponseEntity<>(new WebResponseDTO<>(200, "OK", allSubDistricts), HttpStatus.OK);
    }

    @PutMapping(value = "/{subDistrictId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<SubDistrictDTO>> updateSubDistrict(@PathVariable(name = "subDistrictId") String id, @RequestBody UpdateSubDistrictRequestDTO updateSubDistrictRequestDTO) {
        SubDistrictDTO subDistrict = subDistrictService.updateSubDistrict(id, updateSubDistrictRequestDTO);
        return new ResponseEntity<>(new WebResponseDTO<>(200, "CREATED", subDistrict), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{subDistrictId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<String>> deleteSubDistrict(@PathVariable(name = "subDistrictId") String id) {
        subDistrictService.deleteSubDistrict(id);
        return new ResponseEntity<>(new WebResponseDTO<>(200, "OK", "Successfully Deleted"), HttpStatus.OK);
    }

    @GetMapping(value = "/name", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<SubDistrictDTO>> getSubDistrictByName(@RequestParam(name = "name") String name) {
        SubDistrictDTO subDistrictDTO = subDistrictService.getSubDistrictByName(name);
        return new ResponseEntity<>(new WebResponseDTO<>(200, "OK", subDistrictDTO), HttpStatus.OK);
    }

    @GetMapping(value = "/name/contains", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<List<SubDistrictDTO>>> getSubDistrictsByNameContains(@RequestParam(name = "name") String name) {
        List<SubDistrictDTO> subDistrictDTOList = subDistrictService.getSubDistrictByNameContains(name);
        return new ResponseEntity<>(new WebResponseDTO<>(200, "OK", subDistrictDTOList), HttpStatus.OK);
    }

    @GetMapping(value = "/code", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<SubDistrictDTO>> getSubDistrictByCode(@RequestParam("code") String code) {
        SubDistrictDTO subDistrictDTO = subDistrictService.getSubDistrictByCode(code);
        return new ResponseEntity<>(new WebResponseDTO<>(200, "OK", subDistrictDTO), HttpStatus.OK);
    }
}
