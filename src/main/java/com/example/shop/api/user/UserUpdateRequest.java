package com.example.shop.api.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@JsonIgnoreProperties(ignoreUnknown = true)
@Value
@Builder
@AllArgsConstructor
public class UserUpdateRequest {

    private String username;

    private String password;

}
