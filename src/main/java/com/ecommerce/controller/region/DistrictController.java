package com.ecommerce.controller.region;

import com.ecommerce.dto.WebResponseDto;
import com.ecommerce.dto.kota.*;
import com.ecommerce.dto.region.district.*;
import com.ecommerce.exception.KotaNotFoundException;
import com.ecommerce.exception.ProvinsiNotFoundException;
import com.ecommerce.service.region.DistrictService;
import com.ecommerce.util.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/districts")
public class DistrictController {

    private final DistrictService districtService;

    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<DistrictDTO> createKota(@RequestBody CreateDistrictRequestDTO createKotaRequest) throws ProvinsiNotFoundException {
        final DistrictDTO kotaResponse = districtService.createKota(createKotaRequest);
        return WebResponseDto.<DistrictDTO>builder()
                .code(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED.getReasonPhrase())
                .data(kotaResponse)
                .build();
    }

    @GetMapping(value = "/{idKota}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<DistrictDTO> getKotaById(@PathVariable("idKota") String id) throws KotaNotFoundException {
        final DistrictDTO kotaResponse = districtService.getKotaById(id);
        return WebResponseDto.<DistrictDTO>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(kotaResponse)
                .build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<ListDistrictResponseDTO> getAllKota(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {

        ListDistrictRequestDTO requestDto = new ListDistrictRequestDTO();
        requestDto.setPageNo(pageNo);
        requestDto.setPageSize(pageSize);
        requestDto.setSortBy(sortBy);
        requestDto.setSortDir(sortDir);

        final ListDistrictResponseDTO allKotaResponse = districtService.getAllKota(requestDto);
        return WebResponseDto.<ListDistrictResponseDTO>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(allKotaResponse)
                .build();

    }

    @PutMapping(value = "/{idKota}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<DistrictDTO> updateKota(@PathVariable("idKota") String id, @RequestBody UpdateKotaRequestDto updateKotaRequestDto) throws ProvinsiNotFoundException, KotaNotFoundException {
        final DistrictDTO kotaResponse = districtService.updateKota(id, updateKotaRequestDto);
        return WebResponseDto.<DistrictDTO>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(kotaResponse)
                .build();
    }

    @DeleteMapping(value = "/{idKota}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<String> deleteKota(@PathVariable("idKota") String id) throws KotaNotFoundException {
        districtService.deleteKota(id);
        return WebResponseDto.<String>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(null)
                .build();
    }

    @GetMapping(value = "/name", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<DistrictDTO> getKotaByName(@RequestParam("name") String name) throws KotaNotFoundException {
        final DistrictDTO kotaResponse = districtService.getKotaByName(name);
        return WebResponseDto.<DistrictDTO>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(kotaResponse)
                .build();
    }

    @GetMapping(value = "/name/contains")
    public WebResponseDto<List<DistrictDTO>> getKotaByNameContaining(@RequestParam("name") String name) {
        final List<DistrictDTO> kotaResponseList = districtService.getKotaByNameContains(name);
        return WebResponseDto.<List<DistrictDTO>>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(kotaResponseList)
                .build();
    }

    @GetMapping(value = "/code", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<DistrictDTO> getKotaByCode(@RequestParam("code") String code) throws KotaNotFoundException {
        final DistrictDTO kotaResponse = districtService.getKotaByCode(code);
        return WebResponseDto.<DistrictDTO>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(kotaResponse)
                .build();
    }
}
