package com.example.shop.web.boundary;

import com.example.shop.api.user.UserCreationRequest;
import com.example.shop.api.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(value = "/register")
@AllArgsConstructor
public class RegisterController {

    private final UserService userService;

    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@Valid @RequestBody @NotNull UserCreationRequest creationRequest) {
        return userService.create(creationRequest);
    }
}
