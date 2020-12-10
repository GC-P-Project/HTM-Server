package com.gachon.htm.api.controller;

import com.gachon.htm.api.model.response.ExerciseListResponse;
import com.gachon.htm.api.model.response.ExerciseVideoResponse;
import com.gachon.htm.domain.model.Exercise;
import com.gachon.htm.domain.model.ExerciseKind;
import com.gachon.htm.domain.repository.Exercise.ExerciseKindRepository;
import com.gachon.htm.domain.repository.Exercise.ExerciseRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/HTM")
public class ExerciseController {

    private final ExerciseRepository exerciseRepository;
    private final ExerciseKindRepository exerciseKindRepository;
    private final ModelMapper modelMapper;

    public ExerciseController(ExerciseRepository exerciseRepository, ExerciseKindRepository exerciseKindRepository, ModelMapper modelMapper) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseKindRepository = exerciseKindRepository;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value = "/list/{kind}", method = RequestMethod.GET)
    public ResponseEntity list(@PathVariable String kind) {
        List<ExerciseKind> byKindContains = exerciseKindRepository.findByKindContains(kind);
        if(byKindContains.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<Exercise> list = new ArrayList<>();
        byKindContains.forEach(k -> {
            list.addAll(exerciseRepository.findByKind(k));
        });
        Type type = new TypeToken<List<ExerciseListResponse>>(){}.getType();
        List<ExerciseListResponse> responses = modelMapper.map(list, type);
        return ResponseEntity.ok(responses);
    }

    @RequestMapping(value = "/video/{id}", method = RequestMethod.GET)
    public ResponseEntity video(@PathVariable long id) {
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        if (!exercise.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        ExerciseVideoResponse response = modelMapper.map(exercise.get(), ExerciseVideoResponse.class);
        return ResponseEntity.ok(response);
    }

}
