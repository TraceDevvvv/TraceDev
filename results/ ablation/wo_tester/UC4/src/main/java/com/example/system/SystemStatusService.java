package com.example.system;

/**
 * Interface for checking system availability.
 * Entry Condition: System IS available.
 */
public interface SystemStatusService {
    /**
     * Checks if the system is available for use.
     * @return true if the system is available, false otherwise.
     */
    boolean checkSystemAvailability();
}