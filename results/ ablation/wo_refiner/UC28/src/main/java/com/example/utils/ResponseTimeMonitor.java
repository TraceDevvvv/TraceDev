package com.example.utils;

/**
 * Utility class for monitoring performance.
 * Added to satisfy requirement REQ-012
 */
public class ResponseTimeMonitor {
    private static ResponseTimeMonitor instance;

    private ResponseTimeMonitor() {
    }

    public static ResponseTimeMonitor getInstance() {
        if (instance == null) {
            instance = new ResponseTimeMonitor();
        }
        return instance;
    }

    /**
     * Measures operation time.
     * @param operation operation name
     * @param startTime start time in milliseconds
     */
    public void measureOperationTime(String operation, long startTime) {
        long duration = System.currentTimeMillis() - startTime;
        logPerformanceMetric(operation + "_duration_ms", duration);
    }

    public void logPerformanceMetric(String metric, long value) {
        System.out.println("[PERF] " + metric + " = " + value + " ms");
    }

    /**
     * Starts a timer for an operation (returns current time as start).
     * @param operation operation name
     * @return current time in milliseconds
     */
    public long startTimer(String operation) {
        return System.currentTimeMillis();
    }
}