package com.ecommerce.util.product;

import com.ecommerce.model.dto.product.ProductResponse;
import com.ecommerce.model.entity.Product;
import jakarta.validation.constraints.NotNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductTransformation {
    public static ProductResponse toProductResponse(@NotNull final Product product) {

        String baseImageUrl = "http://localhost:8080/";
        String image = product.getImage() != null ? product.getImage() : "default.png";
        image = baseImageUrl + image;

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .image(image)
                .price(product.getPrice())
                .stack(product.getStack())
                .categoryId(null)
                .build();
    }
}
