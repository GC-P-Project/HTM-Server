package com.gachon.htm.api.security;

import java.util.Objects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import com.gachon.htm.api.aop.Authentication;
import com.gachon.htm.api.model.AuthorizationContext;
import com.gachon.htm.api.service.TokenService;
import com.gachon.htm.api.service.UserService;
import com.gachon.htm.domain.model.Token;
import com.gachon.htm.domain.model.User;

public class SecurityProvider {

    private final TokenService tokenService;
    private final UserService userService;

    public SecurityProvider(TokenService tokenService, UserService userService) {
        this.tokenService = Objects.requireNonNull(tokenService, "tokenService");
        this.userService = Objects.requireNonNull(userService, "userService");
    }

    public boolean authenticate(JoinPoint joinPoint) {
        Authentication authentication = ((MethodSignature) joinPoint.getSignature())
                .getMethod()
                .getAnnotation(Authentication.class);

        if (authentication.doAuthentication()) {
            Object[] methodArguments = joinPoint.getArgs();
            AuthorizationContext authorizationContext = (AuthorizationContext) methodArguments[0];
            System.out.println(authorizationContext);
            Token token = tokenService.find(authorizationContext.getToken());
            System.out.println(token);
            if (token == null) {
                return false;
            } else {
                User user = userService.find(token.getUserId());
                if (user == null) {
                    return false;
                } else {
                    authorizationContext.with(userService.find(token.getUserId()));
                    return true;
                }
            }
        }
        return true;
    }
}
