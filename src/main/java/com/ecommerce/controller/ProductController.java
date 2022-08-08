package com.ecommerce.controller;

import com.ecommerce.dto.WebResponseDTO;
import com.ecommerce.dto.product.*;
import com.ecommerce.service.ProductService;
import com.ecommerce.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<ProductDTO>> createProduct(@RequestBody CreateProductRequestDTO createProductRequest) {
        ProductDTO product = productService.createProduct(createProductRequest);
        return new ResponseEntity<>(new WebResponseDTO<>(201, "CREATED", product), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<ProductDTO>> getProductById(@PathVariable(name = "productId") String id) {
        ProductDTO productDTO = productService.getProductById(id);
        return new ResponseEntity<>(new WebResponseDTO<>(200, "OK", productDTO), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<ListProductResponseDTO>> getAllProducts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {

        ListProductRequestDTO listProductRequestDTO = new ListProductRequestDTO(pageNo, pageSize, sortBy, sortDir);
        ListProductResponseDTO allProducts = productService.getAllProducts(listProductRequestDTO);
        return new ResponseEntity<>(new WebResponseDTO<>(200, "OK", allProducts), HttpStatus.OK);
    }

    @PutMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<ProductDTO>> updateProduct(@PathVariable(name = "productId") String id, @RequestBody UpdateProductRequestDTO updateProductRequest) {
        ProductDTO productDTO = productService.updateProduct(id, updateProductRequest);
        return new ResponseEntity<>(new WebResponseDTO<>(201, "CREATED", productDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<String>> deleteProduct(@PathVariable(name = "productId") String id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(new WebResponseDTO<>(200, "OK", "Successfully deleted."), HttpStatus.OK);
    }

    @GetMapping(value = "/name", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<ProductDTO>> getProductByName(@RequestParam(name = "name") String name) {
        ProductDTO productDTO = productService.getProductByName(name);
        return new ResponseEntity<>(new WebResponseDTO<>(200, "OK", productDTO), HttpStatus.OK);
    }

    @GetMapping(value = "/name/contains", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<List<ProductDTO>>> getAllProductsByNameContaining(@RequestParam(name = "name") String name) {
        List<ProductDTO> productDTOList = productService.getProductByContainingName(name);
        return new ResponseEntity<>(new WebResponseDTO<>(200, "OK", productDTOList), HttpStatus.OK);
    }

    @GetMapping(value = "/sku", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<ProductDTO>> getProductBySku(@RequestParam(name = "sku") String sku) {
        ProductDTO productDTO = productService.getProductBySku(sku);
        return new ResponseEntity<>(new WebResponseDTO<>(200, "OK", productDTO), HttpStatus.OK);
    }

    @GetMapping(value = "/category/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<List<ProductDTO>>> getAllProductsByCategoryId(@PathVariable(name = "categoryId") String categoryId) {
        List<ProductDTO> productDTOList = productService.getProductByCategoryId(categoryId);
        return new ResponseEntity<>(new WebResponseDTO<>(200, "OK", productDTOList), HttpStatus.OK);
    }

    @GetMapping(value = "/supplier/{supplierId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponseDTO<List<ProductDTO>>> getAllProductsBySupplierId(@PathVariable(name = "supplierId") String supplierId) {
        List<ProductDTO> productDTOList = productService.getProductBySuppliersId(supplierId);
        return new ResponseEntity<>(new WebResponseDTO<>(200, "OK", productDTOList), HttpStatus.OK);
    }
}
