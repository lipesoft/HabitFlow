package com.example.habitflow.data.model;

public class Habit {
    private String _id;   // MongoDB usa _id
    private String name;
    private String description;
    private boolean completed;

    // Getter para o ID (suporta tanto "id" quanto "_id" do MongoDB)
    public String getId() {
        return _id;
    }

    public String getName()        { return name; }
    public String getDescription() { return description; }
    public boolean isCompleted()   { return completed; }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
