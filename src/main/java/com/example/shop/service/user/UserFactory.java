package com.example.shop.service.user;

import com.example.shop.api.user.UserCreationRequest;
import com.example.shop.api.user.UserSnapshot;
import com.example.shop.mapping.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserFactory {

    private final PasswordEncoder passwordEncoder;

    User userFromCreationRequest(UserCreationRequest request) {
        return User.builder()
                .username(request.getUsername())
//                .password(passwordEncoder.encode(request.getPassword()))
                .password(request.getPassword())
                .build();
    }

    UserSnapshot userSnapshotFromUser(User user) {
        return UserSnapshot.builder()
                .id(user.getId())
                .username(user.getUsername())
                .version(user.getVersion())
                .litecoinAmount(user.getLitecoinAmount())
                .moneroAmount(user.getMoneroAmount())
                .build();
    }

}
