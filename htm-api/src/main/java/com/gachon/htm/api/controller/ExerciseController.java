package com.gachon.htm.api.controller;

import com.gachon.htm.api.model.response.ExerciseListResponse;
import com.gachon.htm.api.model.response.ExerciseVideoResponse;
import com.gachon.htm.api.service.ExerciseKindService;
import com.gachon.htm.api.service.ExerciseService;
import com.gachon.htm.domain.model.ExerciseKind;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/HTM")
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final ExerciseKindService exerciseKindService;
    private final ModelMapper modelMapper;


    public ExerciseController(ExerciseService exerciseRepository1, ExerciseKindService exerciseKindService, ModelMapper modelMapper) {
        this.exerciseService = exerciseRepository1;
        this.exerciseKindService = exerciseKindService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value = "/list/{kind}", method = RequestMethod.GET)
    public ResponseEntity list(@PathVariable String kind) {
        List<ExerciseKind> byKindContains = exerciseKindService.find(kind);
        if (byKindContains.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<ExerciseListResponse> responses = exerciseService.find(byKindContains);
        if (responses.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(responses);
    }

    @RequestMapping(value = "/video/{id}", method = RequestMethod.GET)
    public ResponseEntity video(@PathVariable long id) {
        ExerciseVideoResponse exercise = exerciseService.find(id);
        if (exercise == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(exercise);
    }

}
