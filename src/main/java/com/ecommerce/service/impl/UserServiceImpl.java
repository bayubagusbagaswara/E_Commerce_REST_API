package com.ecommerce.service.impl;

import com.ecommerce.dto.MessageResponse;
import com.ecommerce.dto.user.CreateUserRequest;
import com.ecommerce.dto.user.UpdateUserRequest;
import com.ecommerce.dto.user.UserDTO;
import com.ecommerce.exception.BadRequestException;
import com.ecommerce.repository.RoleRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final String USER_ROLE_NOT_SET = "User role not set";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void checkUsernameIsExists(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new BadRequestException(new MessageResponse("Username is already taken"));
        }
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
