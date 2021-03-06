package com.gachon.htm.api.controller;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gachon.htm.api.aop.Authentication;
import com.gachon.htm.api.model.AuthorizationContext;
import com.gachon.htm.api.model.request.UserSignInRequest;
import com.gachon.htm.api.model.request.UserSignUpRequest;
import com.gachon.htm.api.model.response.UserSignInResponse;
import com.gachon.htm.api.service.TokenService;
import com.gachon.htm.api.service.UserService;
import com.gachon.htm.domain.model.Token;
import com.gachon.htm.domain.model.User;
import com.gachon.htm.utils.HashUtils;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;
    private final TokenService tokenService;

    public UserController(UserService userService, TokenService tokenService) {
        this.userService = Objects.requireNonNull(userService, "userService");
        this.tokenService = Objects.requireNonNull(tokenService, "tokenService");
    }

    @Authentication(doAuthentication = false)
    @RequestMapping(path = "/signUp", method = RequestMethod.POST)
    public ResponseEntity signUp(@RequestBody UserSignUpRequest userSignUpRequest) {
        User user = userSignUpRequest.getUser();
        user.with(HashUtils.hashEncode(user.getPassword()));
        if (userService.insert(user)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @Authentication(doAuthentication = false)
    @RequestMapping(path = "/signIn", method = RequestMethod.POST)
    public ResponseEntity signIn(@RequestBody UserSignInRequest userSignInRequest) {
        userSignInRequest.encodePassword();
        User user = userService.find(userSignInRequest.getEmail(), userSignInRequest.getPassword());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } else {
            tokenService.delete(user);
            Token token = tokenService.insert(user);
            return ResponseEntity.ok(new UserSignInResponse(token, user));
        }
    }

    @Authentication(doAuthentication = true)
    @RequestMapping(path = "/signOut", method = RequestMethod.POST)
    public ResponseEntity signOut(@RequestAttribute("authorizationContext") AuthorizationContext authorizationContext) {
        User user = authorizationContext.getUser();
        tokenService.delete(user);
        return ResponseEntity.ok().build();
    }

    @Authentication(doAuthentication = true)
    @RequestMapping(path = "/info", method = RequestMethod.GET)
    public ResponseEntity userInfo(@RequestAttribute("authorizationContext") AuthorizationContext authorizationContext) {
        return ResponseEntity.ok(authorizationContext.getUser());
    }
}
