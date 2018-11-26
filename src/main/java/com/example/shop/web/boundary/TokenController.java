package com.example.shop.web.boundary;

import com.example.shop.api.oauth.AccessTokenInfo;
import com.example.shop.api.oauth.AccessTokenRequest;
import com.example.shop.api.oauth.RefreshTokenRequest;
import com.example.shop.service.oauth.OAuthTokenService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(value = "/tokens")
@AllArgsConstructor
public class TokenController {

    private final OAuthTokenService oAuthTokenService;

    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public AccessTokenInfo getTokenInfo(@Valid @RequestBody AccessTokenRequest accessTokenRequest) {
        return oAuthTokenService.getTokenInfo(accessTokenRequest);
    }

    @PostMapping(value = "/refreshes", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public AccessTokenInfo refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return oAuthTokenService.refreshToken(refreshTokenRequest);
    }
}
