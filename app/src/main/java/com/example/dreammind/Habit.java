package com.example.dreammind;

public class Habit {

    private String title;
    private String description;
    public boolean completed;

    public Habit(String title, String description, boolean completed) {
        this.title = title;
        this.description = description;
        this.completed = completed;


    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
