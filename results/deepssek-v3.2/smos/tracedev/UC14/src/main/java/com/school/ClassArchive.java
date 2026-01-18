package com.school;

/**
 * Represents the database archive for class records.
 * Requirement REQ-002 (database representation)
 */
public class ClassArchive {
    private boolean connectionAvailable = true; // default to true for simulation.

    public boolean insertClassRecord(Class classObj) {
        System.out.println("ClassArchive: Inserting class record for " + classObj.getName());
        // Simulate successful insertion.
        return true;
    }

    public boolean isConnectionAvailable() {
        return connectionAvailable;
    }

    public void setConnectionAvailable(boolean available) {
        this.connectionAvailable = available;
    }
}