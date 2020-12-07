package com.gachon.htm.api.controller;

import com.gachon.htm.domain.model.Exercise;
import com.gachon.htm.domain.model.ExerciseKind;
import com.gachon.htm.domain.repository.test.ExerciseKindRepository;
import com.gachon.htm.domain.repository.test.ExerciseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/HTM")
public class ExerciseController {

    private final ExerciseRepository exerciseRepository;
    private final ExerciseKindRepository exerciseKindRepository;

    public ExerciseController(ExerciseRepository exerciseRepository, ExerciseKindRepository exerciseKindRepository) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseKindRepository = exerciseKindRepository;
    }

    @RequestMapping(value = "/list/{kind}", method = RequestMethod.GET)
    public ResponseEntity<List<Exercise>> list(@PathVariable String kind) {
        List<ExerciseKind> byKindContains = exerciseKindRepository.findByKindContains(kind);
        if(byKindContains.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<Exercise> list = new ArrayList<>();
        byKindContains.forEach(k -> {
            list.addAll(exerciseRepository.findByKind(k));
        });
        return ResponseEntity.ok(list);
    }

    @RequestMapping(value = "/video/{id}", method = RequestMethod.GET)
    public ResponseEntity<Exercise> video(@PathVariable long id) {
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        if (!exercise.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(exercise.get());
    }

}
