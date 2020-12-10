package com.gachon.htm.api.model.response;

import com.gachon.htm.domain.model.ExerciseKind;


public class ExerciseListResponse {
    private long id;
    private String title;
    private int time;
    private String image;
    private ExerciseKind kind;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ExerciseKind getKind() {
        return kind;
    }

    public void setKind(ExerciseKind kind) {
        this.kind = kind;
    }
}
