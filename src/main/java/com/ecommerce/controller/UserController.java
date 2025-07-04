package com.ecommerce.controller;

import com.ecommerce.model.dto.user.UserCreate;
import com.ecommerce.model.dto.user.UserResponse;
import com.ecommerce.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public UserResponse userProfileApi(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @PostMapping
    public UserResponse createCategoryApi(@Valid @RequestBody UserCreate userCreate) {
        return userService.createUser(userCreate);
    }

}
