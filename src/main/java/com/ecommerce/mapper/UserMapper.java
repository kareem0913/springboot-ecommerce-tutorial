package com.ecommerce.mapper;

import com.ecommerce.model.dto.user.UserCreate;
import com.ecommerce.model.dto.user.UserResponse;
import com.ecommerce.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper {

    UserResponse toUserResponse(User user);

    User toUser(UserResponse userResponse);

    @Mapping(target = "isActive", defaultValue = "true")
    User toUser(UserCreate userCreate);
}
