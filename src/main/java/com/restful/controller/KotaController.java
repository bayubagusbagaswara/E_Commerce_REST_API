package com.restful.controller;

import com.restful.dto.WebResponseDto;
import com.restful.dto.kota.*;
import com.restful.exception.KotaNotFoundException;
import com.restful.exception.ProvinsiNotFoundException;
import com.restful.service.KotaService;
import com.restful.util.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kota")
public class KotaController {

    private final KotaService kotaService;

    public KotaController(KotaService kotaService) {
        this.kotaService = kotaService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<KotaResponseDto> createKota(@RequestBody CreateKotaRequestDto createKotaRequest) throws ProvinsiNotFoundException {
        final KotaResponseDto kotaResponse = kotaService.createKota(createKotaRequest);
        return WebResponseDto.<KotaResponseDto>builder()
                .code(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED.getReasonPhrase())
                .data(kotaResponse)
                .build();
    }

    @GetMapping(value = "/{idKota}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<KotaResponseDto> getKotaById(@PathVariable("idKota") String id) throws KotaNotFoundException {
        final KotaResponseDto kotaResponse = kotaService.getKotaById(id);
        return WebResponseDto.<KotaResponseDto>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(kotaResponse)
                .build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<ListKotaResponseDto> getAllKota(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {

        ListKotaRequestDto requestDto = new ListKotaRequestDto();
        requestDto.setPageNo(pageNo);
        requestDto.setPageSize(pageSize);
        requestDto.setSortBy(sortBy);
        requestDto.setSortDir(sortDir);

        final ListKotaResponseDto allKotaResponse = kotaService.getAllKota(requestDto);
        return WebResponseDto.<ListKotaResponseDto>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(allKotaResponse)
                .build();

    }

    @PutMapping(value = "/{idKota}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<KotaResponseDto> updateKota(@PathVariable("idKota") String id, @RequestBody UpdateKotaRequestDto updateKotaRequestDto) throws ProvinsiNotFoundException, KotaNotFoundException {
        final KotaResponseDto kotaResponse = kotaService.updateKota(id, updateKotaRequestDto);
        return WebResponseDto.<KotaResponseDto>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(kotaResponse)
                .build();
    }

    @DeleteMapping(value = "/{idKota}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<String> deleteKota(@PathVariable("idKota") String id) throws KotaNotFoundException {
        kotaService.deleteKota(id);
        return WebResponseDto.<String>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(null)
                .build();
    }
}
