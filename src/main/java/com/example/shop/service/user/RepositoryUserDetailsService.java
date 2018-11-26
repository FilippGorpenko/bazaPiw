package com.example.shop.service.user;

import com.example.shop.api.exception.UserNotFoundException;
import com.example.shop.api.exception.UserUpdateException;
import com.example.shop.api.user.*;
import com.example.shop.mapping.User;
import com.example.shop.repository.DepositRepository;
import com.example.shop.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static java.lang.String.format;
import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class RepositoryUserDetailsService implements UserDetailsService, UserService {

    private final UserFactory userFactory;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final DepositRepository depositRepository;

    @Override
    public Long create(@Valid @NotNull UserCreationRequest creationRequest) {
        log.trace("Creating user {request: {}}", creationRequest);
        User user = userFactory.userFromCreationRequest(creationRequest);
        user = userRepository.save(user);
        log.info("Created user {user: {}}", user);
        return user.getId();
    }

    @Override
    public void update(Long userId, Long version, @Valid @NotNull UserUpdateRequest updateRequest) {
        log.trace("Updating user {request: {}, userId: {}, version: {}}", updateRequest, userId, version);
        User user = findInRepository(userId);
        mergeUpdateRequestIntoUser(updateRequest, user, version);
        log.info("User updated {user: {}}", user);
    }

    @Override
    public void resetPassword(@Valid @NotNull PasswordChangeRequest request) {

    }

    @Override
    public UserSnapshot getById(Long userId) {
        log.trace("Getting user {userId: {}}", userId);
        User user = findInRepository(userId);
        log.trace("Got user {userId: {}}", user.getId());
        return userFactory.userSnapshotFromUser(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameIgnoreCaseAndIsDeletedFalse(username);

        return ofNullable(user)
                .map(this::mapMemberToAuthorizedUser)
                .orElseThrow(() -> new UsernameNotFoundException(format("Can't get member by username {username: {%s}}", username)));
    }

    private User findInRepository(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(format("User %d not found", userId)));
    }

    private void mergeUpdateRequestIntoUser(UserUpdateRequest request, User user, Long version) {
        if (!version.equals(user.getVersion()))
            throw new UserUpdateException(format("Unable to update user, version mismatch {repository version: {%d}, requested version: {%d}}", user.getVersion(), version));
        user.setUsername(request.getUsername());
    }

    private UserDetails mapMemberToAuthorizedUser(User user) {

        return new org.springframework.security.core.userdetails.User(user.getId().toString(), passwordEncoder.encode(user.getPassword()), true, true, true, true, emptyList());
    }

}
