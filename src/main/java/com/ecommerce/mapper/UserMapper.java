package com.ecommerce.mapper;

import com.ecommerce.dto.user.UserDTO;
import com.ecommerce.entity.user.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDTO fromUser(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .username(user.getUsername())
                .roles(user.getRoles())
                .build();
    }

    public List<UserDTO> fromUserList(List<User> userList) {
        return userList.stream()
                .map(this::fromUser)
                .collect(Collectors.toList());
    }
}
