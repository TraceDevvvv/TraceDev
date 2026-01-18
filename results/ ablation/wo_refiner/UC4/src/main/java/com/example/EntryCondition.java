package com.example;

// Entity class representing an entry condition that must be evaluated before allowing access
public class EntryCondition {
    private String conditionId;
    private String description;
    private boolean isActive;

    public EntryCondition() {
    }

    public EntryCondition(String conditionId, String description, boolean isActive) {
        this.conditionId = conditionId;
        this.description = description;
        this.isActive = isActive;
    }

    public String getConditionId() {
        return conditionId;
    }

    public void setConditionId(String conditionId) {
        this.conditionId = conditionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    // Evaluates the entry condition based on its active status
    public boolean evaluate() {
        // In a real system, this would have complex logic
        // For now, just return the active status
        return isActive;
    }
}