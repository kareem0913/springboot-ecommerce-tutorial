package com.ecommerce.controller;

import com.ecommerce.model.dto.GlobalResponse;
import com.ecommerce.model.dto.role.RoleCreate;
import com.ecommerce.model.dto.role.RoleResponse;
import com.ecommerce.service.role.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {
    
    private final RoleService roleService;
    
    @GetMapping
    public Set<RoleResponse> findAllRolesApi() {
        return roleService.findAllRoles();
    }

    @GetMapping("/{id}")
    public RoleResponse findRoleApi(@PathVariable Long id) {
        return roleService.findRoleById(id);
    }

    @PostMapping
    public RoleResponse createRoleApi(@Valid @RequestBody RoleCreate roleCreate) {
        return roleService.createRole(roleCreate);
    }

    @PutMapping("/{id}")
    public RoleResponse updateRoelApi(@PathVariable Long id,
                                              @Valid @RequestBody RoleCreate roleCreate) {
        return roleService.updateRole(id, roleCreate);
    }

    @DeleteMapping("/{id}")
    public GlobalResponse deleteRolaApi(@PathVariable Long id) {
        return roleService.deleteRole(id);
    }
}
