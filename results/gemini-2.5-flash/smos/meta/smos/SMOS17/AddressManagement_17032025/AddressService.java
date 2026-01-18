package com.addressmanagement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Service class responsible for managing addresses and simulating interaction
 * with an external SMOS server.
 */
public class AddressService {
    private List<Address> addressArchive;
    private boolean isSMOSConnected;

    /**
     * Constructs a new AddressService.
     * Initializes the address archive with some dummy data and sets the SMOS connection status to false.
     */
    public AddressService() {
        this.addressArchive = new ArrayList<>();
        // Populate with some sample addresses for demonstration
        addressArchive.add(new Address("123 Main St", "Anytown", "CA", "90210"));
        addressArchive.add(new Address("456 Oak Ave", "Otherville", "NY", "10001"));
        addressArchive.add(new Address("789 Pine Ln", "Somewhere", "TX", "73301"));
        this.isSMOSConnected = false; // Initially not connected
    }

    /**
     * Simulates connecting to the SMOS server.
     * In a real application, this would involve network calls, authentication, etc.
     */
    public void connectToSMOS() {
        if (!isSMOSConnected) {
            System.out.println("Attempting to connect to SMOS server...");
            // Simulate connection delay or process
            try {
                Thread.sleep(500); // Simulate network latency
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("SMOS connection interrupted: " + e.getMessage());
            }
            this.isSMOSConnected = true;
            System.out.println("Successfully connected to SMOS server.");
        } else {
            System.out.println("Already connected to SMOS server.");
        }
    }

    /**
     * Simulates disconnecting from the SMOS server.
     * This fulfills the postcondition "Connection to the SMOS server interrupted".
     */
    public void disconnectFromSMOS() {
        if (isSMOSConnected) {
            System.out.println("Disconnecting from SMOS server...");
            // Simulate disconnection process
            try {
                Thread.sleep(200); // Simulate network latency
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("SMOS disconnection interrupted: " + e.getMessage());
            }
            this.isSMOSConnected = false;
            System.out.println("Connection to the SMOS server interrupted.");
        } else {
            System.out.println("SMOS server is already disconnected.");
        }
    }

    /**
     * Checks if the service is currently connected to the SMOS server.
     *
     * @return true if connected, false otherwise.
     */
    public boolean isSMOSConnected() {
        return isSMOSConnected;
    }

    /**
     * Retrieves the list of addresses from the archive.
     * This method returns an unmodifiable list to prevent external modification
     * of the internal address archive.
     *
     * @return An unmodifiable list of Address objects.
     */
    public List<Address> getAddresses() {
        // In a real scenario, this might fetch data from the SMOS server if connected,
        // or from a local cache/database. For this use case, we return from the archive.
        System.out.println("Retrieving address list from archive.");
        return Collections.unmodifiableList(addressArchive);
    }

    /**
     * Adds a new address to the archive.
     *
     * @param address The Address object to add.
     */
    public void addAddress(Address address) {
        if (address != null) {
            this.addressArchive.add(address);
            System.out.println("Address added: " + address);
        }
    }
}