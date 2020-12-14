package com.gachon.htm.domain.model;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Rating {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Exercise exercise;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private double score;

    @Column(nullable = false)
    private int count;

    public void updateScore(double score) {
        this.score = (this.score * this.count + score) / (this.count + 1);
        this.count += 1;
    }
}
