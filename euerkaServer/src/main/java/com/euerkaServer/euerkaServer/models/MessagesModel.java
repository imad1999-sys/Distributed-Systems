package com.euerkaServer.euerkaServer.models;

public class MessagesModel {
    private String description;
    private String nameOfSender;

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

    public MessagesModel(String nameOfSender, String description) {
        this.nameOfSender = nameOfSender;
        this.description = description;
    }
}
