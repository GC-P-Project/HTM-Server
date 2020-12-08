package com.gachon.htm.domain.model;

import lombok.*;

import javax.persistence.*;

@Builder @Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
public class ExerciseKind {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String kind;
}
