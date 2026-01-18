package com.example.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages transaction locks to block input controls.
 * REQ-015 and REQ-016: Input blocking
 */
public class TransactionLock {
    private Map<String, LockEntry> locks = new HashMap<>();

    private static class LockEntry {
        boolean isLocked;
        String lockId;
        LocalDateTime timestamp;

        LockEntry(String lockId) {
            this.isLocked = true;
            this.lockId = lockId;
            this.timestamp = LocalDateTime.now();
        }
    }

    /**
     * Blocks input controls for a given operation.
     * @param operationId the operation identifier
     */
    public void blockInputControls(String operationId) {
        locks.put(operationId, new LockEntry(operationId));
        System.out.println("Input controls blocked for operation: " + operationId);
    }

    /**
     * Releases the lock for a given operation.
     * @param operationId the operation identifier
     */
    public void releaseLock(String operationId) {
        locks.remove(operationId);
        System.out.println("Lock released for operation: " + operationId);
    }

    /**
     * Checks if an operation is locked.
     * @param operationId the operation identifier
     * @return true if locked
     */
    public boolean isLocked(String operationId) {
        return locks.containsKey(operationId);
    }
}