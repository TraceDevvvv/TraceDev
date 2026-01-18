package com.example;

/**
 * Implementation of ISecurityService for secure data operations.
 * Implements ISecurityService to satisfy requirement R10.
 */
public class SecurityServiceImpl implements ISecurityService {
    // Assumption: For this example, we'll simulate secure erasure status.
    private boolean eraseSucceeds = true; // Default to success

    @Override
    public boolean securelyEraseData(String userId) {
        System.out.println("SecurityServiceImpl: Attempting to securely erase data for userId: " + userId);
        // Simulate secure erasure logic
        if (eraseSucceeds) {
            System.out.println("SecurityServiceImpl: Data for userId " + userId + " securely erased successfully.");
            return true;
        } else {
            System.out.println("SecurityServiceImpl: Secure data erasure failed for userId: " + userId);
            return false;
        }
    }

    /**
     * Helper method to simulate secure erase failure for testing purposes.
     * Not part of the UML, but useful for demonstrating sequence diagram paths.
     * @param fail If true, secure erase will report as failed.
     */
    public void simulateEraseFailure(boolean fail) {
        this.eraseSucceeds = !fail;
    }
}