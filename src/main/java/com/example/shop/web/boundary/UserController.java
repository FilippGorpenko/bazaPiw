package com.example.shop.web.boundary;

import com.example.shop.api.Joke;
import com.example.shop.api.user.UserCreationRequest;
import com.example.shop.api.user.UserService;
import com.example.shop.api.user.UserSnapshot;
import com.example.shop.api.user.UserUpdateRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(value = "/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@Valid @RequestBody @NotNull UserCreationRequest creationRequest) {
        return userService.create(creationRequest);
    }

    @GetMapping(value = "/me")
    @ResponseStatus(HttpStatus.OK)
    public UserSnapshot getMe(@AuthenticationPrincipal User user) {
        return userService.getById(Long.parseLong(user.getUsername()));
    }

    @GetMapping(value = "/joke")
    @ResponseStatus(HttpStatus.OK)
    public Joke create() {
        return new Joke("my joke");
    }

    @GetMapping(value = "/{userId}", produces = APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UserSnapshot getById(@PathVariable("userId") Long userId) {
        return userService.getById(userId);
    }

    @PutMapping(value = "/{userId}/{version}", consumes = APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("userId") Long userId, @PathVariable("version") Long version,
                       @Valid @RequestBody @NotNull UserUpdateRequest updateRequest) {
        userService.update(userId, version, updateRequest);
    }

}
