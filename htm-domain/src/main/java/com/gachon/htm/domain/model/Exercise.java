package com.gachon.htm.domain.model;

import lombok.*;

import javax.persistence.*;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Exercise {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 100)
    private String title;

    @Column(nullable = false)
    private int time;

    @Column(nullable = false, unique = true, length = 500)
    private String iframe;

    @Column(nullable = false, unique = true, length = 300)
    private String image;

    @ManyToOne
    private ExerciseKind kind;
}
