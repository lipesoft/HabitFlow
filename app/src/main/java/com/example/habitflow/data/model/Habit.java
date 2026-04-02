package com.example.habitflow.data.model;

public class Habit {
    private String id;
    private String name;
    private String description;
    private boolean completed;

    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public boolean isCompleted() { return completed; }
}