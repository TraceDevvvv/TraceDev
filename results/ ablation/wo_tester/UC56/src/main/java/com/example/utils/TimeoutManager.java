package com.example.utils;

/**
 * Manages timeout logic for GPS operations.
 */
public class TimeoutManager {
    private long timeoutDuration;
    private long startTime;
    private boolean timerStarted;

    public TimeoutManager(long timeoutMs) {
        this.timeoutDuration = timeoutMs;
        this.timerStarted = false;
    }

    public void setTimeout(long durationMs) {
        this.timeoutDuration = durationMs;
        System.out.println("Timeout set to " + durationMs + " ms");
    }

    /**
     * Starts the timeout timer.
     */
    public void startTimer() {
        this.startTime = System.currentTimeMillis();
        this.timerStarted = true;
        System.out.println("Timeout timer started.");
    }

    /**
     * Checks if timeout has occurred.
     */
    public boolean checkTimeout() {
        if (!timerStarted) {
            return false;
        }
        long elapsed = System.currentTimeMillis() - startTime;
        return elapsed > timeoutDuration;
    }

    /**
     * Triggers timeout action.
     */
    public void triggerTimeout() {
        System.out.println("Timeout triggered after " + timeoutDuration + " ms");
    }
    
    public void acknowledged() {
        System.out.println("Timeout Manager acknowledged.");
    }
}