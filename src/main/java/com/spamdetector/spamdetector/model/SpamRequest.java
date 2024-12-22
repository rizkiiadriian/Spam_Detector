package com.spamdetector.spamdetector.model;



public class SpamRequest {
    private String text;

    // Constructor
    public SpamRequest(String text) {
        this.text = text;
    }

    // Getter dan Setter
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
