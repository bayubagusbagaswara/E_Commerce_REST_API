package com.ecommerce.controller.region;

import com.ecommerce.dto.WebResponseDTO;
import com.ecommerce.dto.kelurahan.*;
import com.ecommerce.dto.region.urbanVillage.*;
import com.ecommerce.exception.KecamatanNotFoundException;
import com.ecommerce.exception.KelurahanNotFoundException;
import com.ecommerce.service.region.UrbanVillageService;
import com.ecommerce.util.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/urbanvillages")
public class UrbanVillageController {

    private final UrbanVillageService urbanVillageService;

    public UrbanVillageController(UrbanVillageService urbanVillageService) {
        this.urbanVillageService = urbanVillageService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDTO<UrbanVillageDTO> createKelurahan(@RequestBody CreateUrbanVillageRequestDTO createKelurahanRequest) throws KecamatanNotFoundException {
        final UrbanVillageDTO kelurahanResponse = urbanVillageService.createKelurahan(createKelurahanRequest);
        return WebResponseDTO.<UrbanVillageDTO>builder()
                .code(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED.getReasonPhrase())
                .data(kelurahanResponse)
                .build();
    }

    @GetMapping(value = "/{idKelurahan}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDTO<UrbanVillageDTO> getKelurahanById(@PathVariable("idKelurahan") String id) throws KelurahanNotFoundException {
        final UrbanVillageDTO kelurahanResponse = urbanVillageService.getKelurahanById(id);
        return WebResponseDTO.<UrbanVillageDTO>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(kelurahanResponse)
                .build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDTO<ListUrbanVillageResponseDTO> getAllKelurahan(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {

        ListUrbanVillageRequestDTO requestDto = new ListUrbanVillageRequestDTO();
        requestDto.setPageNo(pageNo);
        requestDto.setPageSize(pageSize);
        requestDto.setSortBy(sortBy);
        requestDto.setSortDir(sortDir);

        final ListUrbanVillageResponseDTO kelurahanResponseList = urbanVillageService.getAllKelurahan(requestDto);
        return WebResponseDTO.<ListUrbanVillageResponseDTO>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(kelurahanResponseList)
                .build();
    }

    @PutMapping(value = "/{idKelurahan}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDTO<UrbanVillageDTO> updateKelurahan(@PathVariable("idKelurahan") String id, @RequestBody UpdateUrbanVillageRequestDTO updateKelurahanRequest) throws KelurahanNotFoundException, KecamatanNotFoundException {
        final UrbanVillageDTO kelurahanResponse = urbanVillageService.updateKelurahan(id, updateKelurahanRequest);
        return WebResponseDTO.<UrbanVillageDTO>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(kelurahanResponse)
                .build();
    }

    @DeleteMapping(value = "/{idKelurahan}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDTO<UrbanVillageDTO> deleteKelurahan(@PathVariable("idKelurahan") String id) throws KelurahanNotFoundException {
        urbanVillageService.deleteKelurahan(id);
        return WebResponseDTO.<UrbanVillageDTO>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(null)
                .build();
    }

    @GetMapping(value = "/name", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDTO<UrbanVillageDTO> getKelurahanByName(@RequestParam("name") String name) throws KelurahanNotFoundException {
        final UrbanVillageDTO kelurahanResponse = urbanVillageService.getKelurahanByName(name);
        return WebResponseDTO.<UrbanVillageDTO>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(kelurahanResponse)
                .build();
    }

    @GetMapping(value = "/name/contains", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDTO<List<UrbanVillageDTO>> getKelurahanByNameContains(@RequestParam("name") String name) {
        final List<UrbanVillageDTO> kelurahanResponseList = urbanVillageService.getKelurahanByNameContains(name);
        return WebResponseDTO.<List<UrbanVillageDTO>>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(kelurahanResponseList)
                .build();
    }

    @GetMapping(value = "/code", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDTO<UrbanVillageDTO> getKelurahanByCode(@RequestParam("code") String code) throws KelurahanNotFoundException {
        final UrbanVillageDTO kelurahanResponse = urbanVillageService.getKelurahanByCode(code);
        return WebResponseDTO.<UrbanVillageDTO>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(kelurahanResponse)
                .build();
    }
}
