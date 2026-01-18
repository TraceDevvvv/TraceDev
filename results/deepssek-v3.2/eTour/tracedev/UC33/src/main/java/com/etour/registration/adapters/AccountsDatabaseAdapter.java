package com.etour.registration.adapters;

/**
 * Adapter representing the Accounts Database from sequence diagram.
 */
public class AccountsDatabaseAdapter {
    public String executeSelectCountEmail(String email) {
        System.out.println("Database executing: SELECT COUNT(*) FROM accounts WHERE email = '" + email + "'");
        return "0"; // Return count result
    }

    public String executeSelectCountUsername(String username) {
        System.out.println("Database executing: SELECT COUNT(*) FROM accounts WHERE username = '" + username + "'");
        return "0"; // Return count result
    }

    public String executeInsert(String id, String username, String email, String passwordHash, String status) {
        System.out.println("Database executing: INSERT INTO accounts VALUES ('" + id + "', '" + username + "', '" + 
                          email + "', '" + passwordHash + "', '" + status + "')");
        return id; // Return generated ID
    }

    public String getGeneratedId() {
        return "db-generated-" + System.currentTimeMillis();
    }
}