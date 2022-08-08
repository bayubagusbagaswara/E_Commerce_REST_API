package com.ecommerce.dto.refreshToken;

import com.ecommerce.entity.RefreshToken;
import com.ecommerce.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenResponse {

    private String id;

    private User user;

    private String refreshToken;

    private Instant expiryDate;

    public static RefreshTokenResponse mapToDto(RefreshToken refreshToken) {
        return new RefreshTokenResponse(refreshToken.getId(), refreshToken.getUser(), refreshToken.getRefreshToken(), refreshToken.getExpiredDate());
    }

}
