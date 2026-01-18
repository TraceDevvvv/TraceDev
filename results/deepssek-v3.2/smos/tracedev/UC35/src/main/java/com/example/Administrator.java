package com.example;

/**
 * Represents an Administrator user.
 * Supports requirement R2 - Exit Condition: connection interruption.
 */
public class Administrator {

    /**
     * Logs out the administrator by disconnecting from the SMOSConnection.
     *
     * @param connection the SMOSConnection to disconnect from
     */
    public void logout(SMOSConnection connection) {
        // Requirement R2: administrator can interrupt connection to SMOS server
        if (connection != null) {
            connection.disconnect();
        }
    }
}