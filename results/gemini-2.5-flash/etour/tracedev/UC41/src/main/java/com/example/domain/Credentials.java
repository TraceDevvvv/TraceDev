package com.example.domain;

/**
 * Represents user credentials for authentication.
 * Added to satisfy R3.
 */
public class Credentials {
    private String username;
    private String password;

    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Credentials{" +
               "username='" + username + '\'' +
               ", password='[PROTECTED]'" +
               '}';
    }
}