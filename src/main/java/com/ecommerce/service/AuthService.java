package com.ecommerce.service;

import com.ecommerce.dto.auth.AuthenticationResponse;
import com.ecommerce.dto.auth.LoginRequest;
import com.ecommerce.dto.auth.RegisterRequest;
import com.ecommerce.dto.user.UserDTO;

public interface AuthService {

    UserDTO registerNewUser(RegisterRequest registerRequest);

    // login
    AuthenticationResponse login(LoginRequest loginRequest);

    // logout
}
