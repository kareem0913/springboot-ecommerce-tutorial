package com.ecommerce.service.product;

import com.ecommerce.model.dto.product.ProductCreate;
import com.ecommerce.model.dto.product.ProductResponse;
import com.ecommerce.model.entity.Product;

import java.util.List;

public interface ProductService {
    ProductResponse findProduct(Long id);

    Product findPategoryEntity(Long id);

    List<ProductResponse> findAllProducts();

    ProductResponse createProduct(ProductCreate productCreate);

    ProductResponse updateProduct(Long id, ProductCreate productCreate);

    void deleteProduct(Long id);
}
