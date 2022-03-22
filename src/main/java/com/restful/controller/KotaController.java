package com.restful.controller;

import com.restful.dto.WebResponseDto;
import com.restful.dto.kota.CreateKotaRequestDto;
import com.restful.dto.kota.KotaResponseDto;
import com.restful.exception.ProvinsiNotFoundException;
import com.restful.service.KotaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
