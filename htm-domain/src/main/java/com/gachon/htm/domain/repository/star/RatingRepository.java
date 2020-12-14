package com.gachon.htm.domain.repository.star;

import com.gachon.htm.domain.model.Exercise;
import com.gachon.htm.domain.model.Rating;
import com.gachon.htm.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    Optional<Rating> findByExerciseAndUser(Exercise exercise, User user);
}
