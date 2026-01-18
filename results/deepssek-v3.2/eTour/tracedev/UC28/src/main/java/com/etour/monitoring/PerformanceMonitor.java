package com.etour.monitoring;

/**
 * Monitors performance to ensure operations complete within a given time.
 * Implements quality requirement: 5-second completion.
 */
public class PerformanceMonitor {
    /**
     * Checks if the given duration exceeds the allowed timeout.
     * @param durationMillis duration in milliseconds
     * @return true if duration exceeds timeout (5 seconds), false otherwise
     */
    public boolean checkTimeout(long durationMillis) {
        long timeoutMillis = 5000; // 5 seconds
        return durationMillis > timeoutMillis;
    }
}