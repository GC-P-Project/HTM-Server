package com.gachon.htm.api.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gachon.htm.domain.model.User;

public class UserSignUpRequest {

    private final User user;

    @JsonCreator
    public UserSignUpRequest(@JsonProperty("user") User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "UserSignUpRequest{" +
               "user=" + user +
               '}';
    }
}
