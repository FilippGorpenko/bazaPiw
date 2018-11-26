package com.example.shop.infrastructure.configuration.outh2;

import com.example.shop.infrastructure.configuration.IdentityMethodArgumentResolver;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableResourceServer
@EnableAuthorizationServer
public class OAuth2Configuration {
    @Configuration
    public static class GlobalAuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {

        private final UserDetailsService userDetailsService;
        private final PasswordEncoder passwordEncoder;

        public GlobalAuthenticationConfiguration(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
            this.userDetailsService = userDetailsService;
            this.passwordEncoder = passwordEncoder;
        }

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        }
    }

    @Configuration
    @AllArgsConstructor
    public static class CustomResourceServerConfigurer implements ResourceServerConfigurer {

        private final ResourceServerProperties resourceServerProperties;

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.resourceId(resourceServerProperties.getResourceId());
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.csrf().disable().sessionManagement().sessionCreationPolicy(STATELESS)
                    .and().authorizeRequests().antMatchers(getPublicEndpoints()).permitAll()
                    .and().authorizeRequests().antMatchers("/registrations/changes").hasRole("REGISTER")
                    .anyRequest().authenticated();
        }

        private String[] getPublicEndpoints() {
            return new String[] {
                    "/oauth/token",
                    "/swagger/**",
                    "/tokens/**",
                    "/register/**",
            };
        }
    }

    @Configuration
    public static class TokenStoreConfig {

        @Bean
        public TokenStore tokenStore(RedisConnectionFactory redisConnectionFactory) {
            return new RedisTokenStore(redisConnectionFactory);
        }
    }

    @Configuration
    public static class PasswordEncoderConfig {

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }

    @Configuration
    public static class IdentityConfiguration extends WebMvcConfigurerAdapter {

        public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
            argumentResolvers.add(new IdentityMethodArgumentResolver());
        }
    }
}
