package com.ecommerce.service.impl;

import com.ecommerce.dto.MessageResponse;
import com.ecommerce.dto.user.CreateUserRequest;
import com.ecommerce.dto.user.UpdateUserRequest;
import com.ecommerce.dto.user.UserDTO;
import com.ecommerce.entity.enumerator.RoleName;
import com.ecommerce.entity.role.Role;
import com.ecommerce.entity.user.User;
import com.ecommerce.entity.user.UserPassword;
import com.ecommerce.exception.AppException;
import com.ecommerce.exception.BadRequestException;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.mapper.UserMapper;
import com.ecommerce.repository.RoleRepository;
import com.ecommerce.repository.UserPasswordRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private static final String USER_ROLE_NOT_SET = "User role not set";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserPasswordRepository userPasswordRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserPasswordRepository userPasswordRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userPasswordRepository = userPasswordRepository;
        this.userMapper = userMapper;
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

        UserPassword userPassword = new UserPassword();
        userPassword.setUser(user);
        userPassword.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        userRepository.save(user);
        userPasswordRepository.save(userPassword);

        return userMapper.fromUser(user);
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
                .build();

        UserPassword userPassword = new UserPassword();
        userPassword.setUser(user);
        userPassword.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        user.setRoles(new HashSet<>(Collections.singleton(
                roleRepository.getByName(RoleName.ADMIN.name())
                        .orElseThrow(() -> new AppException(USER_ROLE_NOT_SET)))));

        userRepository.save(user);
        return userMapper.fromUser(user);
    }

    @Override
    public void giveAdmin(String username) {
        User user = userRepository.getUserByName(username);
        user.addRole(roleRepository.getByName(RoleName.ADMIN.name()).orElseThrow(() -> new AppException(USER_ROLE_NOT_SET)));
        userRepository.save(user);
    }

    @Override
    public void removeAdmin(String username) {
        User user = userRepository.getUserByName(username);
        user.removeRole(roleRepository.getByName(RoleName.ADMIN.name()).orElseThrow(() -> new AppException(USER_ROLE_NOT_SET)));
        userRepository.save(user);
    }

    @Override
    public void verifyUserByUsernameOrEmail(String usernameOrEmail) {
        userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with this username or email: %s", usernameOrEmail)));
    }

    @Override
    public UserDTO updateUser(String userId, UpdateUserRequest updateUserRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        user.setFirstName(updateUserRequest.getFirstName());
        user.setLastName(updateUserRequest.getLastName());
        user.setUsername(updateUserRequest.getUsername());
        user.setEmail(updateUserRequest.getEmail());
        userRepository.save(user);
        return userMapper.fromUser(user);
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        userRepository.deleteById(user.getId());
    }
}
