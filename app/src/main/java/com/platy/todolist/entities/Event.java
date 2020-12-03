package com.platy.todolist.entities;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {

    @JsonProperty("id")
    private long id;

    @JsonProperty("duration")
    private int duration;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("date")
    private Date date;

    @JsonProperty("task")
    private Task task;

    public Event() {
    }

    public Event(long id, int duration, String name, String description, Date date, Task task) {
        this.id = id;
        this.duration = duration;
        this.name = name;
        this.description = description;
        this.date = date;
        this.task = task;
    }

    public Event(int duration, String name, String description, Date date, Task task) {
        this.duration = duration;
        this.name = name;
        this.description = description;
        this.date = date;
        this.task = task;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @NonNull
    @Override
    public String toString() {
        return "EVENT id: " + id + " duration: " + duration + " name: " + name + " description: "
                + description + " date: " + date + " task: " + task + "\n";
    }
}
