package com.example.shop.service.oauth;

import com.example.shop.api.exception.InvalidCredentialsException;
import com.example.shop.api.oauth.AccessTokenInfo;
import com.example.shop.api.oauth.AccessTokenRequest;
import com.example.shop.api.oauth.RefreshTokenRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.http.*;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

import static java.lang.String.format;
import static java.util.Collections.singletonList;

@Slf4j
@Service
@AllArgsConstructor
public class RestOAuthTokenService implements OAuthTokenService {

    private final RestTemplate restTemplate;
    private final ResourceServerProperties resourceServerProperties;
    private final AuthorizationServerEndpointsConfiguration configuration;


    @Override
    public AccessTokenInfo getTokenInfo(@Valid @NotNull AccessTokenRequest accessTokenRequest) {
        log.info("Getting access token: {username: {}}", accessTokenRequest.getUsername());

        MultiValueMap<String, String> vars = buildLoginMap(accessTokenRequest);
        HttpHeaders headers = buildHeaders();
        ResponseEntity<Map> responseEntity = restTemplate.exchange(resourceServerProperties.getTokenInfoUri(), HttpMethod.POST, new HttpEntity<>(vars, headers), Map.class);
        AccessTokenInfo accessTokenInfo = handleAccessTokenResponse(responseEntity, accessTokenRequest.getUsername());
        return accessTokenInfo;
    }

    @Override
    public AccessTokenInfo refreshToken(RefreshTokenRequest refreshTokenRequest) {
        log.info("Refreshing token {request: {}}", refreshTokenRequest);
        MultiValueMap<String, String> vars = buildRefreshMap(refreshTokenRequest);
        HttpHeaders headers = buildHeaders();
        ResponseEntity<Map> responseEntity = restTemplate.exchange(resourceServerProperties.getTokenInfoUri(), HttpMethod.POST, new HttpEntity<>(vars, headers), Map.class);
        return handleRefreshResponse(responseEntity);
    }

    private MultiValueMap<String, String> buildLoginMap(AccessTokenRequest tokenInfoRequest) {
        MultiValueMap<String, String> vars = new LinkedMultiValueMap<>();
        vars.put("grant_type", singletonList("password"));
        vars.put("username", singletonList(tokenInfoRequest.getUsername()));
        vars.put("password", singletonList(tokenInfoRequest.getPassword()));
        return vars;
    }


    private MultiValueMap<String, String> buildRefreshMap(RefreshTokenRequest refreshTokenRequest) {
        MultiValueMap<String, String> vars = new LinkedMultiValueMap<>();
        vars.add("refresh_token", refreshTokenRequest.getRefreshToken());
        vars.add("grant_type", "refresh_token");
        return vars;
    }

    private HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return headers;
    }

    private AccessTokenInfo handleAccessTokenResponse(ResponseEntity<Map> responseEntity, String username) {
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            log.info("Got access token: {username: {}}}", username);
            return extractAccessToken(responseEntity.getBody(), username);
        } else {
            log.warn("Unable to log user {response: {}}", responseEntity.getBody());
            throw new InvalidCredentialsException(format("Unable to log user in: {username: %s}", username));
        }
    }

    private AccessTokenInfo handleRefreshResponse(ResponseEntity<Map> responseEntity) {
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            log.info("Successful token refresh");
            return extractAccessToken(responseEntity.getBody(), null);
        } else {
            log.warn("Unable to refresh token {response: {}}", responseEntity.getBody());
            throw new InvalidCredentialsException("Unable to refresh token");
        }
    }

    private AccessTokenInfo extractAccessToken(Map body, String username) {
        return AccessTokenInfo.builder()
                .username(username)
                .accessToken((String) body.get("access_token"))
                .refreshToken((String) body.get("refresh_token"))
                .tokenType((String) body.get("token_type"))
                .expiresIn((Integer) body.get("expires_in"))
                .build();
    }

}
