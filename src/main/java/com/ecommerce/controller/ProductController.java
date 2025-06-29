package com.ecommerce.controller;

import com.ecommerce.model.dto.product.ProductCreate;
import com.ecommerce.model.dto.product.ProductResponse;
import com.ecommerce.service.product.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.findAllProducts();
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ProductResponse createProduct(@Valid @ModelAttribute ProductCreate productCreate) {
        return productService.createProduct(productCreate);
    }
}
