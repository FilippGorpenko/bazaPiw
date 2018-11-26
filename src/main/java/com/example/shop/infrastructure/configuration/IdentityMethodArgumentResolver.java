package com.example.shop.infrastructure.configuration;

import com.example.shop.api.Identity;
import org.springframework.core.MethodParameter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;
import java.util.Optional;

import static java.lang.Long.parseLong;

public class IdentityMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(Identity.class)
                && methodParameter.hasParameterAnnotation(AuthenticationPrincipal.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        return getAuthenticatedIdentity()
                .orElseThrow(() -> new AccessDeniedException("AuthenticationPrincipal required authentication context!"));
    }

    public Optional<Identity> getAuthenticatedIdentity() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.nonNull(authentication)) {
            String principalName = authentication.getName();
            Identity identity = new Identity(parsePrincipalNameToLong(principalName));
            return Optional.of(identity);
        }

        return Optional.empty();
    }

    private Long parsePrincipalNameToLong(String principalName) {
        try {
            return parseLong(principalName);
        } catch (NumberFormatException e) {
            throw new IllegalStateException(String.format("Could not parse principal name: '%s' to numeric user id", principalName), e);
        }
    }
}

