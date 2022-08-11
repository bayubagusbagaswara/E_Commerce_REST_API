package com.ecommerce.service.impl;

import com.ecommerce.dto.MessageResponse;
import com.ecommerce.dto.user.CreateUserRequest;
import com.ecommerce.dto.user.UpdateUserRequest;
import com.ecommerce.dto.user.UserDTO;
import com.ecommerce.entity.enumerator.RoleName;
import com.ecommerce.entity.role.Role;
import com.ecommerce.entity.user.User;
import com.ecommerce.exception.AppException;
import com.ecommerce.exception.BadRequestException;
import com.ecommerce.repository.RoleRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
        if (userRepository.existsByEmail(email)) {
            throw new BadRequestException(new MessageResponse("Email is already taken"));
        }
    }

    @Override
    public UserDTO createNewUser(CreateUserRequest userRequest) {
        checkUsernameIsExists(userRequest.getUsername());
        checkEmailIsExists(userRequest.getEmail());

        User user = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .build();

        Set<Role> roleSet = new HashSet<>();

        if (userRepository.count() == 0) {
            roleSet.add(roleRepository.getByName(RoleName.ADMIN.name())
                    .orElseThrow(() -> new AppException(USER_ROLE_NOT_SET)));
            roleSet.add(roleRepository.getByName(RoleName.USER.name())
                    .orElseThrow(() -> new AppException(USER_ROLE_NOT_SET)));
        }

        roleSet.add(roleRepository.getByName(RoleName.USER.name())
                .orElseThrow(() -> new AppException(USER_ROLE_NOT_SET)));

        user.setRoles(roleSet);
        userRepository.save(user);

        return UserDTO.fromUser(user);
    }

    @Override
    public UserDTO createAdmin(CreateUserRequest userRequest) {
        checkUsernameIsExists(userRequest.getUsername());
        checkEmailIsExists(userRequest.getEmail());

        User user = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .build();

        user.setRoles(new HashSet<>(Collections.singleton(
                roleRepository.getByName(RoleName.ADMIN.name())
                        .orElseThrow(() -> new AppException(USER_ROLE_NOT_SET)))));

        userRepository.save(user);
        return UserDTO.fromUser(user);
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
