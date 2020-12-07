package com.platy.todolist.entities;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubTask {
    @JsonProperty("id")
    private long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("completed")
    private boolean completed;


    public SubTask() {
    }

    public SubTask(long id, String name, String description, boolean completed) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.completed = completed;
    }

    public SubTask(String name, String description, boolean completed) {
        this.name = name;
        this.description = description;
        this.completed = completed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }




    @NonNull
    @Override
    public String toString() {
        return "SUBTASK name: " + name + " description: " + description + " completed: " +
                completed + "\n";
    }
}
