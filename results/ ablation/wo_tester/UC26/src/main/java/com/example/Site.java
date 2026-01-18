package com.example;

import java.util.List;

/**
 * Entity representing a site.
 */
public class Site {
    private String id;
    private String name;
    private String address;

    public Site(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Feedback> getFeedbacks() {
        // This method would typically be implemented by a repository.
        // For now, return null or an empty list as a stub.
        return null;
    }
}