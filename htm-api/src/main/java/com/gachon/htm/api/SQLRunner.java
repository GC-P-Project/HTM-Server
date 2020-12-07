package com.gachon.htm.api;

import com.gachon.htm.domain.model.Exercise;
import com.gachon.htm.domain.model.ExerciseKind;
import com.gachon.htm.domain.repository.test.ExerciseKindRepository;
import com.gachon.htm.domain.repository.test.ExerciseRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * DB에 데이터를 주입하기 위한 SQL Runner
 * **/

@Component
public class SQLRunner implements ApplicationRunner {

    private final ExerciseRepository exerciseRepository;
    private final ExerciseKindRepository exerciseKindRepository;

    public SQLRunner(ExerciseRepository exerciseRepository, ExerciseKindRepository exerciseKindRepository) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseKindRepository = exerciseKindRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ExerciseKind exerciseKind = ExerciseKind.builder()
                .kind("Upper_Back")
                .build();
        ExerciseKind save = exerciseKindRepository.save(exerciseKind);

        Exercise exercise = Exercise.builder()
                .title("등운동에 이걸 빼먹진 않겠죠? 등운동의 기초&팁")
                .time(11)
                .iframe("<style>.embed-container { position: relative; padding-bottom: 56.25%; height: 0; overflow: hidden; max-width: 100%; } .embed-container iframe, .embed-container object, .embed-container embed { position: absolute; top: 0; left: 0; width: 100%; height: 100%; }</style><div class='embed-container'><iframe src='https://www.youtube.com/embed/E7TQPZCTYIs' frameborder='0' allowfullscreen></iframe></div>")
                .image("https://i.ytimg.com/an_webp/E7TQPZCTYIs/mqdefault_6s.webp?du=3000&sqp=CIvRuP4F&rs=AOn4CLDDR_CqO6TwcRkU87SQc0a6ooy7kg")
                .kind(save)
                .build();
        exerciseRepository.save(exercise);
    }
}
