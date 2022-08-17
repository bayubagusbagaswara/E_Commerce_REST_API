package com.ecommerce.service.impl;

import com.ecommerce.mapper.ProductMapper;
import com.ecommerce.dto.product.*;
import com.ecommerce.entity.Category;
import com.ecommerce.entity.product.Product;
import com.ecommerce.entity.product.ProductDetail;
import com.ecommerce.exception.CategoryNotFoundException;
import com.ecommerce.exception.ProductNotFoundException;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDTO createProduct(CreateProductRequestDTO createProductRequest) {
        Category category = categoryRepository.findById(createProductRequest.getCategoryId()).orElseThrow(() -> new CategoryNotFoundException("id", createProductRequest.getCategoryId()));
        Product product = Product.builder()
                .name(createProductRequest.getName())
                .price(createProductRequest.getPrice())
                .quantity(createProductRequest.getQuantity())
                .productDetail(new ProductDetail(createProductRequest.getSku(), createProductRequest.getDescription()))
                .category(category)
                .build();
        productRepository.save(product);
        return productMapper.fromProduct(product);
    }

    @Override
    public ProductDTO getProductById(String productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("id", productId));
        return productMapper.fromProduct(product);
    }

    @Override
    public ListProductResponseDTO getAllProducts(ListProductRequestDTO listProductRequest) {
        Integer pageNo = listProductRequest.getPageNo();
        Integer pageSize = listProductRequest.getPageSize();
        String sortBy = listProductRequest.getSortBy();
        String sortDir = listProductRequest.getSortDir();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Product> productPage = productRepository.findAll(pageable);
        List<Product> productList = productPage.getContent();
        List<ProductDTO> productDTOS = productMapper.fromProductList(productList);

        return ListProductResponseDTO.builder()
                .productDTOList(productDTOS)
                .pageNo(productPage.getNumber())
                .pageSize(productPage.getSize())
                .totalElements(productPage.getTotalElements())
                .totalPages(productPage.getTotalPages())
                .last(productPage.isLast())
                .build();
    }

    @Override
    public ProductDTO updateProduct(String productId, UpdateProductRequestDTO updateProductRequest) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("id", productId));
        Category category = categoryRepository.findById(updateProductRequest.getCategoryId()).orElseThrow(() -> new CategoryNotFoundException("id", updateProductRequest.getCategoryId()));

        ProductDetail productDetail = product.getProductDetail();
        productDetail.setSku(updateProductRequest.getSku());
        productDetail.setDescription(updateProductRequest.getDescription());

        product.setName(updateProductRequest.getName());
        product.setPrice(updateProductRequest.getPrice());
        product.setQuantity(updateProductRequest.getQuantity());
        product.setProductDetail(productDetail);
        product.setCategory(category);
        product.setUpdatedAt(Instant.now());

        productRepository.save(product);
        return productMapper.fromProduct(product);
    }

    @Override
    public void deleteProduct(String productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException("id", productId);
        }
        productOptional.get().removeSuppliers();
        productRepository.deleteById(productOptional.get().getId());
    }

    @Override
    public ProductDTO getProductByName(String name) {
        Product product = productRepository.findProductByNameIgnoreCase(name).orElseThrow(() -> new ProductNotFoundException("name", name));
        return productMapper.fromProduct(product);
    }

    @Override
    public List<ProductDTO> getProductByContainingName(String name) {
        List<Product> productList = productRepository.findAllByNameContainingIgnoreCase(name);
        return productMapper.fromProductList(productList);
    }

    @Override
    public ProductDTO getProductBySku(String sku) {
        Product product = productRepository.findAllByProductDetailSku(sku).orElseThrow(() -> new ProductNotFoundException("SKU", sku));
        return productMapper.fromProduct(product);
    }

    @Override
    public List<ProductDTO> getProductByCategoryId(String categoryId) {
        List<Product> productList = productRepository.findAllByCategoryId(categoryId);
        return productMapper.fromProductList(productList);
    }

    @Override
    public List<ProductDTO> getProductBySuppliersId(String supplierId) {
        List<Product> productList = productRepository.findAllBySuppliersId(supplierId);
        return productMapper.fromProductList(productList);
    }

    @Override
    public Product getProductId(String productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("id", productId));
    }

    @Override
    public void addImageToProduct(MultipartFile file, String productId) {
        //        Product p = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with " + id + " not found"));
//        Carousel carousel = new Carousel();
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        if (fileName.contains("..")) {
//            System.out.println("not a valid file");
//        }
//        try {
//            carousel.setImage(resizeImageForUse(Base64.getEncoder().encodeToString(file.getBytes()), 400, 400));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        p.getCarousel().add(carousel);
//        productRepository.save(p);
    }

    @Override
    public String resizeImageForUse(String src, int width, int height) {
        BufferedImage image = base64ToBufferedImage(src);
        image = resizeImage(image, width, height);
        return bufferedImageToBase64(image);
    }

    @Override
    public BufferedImage resizeImage(BufferedImage image, int width, int height) {
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        try {
//            Thumbnails.of(image).size(width, height).outputFormat("JPEG").outputQuality(1).toOutputStream(outputStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        byte[] data = outputStream.toByteArray();
//        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
//        try {
//            return ImageIO.read(inputStream);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        return null;
    }

    @Override
    public BufferedImage base64ToBufferedImage(String base64Img) {
        BufferedImage image = null;
        byte[] decodedBytes = Base64.getDecoder().decode(base64Img);

        try {
            image = ImageIO.read(new ByteArrayInputStream(decodedBytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    public String bufferedImageToBase64(BufferedImage image) {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "JPEG", Base64.getEncoder().wrap(out));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString(StandardCharsets.ISO_8859_1);
    }

//    Coupon discount = couponRepository.findMax();
//    List<Product> products = productRepository.findAll();
//    Product featuredProduct = null;
//        for (Product p : products) {
//        if (p.getDiscount().equals(discount)) {
//            featuredProduct = p;
//            break;
//        }
//    }
//        return featuredProduct;


//    public void saveProductToDatabase(MultipartFile file, String name, String description, int quantity, Double price, String brand, String categories) {
//
//        // buat object Product
//        Product product = new Product();
//        // ambil nama file
//        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
//        if (fileName.contains("..")) {
//            System.out.println("not a valid file");
//        }
//
//        // set image ke product
//        try {
//            product.setImage(resizeImageForUse(Base64.getEncoder().encodeToString(file.getBytes()), 400, 400));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // create product detail
//        ProductDetail productDetail = new ProductDetail();
//        productDetail.setDescription(description);
//
//        // create Coupon
//        Coupon coupon = new Coupon();
//        coupon.setDiscount(0);
//
//        product.setProductDetail(productDetail);
//        product.setName(name);
//        product.setQuantity(quantity);
//
//        // add categories to product
//        product = addCategoriesToProduct(product, categories);
//
//        // save product
//        productRepository.save(product);
}
