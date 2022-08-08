package com.ecommerce.service;

import com.ecommerce.dto.auth.RegisterRequest;
import com.ecommerce.dto.user.UserDTO;

public interface AuthService {

    UserDTO registerNewUser(RegisterRequest registerRequest);


    // login

    // logout
}
