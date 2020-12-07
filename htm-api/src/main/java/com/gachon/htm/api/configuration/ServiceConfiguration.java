package com.gachon.htm.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gachon.htm.api.service.TestService;
import com.gachon.htm.api.service.TokenService;
import com.gachon.htm.api.service.UserService;
import com.gachon.htm.domain.repository.test.TestRepository;
import com.gachon.htm.domain.repository.token.TokenRepository;
import com.gachon.htm.domain.repository.user.UserRepository;

@Configuration
public class ServiceConfiguration {

    @Bean
    public TestService testService(TestRepository testRepository) {
        return new TestService(testRepository);
    }

    @Bean
    public TokenService tokenService(TokenRepository tokenRepository) {
        return new TokenService(tokenRepository);
    }

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserService(userRepository);
    }
}
