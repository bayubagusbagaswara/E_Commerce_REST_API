package com.ecommerce.service.impl;

import com.ecommerce.dto.MessageResponse;
import com.ecommerce.dto.user.CreateUserRequest;
import com.ecommerce.dto.user.UpdateUserRequest;
import com.ecommerce.dto.user.UserDTO;
import com.ecommerce.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public void checkUsernameIsExists(String username) {

    }

    @Override
    public void checkEmailIsExists(String email) {

    }

    @Override
    public UserDTO createNewUser(CreateUserRequest createUserRequest) {
        return null;
    }

    @Override
    public UserDTO createAdmin(CreateUserRequest createUserRequest) {
        return null;
    }

    @Override
    public MessageResponse giveAdmin(String username) {
        return null;
    }

    @Override
    public MessageResponse removeAdmin(String username) {
        return null;
    }

    @Override
    public void verifyUserByUsernameOrEmail(String usernameOrEmail) {

    }

    @Override
    public UserDTO updateUser(String userId, UpdateUserRequest updateUserRequest) {
        return null;
    }

    @Override
    public MessageResponse deleteUser(String userId) {
        return null;
    }
}
