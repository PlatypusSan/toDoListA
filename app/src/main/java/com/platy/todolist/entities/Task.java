package com.platy.todolist.entities;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Task {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("colour")
    private String colour;

    @JsonProperty("subTasks")
    private List<SubTask> subTasks;

    @JsonProperty("completed")
    private boolean completed;

    public Task() {
    }

    public Task(String name, String description, String colour, List<SubTask> subTasks, boolean completed) {
        this.name = name;
        this.description = description;
        this.colour = colour;
        this.subTasks = subTasks;
        this.completed = completed;
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

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubTask> subTasks) {
        this.subTasks = subTasks;
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
        return "TASK name: " + name + " description: " + description + " completed: " +
                completed + " colour: " + colour + " subTasks: " + subTasks + "\n";
    }
}
