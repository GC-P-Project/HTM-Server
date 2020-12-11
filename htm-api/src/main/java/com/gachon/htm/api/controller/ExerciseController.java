package com.gachon.htm.api.controller;

import com.gachon.htm.api.aop.Authentication;
import com.gachon.htm.api.model.AuthorizationContext;
import com.gachon.htm.api.model.request.ExerciseWatchRequest;
import com.gachon.htm.api.model.response.ExerciseListResponse;
import com.gachon.htm.api.model.response.ExerciseVideoResponse;
import com.gachon.htm.api.service.ExerciseKindService;
import com.gachon.htm.api.service.ExerciseService;
import com.gachon.htm.api.service.UserService;
import com.gachon.htm.domain.model.ExerciseKind;
import com.gachon.htm.domain.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/HTM")
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final ExerciseKindService exerciseKindService;
    private final UserService userService;


    public ExerciseController(ExerciseService exerciseRepository1, ExerciseKindService exerciseKindService, UserService userService) {
        this.exerciseService = exerciseRepository1;
        this.exerciseKindService = exerciseKindService;
        this.userService = userService;
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

    @Authentication(doAuthentication = true)
    @RequestMapping(value = "/watch", method = RequestMethod.POST)
    public ResponseEntity watch(@RequestAttribute("authorizationContext") AuthorizationContext authorizationContext, @RequestBody ExerciseWatchRequest request) {
        User user = authorizationContext.getUser();
        if (!userService.watchVideo(user, request)) {
            return ResponseEntity.badRequest().build();
        } else return ResponseEntity.ok().build();
    }

}
