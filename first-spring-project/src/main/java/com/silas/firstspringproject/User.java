package com.silas.firstspringproject;

public class User {
    private Integer name;

    public User(Integer name) {
        this.name = name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public Integer getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.valueOf(this.name);
    }
}

