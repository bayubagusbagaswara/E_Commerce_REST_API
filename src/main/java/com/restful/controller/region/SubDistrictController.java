package com.restful.controller.region;

import com.restful.dto.WebResponseDto;
import com.restful.dto.kecamatan.*;
import com.restful.exception.KecamatanNotFoundException;
import com.restful.exception.KotaNotFoundException;
import com.restful.service.KecamatanService;
import com.restful.util.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subdistricts")
public class SubDistrictController {

    private final KecamatanService kecamatanService;

    public SubDistrictController(KecamatanService kecamatanService) {
        this.kecamatanService = kecamatanService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<KecamatanResponseDto> createKecamatan(@RequestBody CreateKecamatanRequestDto createKecamatanRequest) throws KotaNotFoundException {
        final KecamatanResponseDto kecamatanResponse = kecamatanService.createKecamatan(createKecamatanRequest);
        return WebResponseDto.<KecamatanResponseDto>builder()
                .code(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED.getReasonPhrase())
                .data(kecamatanResponse)
                .build();
    }

    @GetMapping(value = "/{idKecamatan}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<KecamatanResponseDto> getKecamatanById(@PathVariable("idKecamatan") String id) throws KecamatanNotFoundException {
        final KecamatanResponseDto kecamatanResponse = kecamatanService.getKecamatanById(id);
        return WebResponseDto.<KecamatanResponseDto>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(kecamatanResponse)
                .build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<ListKecamatanResponseDto> getAllKecamatan(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {

        ListKecamatanRequestDto requestDto = new ListKecamatanRequestDto();
        requestDto.setPageNo(pageNo);
        requestDto.setPageSize(pageSize);
        requestDto.setSortBy(sortBy);
        requestDto.setSortDir(sortDir);

        final ListKecamatanResponseDto kecamatanResponses = kecamatanService.getAllKecamatan(requestDto);
        return WebResponseDto.<ListKecamatanResponseDto>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(kecamatanResponses)
                .build();
    }

    @PutMapping(value = "/{idKecamatan}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<KecamatanResponseDto> updateKecamatan(@PathVariable("idKecamatan") String id, @RequestBody UpdateKecamatanRequestDto updateKecamatanRequest) throws KecamatanNotFoundException, KotaNotFoundException {
        final KecamatanResponseDto kecamatanResponse = kecamatanService.updateKecamatan(id, updateKecamatanRequest);
        return WebResponseDto.<KecamatanResponseDto>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(kecamatanResponse)
                .build();
    }

    @DeleteMapping(value = "/{idKecamatan}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<KecamatanResponseDto> deleteKecamatan(@PathVariable("idKecamatan") String id) throws KecamatanNotFoundException {
        kecamatanService.deleteKecamatan(id);
        return WebResponseDto.<KecamatanResponseDto>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(null)
                .build();
    }

    @GetMapping(value = "/name", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<KecamatanResponseDto> getKecamatanByName(@RequestParam("name") String name) throws KecamatanNotFoundException {
        final KecamatanResponseDto kecamatanResponse = kecamatanService.getKecamatanByName(name);
        return WebResponseDto.<KecamatanResponseDto>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(kecamatanResponse)
                .build();
    }

    @GetMapping(value = "/name/contains", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<List<KecamatanResponseDto>> getKecamatanByNameContains(@RequestParam("name") String name) {
        final List<KecamatanResponseDto> kecamatanResponseList = kecamatanService.getKecamatanByNameContains(name);
        return WebResponseDto.<List<KecamatanResponseDto>>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(kecamatanResponseList)
                .build();
    }

    @GetMapping(value = "/code", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<KecamatanResponseDto> getKecamatanByCode(@RequestParam("code") String code) throws KecamatanNotFoundException {
        final KecamatanResponseDto kecamatanResponse = kecamatanService.getKecamatanByCode(code);
        return WebResponseDto.<KecamatanResponseDto>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(kecamatanResponse)
                .build();
    }
}
