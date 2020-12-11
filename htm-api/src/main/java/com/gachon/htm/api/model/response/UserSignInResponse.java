package com.gachon.htm.api.model.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gachon.htm.domain.model.Token;
import com.gachon.htm.domain.model.User;

public class UserSignInResponse {

    private final Token token;
    private final User user;

    @JsonCreator
    public UserSignInResponse(@JsonProperty("token") Token token,
                              @JsonProperty("user") User user) {
        this.token = token;
        this.user = user;
    }

    public Token getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "UserSignInResponse{" +
               "token=" + token +
               ", user=" + user +
               '}';
    }
}
