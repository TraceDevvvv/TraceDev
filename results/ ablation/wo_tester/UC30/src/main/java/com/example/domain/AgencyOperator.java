package com.example.domain;

import com.example.application.dto.InsertTagRequest;

/**
 * Represents an agency operator actor.
 */
public class AgencyOperator {
    private String userId;
    private String name;

    public AgencyOperator(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Simulates login as per entry condition: Agency Operator HAS logged.
     */
    public void login() {
        System.out.println("Agency Operator " + name + " logged in.");
    }

    /**
     * Simulates submitting a tag form.
     */
    public void submitTagForm(InsertTagRequest request) {
        System.out.println("Agency Operator " + name + " submitted tag form for tag: " + request.getTagName());
    }
}