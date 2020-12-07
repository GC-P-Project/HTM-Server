package com.gachon.htm.api.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gachon.htm.api.model.AuthorizationContext;

public class AuthenticationTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        AuthorizationContext authorizationContext;
        if (StringUtils.isNotBlank(request.getHeader("Authorization"))) {
            authorizationContext = new AuthorizationContext(request.getHeader("Authorization"), null);
            request.setAttribute("authorizationContext", authorizationContext);
        }
        filterChain.doFilter(request, response);
    }
}
