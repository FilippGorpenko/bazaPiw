package com.example.shop.api.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@JsonIgnoreProperties(ignoreUnknown = true)
@Value
@AllArgsConstructor
@Builder
public class PasswordChangeRequest {

    private String username;

    private String oldPassword;

    private String newPassword;

}
