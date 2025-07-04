package com.ecommerce.service.role;

import com.ecommerce.error.exception.DuplicateResourceException;
import com.ecommerce.error.exception.ResourceNotFoundException;
import com.ecommerce.mapper.RoleMapper;
import com.ecommerce.model.dto.GlobalResponse;
import com.ecommerce.model.dto.role.RoleCreate;
import com.ecommerce.model.dto.role.RoleResponse;
import com.ecommerce.model.entity.Role;
import com.ecommerce.repository.RoleRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public RoleResponse findRoleById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> {
            return new ResourceNotFoundException("Role not found",
                    "thre is not role found with this: " + id);
        });
        return roleMapper.toRoleResponse(role);
    }

    @Override
    public Set<RoleResponse> findAllRoles() {
    return roleRepository.findAll()
            .stream()
            .map(roleMapper::toRoleResponse)
            .collect(Collectors.toSet());
    }

    @Override
    public RoleResponse createRole(@NotNull final  RoleCreate roleCreate) {
        if (existsByName(roleCreate.getName())) {
            throw new DuplicateResourceException("Role already exists",
                    "A role with the name '%s' already exists.".formatted(roleCreate.getName()));
        }

        Role role =  roleRepository.save(roleMapper.toRole(roleCreate));
        return roleMapper.toRoleResponse(role);
    }

    @Override
    public RoleResponse updateRole(Long id, @NotNull final RoleCreate roleCreate) {
        Role role = roleRepository.findById(id).orElseThrow(() -> {
            return new ResourceNotFoundException("role not found",
                    "No Role with Found with this : " + id);
        });

        if (existsByName(roleCreate.getName()) && !role.getName().equals(roleCreate.getName())) {
            throw new DuplicateResourceException("Role already exists",
                    "A role with the name '%s' already exists.".formatted(roleCreate.getName()));
        }

        role.setName(roleCreate.getName());
        role.setDescription(roleCreate.getDescription());

        Role updatedRole = roleRepository.save(role);
        return roleMapper.toRoleResponse(updatedRole);
    }

    @Override
    public GlobalResponse deleteRole(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> {
            return new ResourceNotFoundException("Role not found",
                    "No role found with the provided ID: " + id);
        });
        roleRepository.delete(role);
        return new GlobalResponse(200, "Role deleted successfully");
    }

    private boolean existsByName(@NotNull final String name) {
        return roleRepository.existsByName(name);
    }
}
