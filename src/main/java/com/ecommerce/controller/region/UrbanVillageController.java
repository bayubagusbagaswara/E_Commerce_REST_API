package com.ecommerce.controller.region;

import com.ecommerce.dto.WebResponseDTO;
import com.ecommerce.dto.region.urbanVillage.*;
import com.ecommerce.service.region.UrbanVillageService;
import com.ecommerce.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/urban-villages")
public class UrbanVillageController {

    private final UrbanVillageService urbanVillageService;

    @Autowired
    public UrbanVillageController(UrbanVillageService urbanVillageService) {
        this.urbanVillageService = urbanVillageService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<UrbanVillageDTO>> createUrbanVillage(@RequestBody CreateUrbanVillageRequestDTO createUrbanVillageRequestDTO) {
        UrbanVillageDTO urbanVillage = urbanVillageService.createUrbanVillage(createUrbanVillageRequestDTO);
        return new ResponseEntity<>(new WebResponseDTO<>(201, "CREATED", urbanVillage), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{urbanVillageId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<UrbanVillageDTO>> getUrbanVillageById(@PathVariable(name = "urbanVillageId") String id) {
        UrbanVillageDTO urbanVillageDTO = urbanVillageService.getUrbanVillageById(id);
        return new ResponseEntity<>(new WebResponseDTO<>(200, "OK", urbanVillageDTO), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<ListUrbanVillageResponseDTO>> getAllUrbanVillages(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {

        ListUrbanVillageRequestDTO listUrbanVillageRequestDTO = new ListUrbanVillageRequestDTO(pageNo, pageSize, sortBy, sortDir);
        ListUrbanVillageResponseDTO allUrbanVillages = urbanVillageService.getAllUrbanVillages(listUrbanVillageRequestDTO);
        return new ResponseEntity<>(new WebResponseDTO<>(200, "OK", allUrbanVillages), HttpStatus.OK);
    }

    @PutMapping(value = "/{urbanVillageId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<UrbanVillageDTO>> updateUrbanVillage(@PathVariable(name = "urbanVillageId") String id, @RequestBody UpdateUrbanVillageRequestDTO updateUrbanVillageRequestDTO) {
        UrbanVillageDTO urbanVillage = urbanVillageService.updateUrbanVillage(id, updateUrbanVillageRequestDTO);
        return new ResponseEntity<>(new WebResponseDTO<>(201, "CREATED", urbanVillage), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{urbanVillageId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<String>> deleteUrbanVillage(@PathVariable(name = "urbanVillageId") String id) {
        urbanVillageService.deleteUrbanVillage(id);
        return new ResponseEntity<>(new WebResponseDTO<>(200, "OK", "Successfully Deleted"), HttpStatus.OK);
    }

    @GetMapping(value = "/name", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<UrbanVillageDTO>> getUrbanVillageByName(@RequestParam(name = "name") String name) {
        UrbanVillageDTO urbanVillageDTO = urbanVillageService.getUrbanVillageByName(name);
        return new ResponseEntity<>(new WebResponseDTO<>(200, "OK", urbanVillageDTO), HttpStatus.OK);
    }

    @GetMapping(value = "/name/contains", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<List<UrbanVillageDTO>>> getUrbanVillagesByNameContains(@RequestParam(name = "name") String name) {
        List<UrbanVillageDTO> urbanVillageDTOS = urbanVillageService.getUrbanVillageByNameContains(name);
        return new ResponseEntity<>(new WebResponseDTO<>(200, "OK", urbanVillageDTOS), HttpStatus.OK);
    }

    @GetMapping(value = "/code", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<UrbanVillageDTO>> getUrbanVillageByCode(@RequestParam(name = "code") String code) {
        UrbanVillageDTO urbanVillageDTO = urbanVillageService.getUrbanVillageByCode(code);
        return new ResponseEntity<>(new WebResponseDTO<>(200, "OK", urbanVillageDTO), HttpStatus.OK);
    }
}
