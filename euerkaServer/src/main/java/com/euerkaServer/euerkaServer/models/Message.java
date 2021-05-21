package com.euerkaServer.euerkaServer.models;

public class Message {
    private String nameOfSender;
    private String description;

    public Message(String nameOfSender, String description) {
        this.nameOfSender = nameOfSender;
        this.description = description;
    }

    public String getNameOfSender() {
        return nameOfSender;
    }

    public void setNameOfSender(String nameOfSender) {
        this.nameOfSender = nameOfSender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
