package com.ecommerce.dto.role;

import com.ecommerce.entity.role.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {

    private String id;

    private String name;

    public static RoleDTO fromRole(Role role) {
        return new RoleDTO(role.getId(), role.getName().name());
    }

    public static Set<RoleDTO> fromEntitySet(Set<Role> roles) {
        return roles.stream()
                .map(RoleDTO::fromRole)
                .collect(Collectors.toSet());
    }
}
