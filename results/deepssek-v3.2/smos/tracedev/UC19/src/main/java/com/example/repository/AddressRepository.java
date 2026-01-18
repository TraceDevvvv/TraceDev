package com.example.repository;

import com.example.dto.AddressDetailDTO;
import com.example.external.SMOSServer;

/**
 * Repository for accessing address details, with a connection to external SMOS server.
 */
public class AddressRepository {
    private SMOSServer smosServer;

    // Constructor
    public AddressRepository() {
        this.smosServer = new SMOSServer();
    }

    /**
     * Finds address details by ID.
     * Sequence: Called by ViewAddressDetailsController.
     */
    public AddressDetailDTO findById(Long id) {
        try {
            // Fetch from SMOS server as per class diagram
            AddressDetailDTO dto = fetchFromSMOSServer(id);
            return dto;
        } catch (RuntimeException e) {
            // Connection interrupted or other error
            handleConnectionError();
            return null; // Returns null/error as per sequence diagram
        }
    }

    /**
     * Fetches address details from the external SMOS server.
     * Sequence: Called by findById.
     */
    protected AddressDetailDTO fetchFromSMOSServer(Long id) {
        // Delegate to external server
        return smosServer.fetchAddressData(id);
    }

    /**
     * Handles connection errors to SMOS server.
     * Sequence: Called when connection is interrupted.
     */
    protected void handleConnectionError() {
        // Log the connection error
        System.err.println("Connection to SMOS server interrupted.");
        // Possibly retry or notify monitoring system in a real implementation.
    }

    // Added method to represent the return message m7: returns AddressDetailDTO
    // This is already covered by findById method, but added for traceability.
    public AddressDetailDTO returnsAddressDetailDTO(Long addressId) {
        return findById(addressId);
    }

    // Added method to represent the return message m15: returns null/error
    // This is already covered by findById method (returns null on error), but added for traceability.
    public AddressDetailDTO returnsNullOrError(Long addressId) {
        return findById(addressId);
    }
}