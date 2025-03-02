package com.example.model;

public class User {
    private int idUser;
    private String fullName;
    private String email;
    private String password;
    private String cin;
    private String type;

    public User( String fullName, String email, String password, String cin, String type) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.cin = cin;
        this.type = type;
    }
    
    public User( int idUser, String fullName, String email, String password, String cin, String type) {
    	this.idUser=idUser;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.cin = cin;
        this.type = type;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

