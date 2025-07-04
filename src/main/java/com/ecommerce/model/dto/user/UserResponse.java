package com.ecommerce.model.dto.user;

import com.ecommerce.model.dto.role.RoleResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private String password;
    private String email;
    private String phoneNumber;
    private String address;
    private boolean isActive;
    private Set<RoleResponse> roles;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
