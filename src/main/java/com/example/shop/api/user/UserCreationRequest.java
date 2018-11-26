package com.example.shop.api.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
@Value
@Builder
@AllArgsConstructor
public class UserCreationRequest {

    @NotNull
    private String username;

    @NotNull
    private String password;

}
