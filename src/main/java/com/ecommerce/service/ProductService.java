package com.ecommerce.service;

import com.ecommerce.dto.product.*;
import com.ecommerce.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.util.List;

public interface ProductService {

    ProductDTO createProduct(CreateProductRequestDTO createProductRequest);

    ProductDTO updateProduct(String productId, UpdateProductRequestDTO updateProductRequest);

    void deleteProduct(String productId);

    ProductDTO getProductById(String productId);

    ListProductResponseDTO getAllProducts(ListProductRequestDTO listProductRequest);

    ProductDTO getProductByName(String name);

    List<ProductDTO> getProductByContainingName(String name);

    ProductDTO getProductBySku(String sku);

    List<ProductDTO> getProductByCategoryId(String categoryId);

    List<ProductDTO> getProductBySuppliersId(String supplierId);

    Product getProductId(String productId);

    //    Product getProductWithBiggestDiscount();

    void addImageToProduct(MultipartFile file, String productId);

    String resizeImageForUse(String src, int width, int height);
    BufferedImage resizeImage(BufferedImage image, int width, int height);
    BufferedImage base64ToBufferedImage(String base64Img);
    String bufferedImageToBase64(BufferedImage image);
}
