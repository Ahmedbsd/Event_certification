package com.example.model;

public class Event {
    private int id;
    private String title;
    private String description;
    private String date;
    private String period;
    private int managerId;

    public Event(int id, String title, String description, String date, String period, int managerId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.period = period;
        this.managerId = managerId;
    }

    public Event(String title, String description, String date, String period, int managerId) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.period = period;
        this.managerId = managerId;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getPeriod() {
        return period;
    }

    public int getManagerId() {
        return managerId;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }
}
