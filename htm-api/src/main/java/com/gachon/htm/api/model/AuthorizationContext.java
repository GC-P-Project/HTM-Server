package com.gachon.htm.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gachon.htm.domain.model.User;

public class AuthorizationContext {

    private String token;
    private User user;

    @JsonCreator
    public AuthorizationContext(@JsonProperty("token") String token,
                                @JsonProperty("user") User user) {
        this.token = token;
        this.user = user;
    }

    public AuthorizationContext with(User user) {
        this.user = user;
        return this;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "AuthorizationContext{" +
               "token='" + token + '\'' +
               ", user=" + user +
               '}';
    }
}
