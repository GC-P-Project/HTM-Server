package com.gachon.htm.domain.repository.exercise;

import com.gachon.htm.domain.model.Exercise;
import com.gachon.htm.domain.model.ExerciseKind;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    List<Exercise> findByKind(ExerciseKind kind);
    List<Exercise> findByKindGreaterThanEqualAndKindLessThanEqualOrderByTime(ExerciseKind kind1, ExerciseKind kind2);

}
