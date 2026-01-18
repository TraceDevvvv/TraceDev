/*
A mock implementation of the SMOSAdapter interface for simulation purposes.
It prints a log message without directly interacting with the UI.
*/
package com.chatdev.deleteuser;
// No specific JavaFX imports needed here anymore
public class MockSMOSAdapter implements SMOSAdapter {
    /**
     * Simulates disconnecting a user from the SMOS server by logging the event.
     * The UI notification will be handled by the calling application.
     * @param userId The ID of the user to disconnect.
     * @param userName The name of the user to disconnect.
     */
    @Override
    public void disconnectUser(String userId, String userName) {
        System.out.println("LOG: Connection to SMOS server for user " + userId + " interrupted (simulated).");
        // UI interaction for this event will be handled by DeleteUserApp.
    }
}