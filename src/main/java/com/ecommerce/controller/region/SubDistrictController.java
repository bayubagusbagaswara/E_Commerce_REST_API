package com.ecommerce.controller.region;

import com.ecommerce.dto.WebResponseDto;
import com.ecommerce.dto.kecamatan.*;
import com.ecommerce.dto.region.subDistrict.*;
import com.ecommerce.exception.KecamatanNotFoundException;
import com.ecommerce.exception.KotaNotFoundException;
import com.ecommerce.service.region.SubDistrictService;
import com.ecommerce.util.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subdistricts")
public class SubDistrictController {

    private final SubDistrictService subDistrictService;

    public SubDistrictController(SubDistrictService subDistrictService) {
        this.subDistrictService = subDistrictService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<SubDistrictDTO> createKecamatan(@RequestBody CreateSubDistrictRequestDTO createKecamatanRequest) throws KotaNotFoundException {
        final SubDistrictDTO kecamatanResponse = subDistrictService.createKecamatan(createKecamatanRequest);
        return WebResponseDto.<SubDistrictDTO>builder()
                .code(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED.getReasonPhrase())
                .data(kecamatanResponse)
                .build();
    }

    @GetMapping(value = "/{idKecamatan}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<SubDistrictDTO> getKecamatanById(@PathVariable("idKecamatan") String id) throws KecamatanNotFoundException {
        final SubDistrictDTO kecamatanResponse = subDistrictService.getKecamatanById(id);
        return WebResponseDto.<SubDistrictDTO>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(kecamatanResponse)
                .build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<ListSubDistrictResponseDTO> getAllKecamatan(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {

        ListSubDistrictRequestDTO requestDto = new ListSubDistrictRequestDTO();
        requestDto.setPageNo(pageNo);
        requestDto.setPageSize(pageSize);
        requestDto.setSortBy(sortBy);
        requestDto.setSortDir(sortDir);

        final ListSubDistrictResponseDTO kecamatanResponses = subDistrictService.getAllKecamatan(requestDto);
        return WebResponseDto.<ListSubDistrictResponseDTO>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(kecamatanResponses)
                .build();
    }

    @PutMapping(value = "/{idKecamatan}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<SubDistrictDTO> updateKecamatan(@PathVariable("idKecamatan") String id, @RequestBody UpdateSubDistrictRequestDTO updateKecamatanRequest) throws KecamatanNotFoundException, KotaNotFoundException {
        final SubDistrictDTO kecamatanResponse = subDistrictService.updateKecamatan(id, updateKecamatanRequest);
        return WebResponseDto.<SubDistrictDTO>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(kecamatanResponse)
                .build();
    }

    @DeleteMapping(value = "/{idKecamatan}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<SubDistrictDTO> deleteKecamatan(@PathVariable("idKecamatan") String id) throws KecamatanNotFoundException {
        subDistrictService.deleteKecamatan(id);
        return WebResponseDto.<SubDistrictDTO>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(null)
                .build();
    }

    @GetMapping(value = "/name", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<SubDistrictDTO> getKecamatanByName(@RequestParam("name") String name) throws KecamatanNotFoundException {
        final SubDistrictDTO kecamatanResponse = subDistrictService.getKecamatanByName(name);
        return WebResponseDto.<SubDistrictDTO>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(kecamatanResponse)
                .build();
    }

    @GetMapping(value = "/name/contains", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<List<SubDistrictDTO>> getKecamatanByNameContains(@RequestParam("name") String name) {
        final List<SubDistrictDTO> kecamatanResponseList = subDistrictService.getKecamatanByNameContains(name);
        return WebResponseDto.<List<SubDistrictDTO>>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(kecamatanResponseList)
                .build();
    }

    @GetMapping(value = "/code", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<SubDistrictDTO> getKecamatanByCode(@RequestParam("code") String code) throws KecamatanNotFoundException {
        final SubDistrictDTO kecamatanResponse = subDistrictService.getKecamatanByCode(code);
        return WebResponseDto.<SubDistrictDTO>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(kecamatanResponse)
                .build();
    }
}
