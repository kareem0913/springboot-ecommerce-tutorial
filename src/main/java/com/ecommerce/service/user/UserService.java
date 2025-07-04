package com.ecommerce.service.user;

import com.ecommerce.model.dto.user.UserCreate;
import com.ecommerce.model.dto.user.UserResponse;

public interface UserService {

    UserResponse findUserById(Long id);

    UserResponse createUser(UserCreate userCreate);

    UserResponse updateUserProfile(Long id, UserCreate userCreate);

    void deleteUser(Long id);
}
