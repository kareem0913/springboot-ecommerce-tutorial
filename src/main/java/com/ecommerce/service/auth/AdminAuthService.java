package com.ecommerce.service.auth;

public interface AdminAuthService {
    /**
     * Authenticates a user with the provided email and password.
     *
     * @param email    the email of the user
     * @param password the password of the user
     * @return a token if authentication is successful
     */
    String login(String email, String password);


    /**
     * Logs out the user by invalidating the session or token.
     *
     * @param token the token of the user to be logged out
     */
    void logout(String token);
}
