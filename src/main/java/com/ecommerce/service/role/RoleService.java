package com.ecommerce.service.role;

import com.ecommerce.model.dto.GlobalResponse;
import com.ecommerce.model.dto.role.RoleCreate;
import com.ecommerce.model.dto.role.RoleResponse;

import java.util.Set;

public interface RoleService {

    RoleResponse findRoleById(Long id);

    Set<RoleResponse> findAllRoles();

    RoleResponse createRole(RoleCreate roleCreate);

    RoleResponse updateRole(Long id, RoleCreate roleCreate);

    GlobalResponse deleteRole(Long id);
}
