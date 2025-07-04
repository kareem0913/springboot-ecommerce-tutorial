package com.ecommerce.mapper;

import com.ecommerce.model.dto.role.RoleCreate;
import com.ecommerce.model.dto.role.RoleResponse;
import com.ecommerce.model.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleResponse toRoleResponse(Role role);

    Role toRole(RoleResponse roleResponse);

    Role toRole(RoleCreate roleCreate);
}
