package com.gachon.htm.api.service;

import java.util.Objects;
import java.util.Optional;

import com.gachon.htm.api.model.request.ExerciseWatchRequest;
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

    public User find(String email, String password) {
        Optional<User> user = userRepository.findByEmailAndPassword(email, password);
        return user.orElse(null);
    }

    public boolean insert(User user) {
        try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean watchVideo(User user, ExerciseWatchRequest request) {
        int time = request.getTime();
        switch ((int) request.getKindId()) {
            case 1:
                user.setAllTime(time + user.getAllTime());
                break;
            case 2:
            case 3:
            case 4:
            case 5:
                user.setUpperTime(time + user.getUpperTime());
                break;
            case 6:
                user.setLowerTime(time + user.getLowerTime());
                break;
            default:
                return false;
        }
        try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
