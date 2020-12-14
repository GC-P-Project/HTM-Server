package com.gachon.htm.api.service;

import com.gachon.htm.domain.model.ExerciseKind;
import com.gachon.htm.domain.repository.exercise.ExerciseKindRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseKindService {

    private final ExerciseKindRepository exerciseKindRepository;

    public ExerciseKindService(ExerciseKindRepository exerciseKindRepository) {
        this.exerciseKindRepository = exerciseKindRepository;
    }

    public List<ExerciseKind> find(String kind) { return exerciseKindRepository.findByKindContains(kind); }

}
