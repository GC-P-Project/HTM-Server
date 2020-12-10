package com.gachon.htm.api.model.response;

import com.gachon.htm.domain.model.ExerciseKind;


public class ExerciseVideoResponse {
    private long id;
    private String title;
    private int time;
    private String iframe;
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

    public String getIframe() {
        return iframe;
    }

    public void setIframe(String iframe) {
        this.iframe = iframe;
    }

    public ExerciseKind getKind() {
        return kind;
    }

    public void setKind(ExerciseKind kind) {
        this.kind = kind;
    }
}
