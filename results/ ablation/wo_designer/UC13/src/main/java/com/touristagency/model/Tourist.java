package com.touristagency.model;

import java.io.Serializable;

/**
 * Represents a Tourist with an account status.
 */
public class Tourist implements Serializable {
    private String id;
    private String name;
    private String email;
    private boolean accountEnabled;

    public Tourist(String id, String name, String email, boolean accountEnabled) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.accountEnabled = accountEnabled;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAccountEnabled() {
        return accountEnabled;
    }

    public void setAccountEnabled(boolean accountEnabled) {
        this.accountEnabled = accountEnabled;
    }

    @Override
    public String toString() {
        return "Tourist{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", accountEnabled=" + accountEnabled +
                '}';
    }
}