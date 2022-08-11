package com.ecommerce.service.impl;

import com.ecommerce.dto.auth.AuthenticationResponse;
import com.ecommerce.dto.auth.LoginRequest;
import com.ecommerce.dto.auth.LogoutRequest;
import com.ecommerce.dto.auth.RegisterRequest;
import com.ecommerce.dto.refreshToken.RefreshTokenRequest;
import com.ecommerce.dto.user.CreateUserRequest;
import com.ecommerce.dto.user.UserDTO;
import com.ecommerce.jwt.JwtTokenProvider;
import com.ecommerce.service.AuthService;
import com.ecommerce.service.RefreshTokenService;
import com.ecommerce.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

    private static final String USER_ROLE_NOT_SET = "User role not set";

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, RefreshTokenService refreshTokenService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.refreshTokenService = refreshTokenService;
        this.userService = userService;
    }

    @Override
    public UserDTO registerNewUser(RegisterRequest registerRequest) {
        CreateUserRequest createUserRequest = CreateUserRequest.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .username(registerRequest.getUsername())
                .password(registerRequest.getPassword())
                .build();
        return userService.createNewUser(createUserRequest);
    }

    @Override
    public AuthenticationResponse login(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        return null;
    }

    @Override
    public void logout(LogoutRequest logoutRequest) {

    }
}
