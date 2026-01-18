package com.example.application;

/**
 * Data Transfer Object for user summary information.
 */
public class UserSummaryDTO {
    private String id;
    private String name;
    private String surname;

    public UserSummaryDTO(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}