package com.ata.service;

/**
 * Service for authentication checks as per class diagram.
 * Validates user login and ATA staff status.
 * Assumption: For simplicity, we simulate authentication.
 * In a real implementation, database validation would be performed.
 */
public class AuthenticationService {
    /**
     * Checks if the user is logged in.
     * @return true if logged in (simulated)
     */
    public boolean isLoggedIn() {
        // Simulated authentication; real implementation would check session/token.
        return true;
    }

    /**
     * Checks if the user is an ATA staff member.
     * @return true if ATA staff (simulated)
     */
    public boolean isATAStaff() {
        // Simulated authorization; real implementation would check role in database.
        return true;
    }
}