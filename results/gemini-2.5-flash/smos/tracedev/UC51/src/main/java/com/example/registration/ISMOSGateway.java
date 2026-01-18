package com.example.registration;

/**
 * Interface defining the contract for interacting with the SMOS (Student Management Online System) Gateway.
 */
public interface ISMOSGateway {

    /**
     * Initiates an interruption or disconnection of the connection to the SMOS system.
     */
    void interruptSMOSConnection();
}