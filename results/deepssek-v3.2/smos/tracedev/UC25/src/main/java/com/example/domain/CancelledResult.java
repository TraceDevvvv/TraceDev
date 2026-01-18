package com.example.domain;

/**
 * Result indicating that an operation was cancelled.
 */
public class CancelledResult {
    private final boolean cancelled;

    public CancelledResult(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public boolean isCancelled() {
        return cancelled;
    }
}