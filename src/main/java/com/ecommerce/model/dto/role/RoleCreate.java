package com.ecommerce.model.dto.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RoleCreate {
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "name must be between 2 and 50 characters")
    private String name;
    @Size(max = 500, message = "description must be less than 500 characters")
    private String description;
}
