package com.ecommerce.dto.user;

import com.ecommerce.entity.role.Role;
import com.ecommerce.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private Set<Role> roles;

    public static UserDTO fromUser(User user) {
        return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getUsername(), user.getRoles());
    }

    public static List<UserDTO> fromUserList(List<User> userList) {
        return userList.stream()
                .map(UserDTO::fromUser)
                .collect(Collectors.toList());
    }

}
