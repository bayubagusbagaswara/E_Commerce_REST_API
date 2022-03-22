package com.restful.controller;

import com.restful.dto.WebResponseDto;
import com.restful.dto.provinsi.CreateProvinsiRequestDto;
import com.restful.dto.provinsi.ProvinsiResponseDto;
import com.restful.service.ProvinsiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    // Get All Provinsi
    // Update Provinsi
    // Delete Provinsi
}