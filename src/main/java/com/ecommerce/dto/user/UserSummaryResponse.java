package com.ecommerce.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSummaryResponse {

    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String username;

}
