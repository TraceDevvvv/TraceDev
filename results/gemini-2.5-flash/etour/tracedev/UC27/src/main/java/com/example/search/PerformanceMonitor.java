package com.example.search;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Monitors the performance of operations by timing their execution.
 * This class directly maps to the 'PerformanceMonitor' class in the UML diagram.
 * Added to satisfy requirement R11.
 */
public class PerformanceMonitor {
    // Stores the start times of operations using their names as keys.
    private final Map<String, Long> startTimes;

    public PerformanceMonitor() {
        this.startTimes = new ConcurrentHashMap<>();
    }

    /**
     * Records the start time for a given operation.
     * @param operationName The name of the operation to monitor.
     */
    public void startTimer(String operationName) {
        System.out.println("      [PerformanceMonitor] startTimer(\"" + operationName + "\")");
        startTimes.put(operationName, System.nanoTime());
    }

    /**
     * Stops the timer for a given operation and returns its duration in milliseconds.
     * @param operationName The name of the operation to stop monitoring.
     * @return The duration of the operation in milliseconds. Returns -1 if the operation was not started.
     */
    public long stopTimer(String operationName) {
        Long startTime = startTimes.remove(operationName);
        if (startTime != null) {
            long durationNanos = System.nanoTime() - startTime;
            long durationMillis = durationNanos / 1_000_000;
            System.out.println("      [PerformanceMonitor] stopTimer(\"" + operationName + "\") : duration " + durationMillis + "ms");
            return durationMillis;
        }
        System.err.println("Warning: Timer for operation '" + operationName + "' was stopped without being started.");
        return -1; // Indicate that timer was not started
    }

    /**
     * Checks if a given duration exceeds a specified maximum time limit.
     * @param duration The actual duration of an operation in milliseconds.
     * @param maxTime The maximum allowed duration in milliseconds.
     * @return true if duration > maxTime, false otherwise.
     */
    public boolean checkTimeout(long duration, long maxTime) {
        boolean timedOut = duration > maxTime;
        System.out.println("      [PerformanceMonitor] checkTimeout(duration=" + duration + "ms, maxTime=" + maxTime + "ms): " + timedOut);
        return timedOut;
    }
}