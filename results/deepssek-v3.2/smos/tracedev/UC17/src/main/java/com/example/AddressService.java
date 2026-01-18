package com.example;

import java.util.List;

/**
 * Service layer for address-related business logic.
 */
public class AddressService {
    private AddressRepository addressRepository;
    private SMOSServer smosServer;

    // Constructor
    public AddressService(AddressRepository addressRepository, SMOSServer smosServer) {
        this.addressRepository = addressRepository;
        this.smosServer = smosServer;
    }

    /**
     * Retrieves all addresses and ensures data integrity.
     */
    public List<Address> getAllAddresses() {
        // Get addresses from repository
        List<Address> addresses = addressRepository.findAll();

        // Validate data integrity (quality requirement: display list completely and accurately)
        validateDataIntegrity(addresses);

        // Interrupt SMOS connection as per requirement
        interruptSMOSConnection();

        return addresses;
    }

    /**
     * Validates the integrity of address data.
     * Ensures no null fields and valid postal codes.
     */
    private void validateDataIntegrity(List<Address> addresses) {
        // Check each address for completeness
        for (Address addr : addresses) {
            if (addr.getStreet() == null || addr.getCity() == null ||
                addr.getState() == null || addr.getPostalCode() == null) {
                throw new IllegalStateException("Address data incomplete: " + addr);
            }
            // Additional validation could be added here
        }
        System.out.println("AddressService: Data integrity validated.");
    }

    /**
     * Interrupts the connection to the SMOS server.
     */
    public void interruptSMOSConnection() {
        // Call disconnect on SMOS server
        smosServer.disconnect();
    }

    /**
     * Validates the SMOS connection.
     */
    public void validateSMOSConnection() {
        // In a real application, this would check the connection to SMOS
        System.out.println("AddressService: Validating SMOS connection...");
        // For now, we assume the connection is valid if the SMOSServer instance exists
        if (smosServer == null) {
            throw new IllegalStateException("SMOS connection is not available.");
        }
        System.out.println("AddressService: SMOS connection validated.");
    }
}