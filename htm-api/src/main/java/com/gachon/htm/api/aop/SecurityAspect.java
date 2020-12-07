package com.gachon.htm.api.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import com.gachon.htm.api.exception.AuthorizationException;
import com.gachon.htm.api.security.SecurityProvider;

@Aspect
public class SecurityAspect {

    private final SecurityProvider securityProvider;

    public SecurityAspect(SecurityProvider securityProvider) {
        this.securityProvider = securityProvider;
    }

    @Before("execution(* com.gachon.htm.api.controller.*.*(..)) && @annotation(com.gachon.htm.api.aop.Authentication)")
    public void authenticate(JoinPoint joinPoint) {
        if (!securityProvider.authenticate(joinPoint)) {
            throw new AuthorizationException();
        }
    }
}
