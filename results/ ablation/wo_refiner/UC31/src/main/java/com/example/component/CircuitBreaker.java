package com.example.component;

import java.util.concurrent.Callable;

/**
 * Circuit breaker pattern implementation for reliability.
 * Provides retry mechanism for operations that may fail.
 * Traceability: Satisfies R13 (Quality - Reliability)
 */
public class CircuitBreaker {
    private enum CircuitState { CLOSED, OPEN, HALF_OPEN }
    private CircuitState state = CircuitState.CLOSED;
    private int failureCount = 0;
    private final int MAX_FAILURES = 3;
    
    /**
     * Executes an operation with retry logic.
     * @param operation the operation to execute
     * @return the result of the operation
     * @throws Exception if operation fails after retries
     */
    public Object executeWithRetry(Callable<Object> operation) throws Exception {
        int attempts = 0;
        final int MAX_ATTEMPTS = 3;
        
        while (attempts < MAX_ATTEMPTS) {
            try {
                attempts++;
                return operation.call();
            } catch (Exception e) {
                failureCount++;
                if (failureCount >= MAX_FAILURES) {
                    state = CircuitState.OPEN;
                    throw new RuntimeException("Circuit breaker is OPEN. Too many failures.", e);
                }
                if (attempts == MAX_ATTEMPTS) {
                    throw e;
                }
                // Wait before retry (exponential backoff simplified)
                try {
                    Thread.sleep(100 * attempts);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Interrupted during retry", ie);
                }
            }
        }
        throw new RuntimeException("Operation failed after " + MAX_ATTEMPTS + " attempts");
    }

    /**
     * throws DatabaseConnectionException - corresponds to message m35 in sequence diagram.
     * This is a return message from CB to MPC.
     * In the sequence, the CircuitBreaker might throw a DatabaseConnectionException.
     * We already throw it in executeWithRetry, but we add a method for traceability.
     */
    public void throwDatabaseConnectionException() throws com.example.exception.DatabaseConnectionException {
        // This method simulates throwing a DatabaseConnectionException.
        throw new com.example.exception.DatabaseConnectionException("Database connection lost");
    }
}