package com.example;

/**
 * Interface for security-related operations, like secure data erasure.
 * Added to satisfy requirement R10.
 */
public interface ISecurityService {
    /**
     * Securely erases data associated with a specific user.
     * @param userId The ID of the user whose data needs to be erased.
     * @return true if data was securely erased, false otherwise.
     */
    boolean securelyEraseData(String userId);
}