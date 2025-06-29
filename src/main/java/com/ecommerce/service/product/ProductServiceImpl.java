package com.ecommerce.service.product;

import com.ecommerce.model.dto.product.ProductCreate;
import com.ecommerce.model.dto.product.ProductResponse;
import com.ecommerce.model.entity.Product;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.util.product.ProductTransformation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.ecommerce.util.Util.saveFile;
import static com.ecommerce.util.product.ProductTransformation.toProductResponse;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final Path uploadDir;

    public ProductServiceImpl(ProductRepository productRepository, @Value("${file.upload-dir:uploads}") String dir) {
        this.productRepository = productRepository;
        this.uploadDir = Paths.get(dir).toAbsolutePath().normalize();
    }

    @Override
    public ProductResponse findProduct(Long id) {
        return null;
    }

    @Override
    public Product findPategoryEntity(Long id) {
        return null;
    }

    @Override
    public List<ProductResponse> findAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductTransformation::toProductResponse)
                .toList();
    }

    @Override
    public ProductResponse createProduct(ProductCreate productCreate) {
        MultipartFile img = productCreate.getImage();
        String stored = saveFile(img, uploadDir);
        Product product = Product.builder()
                .name("Laptop")
                .description("Gaming laptop")
                .image(stored)
                .price(999.99)
                .stack(10)
                .category(null)
                .build();
        Product createdProduct = productRepository.save(product);
        return toProductResponse(createdProduct);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductCreate productCreate) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
