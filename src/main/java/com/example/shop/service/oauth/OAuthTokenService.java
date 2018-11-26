package com.example.shop.service.oauth;

import com.example.shop.api.oauth.AccessTokenInfo;
import com.example.shop.api.oauth.AccessTokenRequest;
import com.example.shop.api.oauth.RefreshTokenRequest;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface OAuthTokenService {

    AccessTokenInfo getTokenInfo(@Valid @NotNull AccessTokenRequest accessTokenRequest);

    AccessTokenInfo refreshToken(@Valid @NotNull RefreshTokenRequest refreshTokenRequest);

}
