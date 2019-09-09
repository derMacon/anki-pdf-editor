package com.silas.ankiapiconnector.logic;

public class Card {
    private String front;
    private String back;
    private String tags;

    public Card(String front, String back, String tags) {
        this.front = front;
        this.back = back;
        this.tags = tags;
    }

    public String getFront() {
        return front;
    }

    public String getBack() {
        return back;
    }

    public String getTags() {
        return tags;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "front: " + this.front + "\n"
                + "back: " + this.back + "\n"
                + "tags: " + this.tags;
    }
}
