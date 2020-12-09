package com.gachon.htm.api.model.request;

import com.gachon.htm.utils.HashUtils;

public class UserSignInRequest {

    private final String email;
    private String password;

    public UserSignInRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserSignInRequest encodePassword() {
        this.password = HashUtils.hashEncode(password);
        return this;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "UserSignInRequest{" +
               "email='" + email + '\'' +
               ", password='" + password + '\'' +
               '}';
    }
}
