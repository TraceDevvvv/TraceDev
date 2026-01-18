package com.example.app;

/**
 * Provides system-wide context information.
 * This class corresponds to the 'SystemContext' class in the UML Class Diagram (R4).
 * For demonstration purposes, the completion status of 'ViewingLancoclasses' can be toggled.
 */
public class SystemContext {
    // Assumption: Default status is true for happy path demonstration
    private boolean viewingLancoclassesCompleted = true;

    /**
     * Checks if the 'ViewingLancoclasses' use case has been completed.
     * @return true if completed, false otherwise.
     */
    public boolean isViewingLancoclassesCompleted() {
        System.out.println("SystemContext: Checking 'isViewingLancoclassesCompleted' status. Completed: " + viewingLancoclassesCompleted);
        return viewingLancoclassesCompleted;
    }

    /**
     * Sets the completion status of 'ViewingLancoclasses' for testing purposes.
     * @param completed The desired completion status.
     */
    public void setViewingLancoclassesCompleted(boolean completed) {
        this.viewingLancoclassesCompleted = completed;
        System.out.println("SystemContext: 'isViewingLancoclassesCompleted' status set to " + completed);
    }
}