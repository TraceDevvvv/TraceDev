package com.example.model;

/**
 * Represents ATA Staff actor.
 * Extends User to inherit user properties.
 */
public class ATAStaff extends User {
    public ATAStaff(String userId, String role) {
        super(userId, role);
    }
    
    // Additional ATA staff specific methods can be added here
}