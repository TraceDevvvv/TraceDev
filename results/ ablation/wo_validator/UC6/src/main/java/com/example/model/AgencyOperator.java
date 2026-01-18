package com.example.model;

/**
 * Represents an agency operator who can perform operations.
 */
public class AgencyOperator {
    private String id;
    private String name;

    public AgencyOperator(String id, String name) {
        this.id = id;
        this.name = name;
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

    /**
     * Checks if the operator is logged in.
     *
     * @return true if the operator is considered logged in (simplified).
     */
    public boolean isLoggedIn() {
        // Assumption: Logged in if id and name are not null.
        return id != null && name != null;
    }

    /**
     * Selects a rest point for an operation.
     *
     * @param restPointId the ID of the rest point.
     */
    public void selectRestPoint(int restPointId) {
        System.out.println("Operator " + name + " selected rest point with ID: " + restPointId);
    }

    /**
     * Simulates confirming deletion via a dialog.
     *
     * @return true if confirmed, false otherwise.
     */
    public boolean confirmDeletion() {
        // Simulates user confirmation; in real scenario might use UI.
        // Assumption: For this example, we assume confirmation is true for successful flow.
        return true;
    }

    /**
     * Simulates cancelling the operation via a dialog.
     */
    public void cancelOperation() {
        System.out.println("Operator " + name + " cancelled the operation.");
    }
}