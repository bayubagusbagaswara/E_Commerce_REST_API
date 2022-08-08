package com.ecommerce.service;

import com.ecommerce.dto.user.CreateUserRequest;
import com.ecommerce.dto.user.UserDTO;

public interface UserService {

    void checkUsernameIsExists(String username);
    void checkEmailIsExists(String email);

    UserDTO createNewUser(CreateUserRequest createUserRequest);

    UserDTO createAdmin(CreateUserRequest createUserRequest);


}
