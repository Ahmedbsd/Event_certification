package com.example.model;

public class Participant {
    private int id;
    private String firstName;
    private String secondName;
    private String cin;
    private String role;
    private int idEvent;

    public Participant() {}

    public Participant(int id, String firstName, String secondName, String cin, String role, int idEvent) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.cin = cin;
        this.role = role;
        this.idEvent = idEvent;
    }
    public Participant(String firstName, String secondName, String cin, String role, int idEvent) {
        
        this.firstName = firstName;
        this.secondName = secondName;
        this.cin = cin;
        this.role = role;
        this.idEvent = idEvent;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getSecondName() { return secondName; }
    public void setSecondName(String secondName) { this.secondName = secondName; }

    public String getCin() { return cin; }
    public void setCin(String cin) { this.cin = cin; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public int getIdEvent() { return idEvent; }
    public void setIdEvent(int idEvent) { this.idEvent = idEvent; }
}
