/*
Represents an interface for interacting with an external SMOS server.
This abstraction allows for different implementations (e.g., mock for testing, real for production).
*/
package com.chatdev.deleteuser;
public interface SMOSAdapter {
    /**
     * Simulates disconnecting a user from the SMOS server.
     * In a real application, this would interact with the actual SMOS server
     * to sever any active connections or remove the user's presence from SMOS.
     * @param userId The ID of the user to disconnect.
     * @param userName The name of the user associated with the disconnection.
     */
    void disconnectUser(String userId, String userName);
}