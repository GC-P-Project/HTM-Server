package com.gachon.htm.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "token")
public class Token {

    @Id
    @Column(name = "token")
    private String token;

    @Column(name = "user_id")
    private long userId;

    public Token() {
    }

    @JsonCreator
    public Token(@JsonProperty("token") String token,
                 @JsonProperty("userId") long userId) {
        this.token = token;
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public long getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "Token{" +
               "token='" + token + '\'' +
               ", userId=" + userId +
               '}';
    }
}
