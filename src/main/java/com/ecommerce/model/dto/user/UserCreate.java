package com.ecommerce.model.dto.user;

import com.ecommerce.validation.PhoneNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Set;

@Data
public class UserCreate {

    @NotBlank(message = "Name is required")
    private String name;
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-={}\\[\\]:\";'<>?,./]).{8,}$",
            message = "Password must contain at least 8 characters, including upper/lowercase letters, numbers, and special characters"
    )
    private String password;
    @Email
    private String email;
    @PhoneNumber
    private String phoneNumber;
    private String address;
    private Boolean isActive;
    private Set<Long> roleIds;
}
