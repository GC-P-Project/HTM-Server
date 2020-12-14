package com.gachon.htm.domain.repository.exercise;

import com.gachon.htm.domain.model.ExerciseKind;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseKindRepository extends JpaRepository<ExerciseKind, Long> {
    List<ExerciseKind> findByKindContainsOrderById(String kind);
}
