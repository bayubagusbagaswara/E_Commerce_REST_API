package com.ecommerce.service;

import com.ecommerce.dto.user.CreateUserRequest;
import com.ecommerce.dto.user.UserDTO;

public interface UserService {

    UserDTO createNewUser(CreateUserRequest createUserRequest);

}
