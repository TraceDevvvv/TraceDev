package com.etour.infrastructure;

import com.etour.infrastructure.exception.ConnectionError;
import java.util.concurrent.Callable;

/**
 * Handles connection reliability and retry logic (REQ-010).
 */
public class ConnectionManager {
    /**
     * Checks if a connection is available.
     * @return true if connection is available, false otherwise.
     */
    public boolean isConnectionAvailable() {
        // Simplified: assume connection is always available for demonstration.
        return true;
    }

    /**
     * Retries an operation on failure up to a maximum number of retries.
     * @param operation The operation to retry (as a Callable)
     * @param maxRetries Maximum number of retry attempts
     * @return The result of the operation
     * @throws ConnectionError if all retries fail
     */
    public Object retryOnFailure(Callable<Object> operation, int maxRetries) throws ConnectionError {
        int attempts = 0;
        while (attempts < maxRetries) {
            try {
                return operation.call();
            } catch (Exception e) {
                attempts++;
                if (attempts >= maxRetries) {
                    throw new ConnectionError("Operation failed after " + maxRetries + " retries: " + e.getMessage());
                }
                // Wait before retrying (simulated)
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new ConnectionError("Retry interrupted");
                }
            }
        }
        throw new ConnectionError("Unexpected error in retry logic");
    }
}