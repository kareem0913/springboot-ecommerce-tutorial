package com.ecommerce.model.dto.product;

import com.ecommerce.validation.FileConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductCreate {

    @NotBlank(message = "Name cant be empty or null")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    @FileConstraint
    private MultipartFile image;
    private Double price;
    private int stack;

//    @NotNull(message = "Category ID cannot be null")
    private Long categoryId;

}
