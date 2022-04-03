package com.restful.controller;

import com.restful.dto.WebResponseDto;
import com.restful.dto.kecamatan.CreateKecamatanRequestDto;
import com.restful.dto.kecamatan.KecamatanResponseDto;
import com.restful.dto.kecamatan.ListKecamatanRequestDto;
import com.restful.dto.kecamatan.ListKecamatanResponseDto;
import com.restful.exception.KecamatanNotFoundException;
import com.restful.exception.KotaNotFoundException;
import com.restful.service.KecamatanService;
import com.restful.util.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kecamatan")
public class KecamatanController {

    private final KecamatanService kecamatanService;

    public KecamatanController(KecamatanService kecamatanService) {
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

    // get kecamatan by id
    @GetMapping(value = "/{idKecamatan}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<KecamatanResponseDto> getKecamatanById(@PathVariable("idKecamatan") String id) throws KecamatanNotFoundException {
        final KecamatanResponseDto kecamatanResponse = kecamatanService.getKecamatanById(id);
        return WebResponseDto.<KecamatanResponseDto>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(kecamatanResponse)
                .build();
    }

    // get all kecamatan
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

    // update kecamatan
    // delete kecamatan
    // find by code
}
