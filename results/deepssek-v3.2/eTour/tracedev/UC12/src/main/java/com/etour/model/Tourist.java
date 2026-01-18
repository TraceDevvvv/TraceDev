package com.etour.model;

import java.util.Map;

/**
 * Entity class representing a tourist.
 */
public class Tourist {
    private String id;
    private String name;
    private String email;
    private Map<String, String> otherDetails;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, String> getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(Map<String, String> otherDetails) {
        this.otherDetails = otherDetails;
    }

    @Override
    public String toString() {
        return "Tourist [id=" + id + ", name=" + name + ", email=" + email + ", otherDetails=" + otherDetails + "]";
    }
}