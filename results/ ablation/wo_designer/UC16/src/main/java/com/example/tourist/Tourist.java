package com.example.tourist;

public class Tourist {
    private String id;
    private String name;
    private String email;

    public Tourist(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
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

    @Override
    public String toString() {
        return "Tourist{id='" + id + "', name='" + name + "', email='" + email + "'}";
    }
}