package com.restful.controller;

import com.restful.dto.WebResponseDto;
import com.restful.dto.kelurahan.*;
import com.restful.exception.KecamatanNotFoundException;
import com.restful.exception.KelurahanNotFoundException;
import com.restful.service.KelurahanService;
import com.restful.util.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kelurahan")
public class KelurahanController {

    private final KelurahanService kelurahanService;

    public KelurahanController(KelurahanService kelurahanService) {
        this.kelurahanService = kelurahanService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<KelurahanResponseDto> createKelurahan(@RequestBody CreateKelurahanRequestDto createKelurahanRequest) throws KecamatanNotFoundException {
        final KelurahanResponseDto kelurahanResponse = kelurahanService.createKelurahan(createKelurahanRequest);
        return WebResponseDto.<KelurahanResponseDto>builder()
                .code(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED.getReasonPhrase())
                .data(kelurahanResponse)
                .build();
    }

    @GetMapping(value = "/{idKelurahan}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<KelurahanResponseDto> getKelurahanById(@PathVariable("idKelurahan") String id) throws KelurahanNotFoundException {
        final KelurahanResponseDto kelurahanResponse = kelurahanService.getKelurahanById(id);
        return WebResponseDto.<KelurahanResponseDto>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(kelurahanResponse)
                .build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<ListKelurahanResponseDto> getAllKelurahan(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {

        ListKelurahanRequestDto requestDto = new ListKelurahanRequestDto();
        requestDto.setPageNo(pageNo);
        requestDto.setPageSize(pageSize);
        requestDto.setSortBy(sortBy);
        requestDto.setSortDir(sortDir);

        final ListKelurahanResponseDto kelurahanResponseList = kelurahanService.getAllKelurahan(requestDto);
        return WebResponseDto.<ListKelurahanResponseDto>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(kelurahanResponseList)
                .build();
    }

    @PutMapping(value = "/{idKelurahan}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<KelurahanResponseDto> updateKelurahan(@PathVariable("idKelurahan") String id, @RequestBody UpdateKelurahanRequestDto updateKelurahanRequest) throws KelurahanNotFoundException, KecamatanNotFoundException {
        final KelurahanResponseDto kelurahanResponse = kelurahanService.updateKelurahan(id, updateKelurahanRequest);
        return WebResponseDto.<KelurahanResponseDto>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(kelurahanResponse)
                .build();
    }

    @DeleteMapping(value = "/{idKelurahan}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<KelurahanResponseDto> deleteKelurahan(@PathVariable("idKelurahan") String id) throws KelurahanNotFoundException {
        kelurahanService.deleteKelurahan(id);
        return WebResponseDto.<KelurahanResponseDto>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(null)
                .build();
    }

    @GetMapping(value = "/name", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<KelurahanResponseDto> getKelurahanByName(@RequestParam("name") String name) throws KelurahanNotFoundException {
        final KelurahanResponseDto kelurahanResponse = kelurahanService.getKelurahanByName(name);
        return WebResponseDto.<KelurahanResponseDto>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(kelurahanResponse)
                .build();
    }

    @GetMapping(value = "/name/contains", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<List<KelurahanResponseDto>> getKelurahanByNameContains(@RequestParam("name") String name) {
        final List<KelurahanResponseDto> kelurahanResponseList = kelurahanService.getKelurahanByNameContains(name);
        return WebResponseDto.<List<KelurahanResponseDto>>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(kelurahanResponseList)
                .build();
    }

    @GetMapping(value = "/code", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<KelurahanResponseDto> getKelurahanByCode(@RequestParam("code") String code) throws KelurahanNotFoundException {
        final KelurahanResponseDto kelurahanResponse = kelurahanService.getKelurahanByCode(code);
        return WebResponseDto.<KelurahanResponseDto>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(kelurahanResponse)
                .build();
    }
}
