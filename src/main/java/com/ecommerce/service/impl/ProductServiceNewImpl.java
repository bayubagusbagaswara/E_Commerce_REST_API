package com.ecommerce.service.impl;

import com.ecommerce.entity.Category;
import com.ecommerce.entity.Coupon;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.ProductDetail;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.repository.CouponRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.ProductServiceNew;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceNewImpl implements ProductServiceNew {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CouponRepository couponRepository;

    public ProductServiceNewImpl(ProductRepository productRepository, CategoryRepository categoryRepository, CouponRepository couponRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.couponRepository = couponRepository;
    }

    @Override
    public void saveProductToDatabase(MultipartFile file, String name, String description, int quantity, Double price, String brand, String categories) {

        // buat object Product
        Product product = new Product();
        // ambil nama file
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        if (fileName.contains("..")) {
            System.out.println("not a valid file");
        }

        // set image ke product
        try {
            product.setImage(resizeImageForUse(Base64.getEncoder().encodeToString(file.getBytes()), 400, 400));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // create product detail
        ProductDetail productDetail = new ProductDetail();
        productDetail.setDescription(description);

        // create Coupon
        Coupon coupon = new Coupon();
        coupon.setDiscount(0);

        product.setProductDetail(productDetail);
        product.setName(name);
        product.setQuantity(quantity);

        // add categories to product
        product = addCategoriesToProduct(product, categories);

        // save product
        productRepository.save(product);
    }

    @Override
    public Product addCategoriesToProduct(Product product, String categories) {
        String[] cats = categories.split(",");
        Category category = null;
        for (String str : cats) {
            category = categoryRepository.findById(str).get();
//            p.getCategories().add(category);
            product.setCategory(category);
        }
        return product;
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
    public void changeProductQuantity(String productId, int quantity) {

    }

    @Override
    public void saveProductDiscount(String productId, int discount) {
//        Product p = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with " + id + " not found"));
//        if (p.getDiscount() == null) {
//            Coupon c = new Coupon();
//            c.setDiscount(discount);
//            p.setDiscount(c);
//        } else {
//            p.getDiscount().setDiscount(discount);
//        }
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
        return null;
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


    @Override
    public Product getProductWithBiggestDiscount() {
        Coupon discount = couponRepository.findMax();
        List<Product> products = productRepository.findAll();
        Product featuredProduct = null;
        for (Product p : products) {
            if (p.getDiscount().equals(discount)) {
                featuredProduct = p;
                break;
            }
        }
        return featuredProduct;
    }
}
