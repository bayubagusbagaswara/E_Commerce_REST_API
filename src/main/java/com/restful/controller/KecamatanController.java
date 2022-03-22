package com.restful.controller;

import com.restful.dto.WebResponseDto;
import com.restful.dto.kecamatan.CreateKecamatanRequestDto;
import com.restful.dto.kecamatan.KecamatanResponseDto;
import com.restful.exception.KotaNotFoundException;
import com.restful.service.KecamatanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
