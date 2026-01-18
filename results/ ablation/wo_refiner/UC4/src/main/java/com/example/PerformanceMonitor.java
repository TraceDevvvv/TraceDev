package com.example;

// Service class to monitor performance and response times (R13)
public class PerformanceMonitor {
    private long maxTimeoutMs = 3000; // R13: Response time must be ≤ 3000ms
    private long currentResponseTime;
    private long startTime;

    public PerformanceMonitor() {
    }

    public PerformanceMonitor(long maxTimeoutMs) {
        this.maxTimeoutMs = maxTimeoutMs;
    }

    public long getMaxTimeoutMs() {
        return maxTimeoutMs;
    }

    public void setMaxTimeoutMs(long maxTimeoutMs) {
        this.maxTimeoutMs = maxTimeoutMs;
    }

    public long getCurrentResponseTime() {
        return currentResponseTime;
    }

    public void setCurrentResponseTime(long currentResponseTime) {
        this.currentResponseTime = currentResponseTime;
    }

    // Starts timing for performance monitoring
    public void startTiming() {
        this.startTime = System.currentTimeMillis();
    }

    // Records the response time since startTiming was called
    public void recordResponseTime() {
        this.currentResponseTime = System.currentTimeMillis() - startTime;
    }

    // Checks if the response time is within the timeout limit
    public boolean isWithinTimeout() {
        return currentResponseTime <= maxTimeoutMs;
    }

    // Returns the elapsed time since startTiming was called
    public long getElapsedTime() {
        return System.currentTimeMillis() - startTime;
    }

    // Records a specific response time
    public void recordResponseTime(long time) {
        this.currentResponseTime = time;
    }
}