package com.gachon.htm.api.service;

import com.gachon.htm.domain.model.Exercise;
import com.gachon.htm.domain.model.Rating;
import com.gachon.htm.domain.model.User;
import com.gachon.htm.domain.repository.rating.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public boolean rating(User user, Exercise exercise, int score) {
        Optional<Rating> rating = ratingRepository.findByExerciseAndUser(exercise, user);
        if (!rating.isPresent()) {
            Rating newRating = new Rating();
            newRating.setCount(1);
            newRating.setExercise(exercise);
            newRating.setUser(user);
            newRating.setScore(score);
            try {
                ratingRepository.save(newRating);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            rating.get().updateScore(score);
            try {
                ratingRepository.save(rating.get());
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }
}
