package com.restful.controller.region;

import com.restful.dto.WebResponseDto;
import com.restful.dto.provinsi.*;
import com.restful.exception.ProvinsiNotFoundException;
import com.restful.service.ProvinsiService;
import com.restful.util.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/provinces")
public class ProvinceController {

    private final ProvinsiService provinsiService;

    public ProvinceController(ProvinsiService provinsiService) {
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

    @GetMapping(value = "/{idProvinsi}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<ProvinsiResponseDto> getProvinsiById(@PathVariable("idProvinsi") String id) throws ProvinsiNotFoundException {
        final ProvinsiResponseDto provinsiResponse = provinsiService.getProvinsiById(id);
        return WebResponseDto.<ProvinsiResponseDto>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(provinsiResponse)
                .build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<ListProvinsiResponseDto> getAllProvinsi(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "searchBy", defaultValue = AppConstants.DEFAULT_SEARCH_BY, required = false) String searchBy,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {

        ListProvinsiRequestDto listProvinsiRequest = new ListProvinsiRequestDto();
        listProvinsiRequest.setPageNo(pageNo);
        listProvinsiRequest.setPageSize(pageSize);
        listProvinsiRequest.setSortBy(sortBy);
        listProvinsiRequest.setSortDir(sortDir);

        final ListProvinsiResponseDto allProvinsiResponse = provinsiService.getAllProvinsi(listProvinsiRequest);
        return WebResponseDto.<ListProvinsiResponseDto>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(allProvinsiResponse)
                .build();
    }

    @PutMapping(value = "/{idProvinsi}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<ProvinsiResponseDto> updateProvinsi(@PathVariable("idProvinsi") String id, UpdateProvinsiRequestDto updateProvinsiRequest) throws ProvinsiNotFoundException {
        final ProvinsiResponseDto provinsiResponse = provinsiService.updateProvinsi(id, updateProvinsiRequest);
        return WebResponseDto.<ProvinsiResponseDto>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(provinsiResponse)
                .build();
    }

    @DeleteMapping(value = "/{idProvinsi}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<String> deleteProvinsi(@PathVariable("idProvinsi") String id) throws ProvinsiNotFoundException {
        provinsiService.deleteProvinsi(id);
        return WebResponseDto.<String>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(null)
                .build();
    }

    @GetMapping(value = "/name", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<ProvinsiResponseDto> getProvinsiByName(@RequestParam("name") String name) throws ProvinsiNotFoundException {
        final ProvinsiResponseDto provinsiResponse = provinsiService.getProvinsiByName(name);
        return WebResponseDto.<ProvinsiResponseDto>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(provinsiResponse)
                .build();
    }

    @GetMapping(value = "/name/contains", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<List<ProvinsiResponseDto>> getProvinsiByNameContaining(@RequestParam("name") String name) {
        final List<ProvinsiResponseDto> provinsiResponseList = provinsiService.getProvinsiByNameContains(name);
        return WebResponseDto.<List<ProvinsiResponseDto>>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(provinsiResponseList)
                .build();
    }

    @GetMapping(value = "/code", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto<ProvinsiResponseDto> getProvinsiByCode(@RequestParam("code") String code) throws ProvinsiNotFoundException {
        final ProvinsiResponseDto provinsiResponse = provinsiService.getProvinsiByCode(code);
        return WebResponseDto.<ProvinsiResponseDto>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(provinsiResponse)
                .build();
    }
}
