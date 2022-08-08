package com.ecommerce.service;

import com.ecommerce.dto.auth.AuthenticationResponse;
import com.ecommerce.dto.auth.LoginRequest;
import com.ecommerce.dto.auth.LogoutRequest;
import com.ecommerce.dto.auth.RegisterRequest;
import com.ecommerce.dto.refreshToken.RefreshTokenRequest;
import com.ecommerce.dto.user.UserDTO;

public interface AuthService {

    UserDTO registerNewUser(RegisterRequest registerRequest);

    AuthenticationResponse login(LoginRequest loginRequest);

    AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    void logout(LogoutRequest logoutRequest);
}
