/*
This service class simulates the connection status to an external SMOS server.
It provides a mechanism to check and toggle the connection status,
which is crucial for simulating the "Connection to the SMOS server interrupted"
postcondition in the use case.
*/
package com.chatdev.eliminatejustification.serv;
public class SMOSConnectionService {
    private boolean smosConnected;
    /**
     * Constructs a new SMOSConnectionService.
     * By default, the SMOS server is considered connected at application start.
     */
    public SMOSConnectionService() {
        this.smosConnected = true; // Assume connected by default
    }
    /**
     * Checks if the system is currently connected to the SMOS server.
     * @return true if connected, false otherwise.
     */
    public boolean isSMOSConnected() {
        return smosConnected;
    }
    /**
     * Simulates connecting to the SMOS server.
     */
    public void connectSMOS() {
        this.smosConnected = true;
        System.out.println("DEBUG: SMOS connection established.");
    }
    /**
     * Simulates disconnecting from the SMOS server.
     */
    public void disconnectSMOS() {
        this.smosConnected = false;
        System.out.println("DEBUG: SMOS connection interrupted.");
    }
}