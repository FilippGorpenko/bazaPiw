package com.example.shop.api.user;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface UserService {

    Long create(@Valid @NotNull UserCreationRequest creationRequest);

    void update(Long userId, Long version, @Valid @NotNull UserUpdateRequest updateRequest);

    void resetPassword(@Valid @NotNull PasswordChangeRequest request);

    UserSnapshot getById(Long userId);
}
