package com.ecommerce.service.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminAuthServiceImpl implements AdminAuthService {

    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    public void logout(String token) {

    }

}
