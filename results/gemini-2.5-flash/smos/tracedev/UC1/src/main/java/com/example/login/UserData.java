package com.example.login;

/**
 * Data Transfer Object (DTO): Represents user data as returned by the ExternalSMOSServer.
 * This class is an assumption based on the `queryUserData` return type.
 */
public class UserData {
    private String id;
    private String username;
    private String passwordHash; // Store the hash as received from external system

    public UserData(String id, String username, String passwordHash) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
}