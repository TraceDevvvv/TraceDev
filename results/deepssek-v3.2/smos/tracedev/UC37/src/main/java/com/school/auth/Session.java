package com.school.auth;

import java.util.Date;

/**
 * Represents a user session.
 */
public class Session {
    private String token;
    private Administrator user;
    private Date expiration;

    public Session(String token, Administrator user) {
        this.token = token;
        this.user = user;
        // Set expiration to 1 hour from now
        this.expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
    }

    public boolean isValid() {
        return new Date().before(expiration);
    }

    public String getToken() {
        return token;
    }

    public Administrator getUser() {
        return user;
    }

    public Date getExpiration() {
        return expiration;
    }
}