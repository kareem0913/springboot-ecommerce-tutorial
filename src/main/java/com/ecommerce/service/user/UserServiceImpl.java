package com.ecommerce.service.user;

import com.ecommerce.error.exception.DuplicateResourceException;
import com.ecommerce.error.exception.ResourceNotFoundException;
import com.ecommerce.mapper.UserMapper;
import com.ecommerce.model.dto.user.UserCreate;
import com.ecommerce.model.dto.user.UserResponse;
import com.ecommerce.model.entity.User;
import com.ecommerce.model.entity.Role;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.repository.RoleRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            return new ResourceNotFoundException("User not found",
                    "there is no user found with this: " + id);
        });
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse createUser(@NotNull final UserCreate userCreate) {
        if (existsByEmail(userCreate.getEmail())) {
            throw new DuplicateResourceException("User already exists",
                    "An user with this email already exists: " + userCreate.getEmail());
        }

        User user =  userMapper.toUser(userCreate);

        Set<Role> roles = new HashSet<>(roleRepository.findAllById(userCreate.getRoleIds()));
        user.setRoles(roles);
        userRepository.save(user);

        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse updateUserProfile(final Long id, @NotNull final UserCreate userCreate) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {}

    private boolean existsByEmail(@NotNull final String email) {
        return userRepository.existsByEmail(email);
    }

}
