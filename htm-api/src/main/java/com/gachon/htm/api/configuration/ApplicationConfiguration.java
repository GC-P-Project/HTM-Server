package com.gachon.htm.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;

import com.gachon.htm.api.aop.SecurityAspect;
import com.gachon.htm.api.filter.AuthenticationTokenFilter;
import com.gachon.htm.api.security.SecurityProvider;
import com.gachon.htm.api.service.TokenService;
import com.gachon.htm.api.service.UserService;
import com.gachon.htm.domain.configuration.DatabaseConfiguration;

@Configuration
@Import(DatabaseConfiguration.class)
public class ApplicationConfiguration {

    @Order(0)
    @Bean
    public AuthenticationTokenFilter authenticationTokenFilter() {
        return new AuthenticationTokenFilter();
    }

    @Bean
    public SecurityProvider securityProvider(TokenService tokenService, UserService userService) {
        return new SecurityProvider(tokenService, userService);
    }

    @Bean
    public SecurityAspect securityAspect(SecurityProvider securityProvider) {
        return new SecurityAspect(securityProvider);
    }
}
