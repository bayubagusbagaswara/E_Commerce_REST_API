package com.restful.controller;

import com.restful.dto.WebResponseDto;
import com.restful.dto.kelurahan.CreateKelurahanRequestDto;
import com.restful.dto.kelurahan.KelurahanResponseDto;
import com.restful.exception.KecamatanNotFoundException;
import com.restful.service.KelurahanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
