package com.ecommerce.model.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryCreate {
    @NotBlank(message = "{validation.category.name.not-blank}")
    @Size(min = 2, max = 50, message = "{validation.category.name.size}")
    private String name;
    @Size(max = 500, message = "{validation.category.description.size}")
    private String description;
}
