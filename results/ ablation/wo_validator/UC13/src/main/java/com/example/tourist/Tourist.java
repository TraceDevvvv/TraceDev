package com.example.tourist;

import java.util.Date;

/**
 * Represents a tourist entity.
 */
public class Tourist {
    private String id;
    private String name;
    private String email;
    private boolean isActive;
    private Date registrationDate;

    public Tourist(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.isActive = true; // default active
        this.registrationDate = new Date(); // current date
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}