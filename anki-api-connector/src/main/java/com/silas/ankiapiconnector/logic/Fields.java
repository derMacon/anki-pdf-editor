package com.silas.ankiapiconnector.logic;

public class Fields {
    private String Front;
    private String Back;

    public Fields(String front, String back) {
        Front = front;
        Back = back;
    }

    public String getFront() {
        return Front;
    }

    public String getBack() {
        return Back;
    }

    public void setFront(String front) {
        Front = front;
    }

    public void setBack(String back) {
        Back = back;
    }
}
