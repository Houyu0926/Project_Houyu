package com.example.projecthouyu;

import java.util.List;

public class RestDogResponse {
    private List<Dog> messages;
    private String status;

    public List<Dog> getMessages() {
        return messages;
    }

    public String getStatus() {
        return status;
    }
}
