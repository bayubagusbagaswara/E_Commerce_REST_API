package com.ecommerce.service;

import com.ecommerce.dto.MessageResponse;
import com.ecommerce.dto.user.CreateUserRequest;
import com.ecommerce.dto.user.UpdateUserRequest;
import com.ecommerce.dto.user.UserDTO;

public interface UserService {

    void checkUsernameIsExists(String username);
    void checkEmailIsExists(String email);

    UserDTO createNewUser(CreateUserRequest createUserRequest);

    UserDTO createAdmin(CreateUserRequest createUserRequest);

    void giveAdmin(String username);
    void removeAdmin(String username);

    void verifyUserByUsernameOrEmail(String usernameOrEmail);

    UserDTO updateUser(String userId, UpdateUserRequest updateUserRequest);

    void deleteUser(String userId);

}
