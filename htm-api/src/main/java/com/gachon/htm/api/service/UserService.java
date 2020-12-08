package com.gachon.htm.api.service;

import java.util.Objects;
import java.util.Optional;

import com.gachon.htm.domain.model.User;
import com.gachon.htm.domain.repository.user.UserRepository;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository, "userRepository");
    }

    public User find(long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElse(null);
    }
}
