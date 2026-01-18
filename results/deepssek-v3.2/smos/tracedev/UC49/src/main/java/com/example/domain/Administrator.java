package com.example.domain;

/**
 * Domain entity representing an administrator.
 */
public class Administrator {
    private String adminId;
    private String name;

    public Administrator() {}

    public Administrator(String adminId, String name) {
        this.adminId = adminId;
        this.name = name;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}