package com.gachon.htm.api.service;

import com.gachon.htm.api.model.response.ExerciseListResponse;
import com.gachon.htm.api.model.response.ExerciseVideoResponse;
import com.gachon.htm.domain.model.Exercise;
import com.gachon.htm.domain.model.ExerciseKind;
import com.gachon.htm.domain.repository.Exercise.ExerciseRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ModelMapper modelMapper;

    public ExerciseService(ExerciseRepository exerciseRepository, ModelMapper modelMapper) {
        this.exerciseRepository = exerciseRepository;
        this.modelMapper = modelMapper;
    }

    public List<ExerciseListResponse> findVideo(List<ExerciseKind> list) {
        List<ExerciseListResponse> responses = new ArrayList<>();
        Type type = new TypeToken<List<ExerciseListResponse>>(){}.getType();
        list.forEach(l -> {
            responses.addAll(modelMapper.map(exerciseRepository.findByKind(l), type));
        });
        return responses;
    }

    public ExerciseVideoResponse findVideo(long id) {
        return modelMapper.map(exerciseRepository.findById(id).orElse(null), ExerciseVideoResponse.class);
    }

    public Exercise find(long id) {
        return exerciseRepository.findById(id).orElseGet(null);
    }
}
