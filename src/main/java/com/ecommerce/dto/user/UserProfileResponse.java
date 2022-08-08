package com.ecommerce.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileResponse {

    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private Instant createdAt;
}
