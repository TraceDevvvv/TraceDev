package com.example.util;

/**
 * Interface for objects that can be cancelled.
 * Added to satisfy requirement Exit Conditions: User cancels connection interruption to SMOS server.
 * Although PasswordChangeController uses this conceptually, it doesn't directly implement it in this context
 * but rather has a specific cancelOperation method. This interface might be used for other cancellable processes.
 */
public interface ICancellable {
    /**
     * Performs cancellation logic.
     */
    void cancel();
}