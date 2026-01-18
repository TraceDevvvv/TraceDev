package com.example;

import java.util.ArrayList;
import java.util.List;

// Service for authentication and entry condition validation
public class AuthService {
    private List<EntryCondition> entryConditions;

    public AuthService() {
        this.entryConditions = new ArrayList<>();
        // Initialize with default entry conditions
        entryConditions.add(new EntryCondition("EC001", "User must be authenticated", true));
        entryConditions.add(new EntryCondition("EC002", "User must have search permissions", true));
    }

    public AuthService(List<EntryCondition> entryConditions) {
        this.entryConditions = entryConditions;
    }

    public List<EntryCondition> getEntryConditions() {
        return entryConditions;
    }

    public void setEntryConditions(List<EntryCondition> entryConditions) {
        this.entryConditions = entryConditions;
    }

    // Validates all entry conditions (R4)
    public boolean validateEntryConditions() {
        if (entryConditions.isEmpty()) {
            return true; // No conditions to validate
        }
        
        for (EntryCondition condition : entryConditions) {
            if (!condition.evaluate()) {
                return false; // At least one condition failed
            }
        }
        
        return true; // All conditions passed
    }

    // Checks if a specific user is authenticated
    public boolean isUserAuthenticated(String userId) {
        // In a real system, this would check against an authentication system
        // For demo, assume authenticated if userId is not empty
        return userId != null && !userId.trim().isEmpty();
    }

    // Adds a new entry condition
    public void addEntryCondition(EntryCondition condition) {
        entryConditions.add(condition);
    }
}