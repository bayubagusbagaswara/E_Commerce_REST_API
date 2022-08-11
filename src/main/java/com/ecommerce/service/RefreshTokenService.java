package com.ecommerce.service;

import com.ecommerce.dto.refreshToken.RefreshTokenResponse;
import com.ecommerce.entity.RefreshToken;

public interface RefreshTokenService {

    RefreshTokenResponse generateRefreshToken(String userId);

    RefreshTokenResponse validateRefreshToken(String refreshToken);

    RefreshToken getRefreshToken(String refreshToken);

    RefreshToken verifyExpirationRefreshToken(String refreshToken);

    void deleteRefreshToken(String token);
}
