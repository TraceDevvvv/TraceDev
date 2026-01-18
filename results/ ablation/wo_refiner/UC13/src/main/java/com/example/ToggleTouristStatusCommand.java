package com.example;

/**
 * Command object for toggling tourist status.
 */
public class ToggleTouristStatusCommand {
    private int touristId;
    private boolean targetStatus;

    public ToggleTouristStatusCommand(int touristId, boolean targetStatus) {
        this.touristId = touristId;
        this.targetStatus = targetStatus;
    }

    public int getTouristId() {
        return touristId;
    }

    public boolean getTargetStatus() {
        return targetStatus;
    }
}