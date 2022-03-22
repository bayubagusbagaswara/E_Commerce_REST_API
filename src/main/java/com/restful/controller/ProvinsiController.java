package com.restful.controller;

import com.restful.dto.WebResponseDto;
import com.restful.dto.provinsi.*;
import com.restful.exception.ProvinsiNotFoundException;
import com.restful.service.ProvinsiService;
import com.restful.util.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/provinsi")
public class ProvinsiController {

    private final ProvinsiService provinsiService;

    public ProvinsiController(ProvinsiService provinsiService) {
        this.provinsiService = provinsiService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<ProvinsiResponseDto> createProvinsi(@RequestBody CreateProvinsiRequestDto createCategoryRequest) {
        final ProvinsiResponseDto provinsiResponse = provinsiService.createProvinsi(createCategoryRequest);
        return WebResponseDto.<ProvinsiResponseDto>builder()
                .code(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED.getReasonPhrase())
                .data(provinsiResponse)
                .build();
    }

    // Get Provinsi By ID, /{idProvinsi}
    @GetMapping(value = "/{idProvinsi}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<ProvinsiResponseDto> getProvinsiById(@PathVariable("idProvinsi") String id) throws ProvinsiNotFoundException {
        final ProvinsiResponseDto provinsiResponse = provinsiService.getProvinsiById(id);
        return WebResponseDto.<ProvinsiResponseDto>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(provinsiResponse)
                .build();
    }

    // Get All Provinsi
    // pengennya Get All ini bisa Get All Provinsi by Name, artinya hanya mengirimkan parameter name
    // lalu di cek jika ada parameter name (name tidak sama dengan null), maka masukkan nilai parameter name ke request
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<ListProvinsiResponseDto> getAllProvinsi(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {

        ListProvinsiRequestDto listProvinsiRequest = new ListProvinsiRequestDto();
        listProvinsiRequest.setPageNo(pageNo);
        listProvinsiRequest.setPageSize(pageSize);
        listProvinsiRequest.setSortBy(sortBy);
        listProvinsiRequest.setSortDir(sortDir);

////        if (name = not null) {
//        listProvinsiRequest.setParamerterName(name)
//
//    }

        final ListProvinsiResponseDto allProvinsiResponse = provinsiService.getAllProvinsi(listProvinsiRequest);
        return WebResponseDto.<ListProvinsiResponseDto>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(allProvinsiResponse)
                .build();
    }

    // Update Provinsi
    @PutMapping(value = "/{idProvinsi}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<ProvinsiResponseDto> updateProvinsi(@PathVariable("idProvinsi") String id, UpdateProvinsiRequestDto updateProvinsiRequest) throws ProvinsiNotFoundException {
        final ProvinsiResponseDto provinsiResponse = provinsiService.updateProvinsi(id, updateProvinsiRequest);
        return WebResponseDto.<ProvinsiResponseDto>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(provinsiResponse)
                .build();
    }

    // Delete Provinsi
    @DeleteMapping(value = "/{idProvinsi}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<String> deleteProvinsi(@PathVariable("idProvinsi") String id) {
        provinsiService.deleteProvinsi(id);
        return WebResponseDto.<String>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(null)
                .build();
    }
}
