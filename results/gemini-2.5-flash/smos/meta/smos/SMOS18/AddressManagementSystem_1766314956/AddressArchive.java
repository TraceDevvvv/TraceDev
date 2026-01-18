package com.address_management_system;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Manages the storage and retrieval of Address objects.
 * This class acts as an archive for addresses, ensuring that each address name is unique.
 */
public class AddressArchive {
    private static final Logger LOGGER = Logger.getLogger(AddressArchive.class.getName());
    private final Set<Address> addresses;

    /**
     * Constructs a new AddressArchive.
     * Initializes an empty set to store addresses.
     */
    public AddressArchive() {
        this.addresses = new HashSet<>();
        LOGGER.log(Level.INFO, "AddressArchive initialized.");
    }

    /**
     * Adds a new address to the archive.
     * Before adding, it checks if an address with the same name already exists.
     *
     * @param newAddress The Address object to be added.
     * @return true if the address was successfully added (i.e., it was not a duplicate), false otherwise.
     * @throws IllegalArgumentException if the newAddress is null.
     */
    public boolean addAddress(Address newAddress) {
        if (newAddress == null) {
            LOGGER.log(Level.WARNING, "Attempted to add a null address to the archive.");
            throw new IllegalArgumentException("Address to be added cannot be null.");
        }

        // Check if an address with the same name already exists
        if (addresses.contains(newAddress)) {
            LOGGER.log(Level.INFO, "Address '{0}' already exists in the archive. Not adding.", newAddress.getName());
            return false; // Address already exists
        }

        addresses.add(newAddress);
        LOGGER.log(Level.INFO, "Address '{0}' successfully added to the archive.", newAddress.getName());
        return true; // Address added successfully
    }

    /**
     * Checks if an address with the given name exists in the archive.
     *
     * @param addressName The name of the address to check.
     * @return true if an address with the given name exists, false otherwise.
     */
    public boolean addressExists(String addressName) {
        if (addressName == null || addressName.trim().isEmpty()) {
            LOGGER.log(Level.WARNING, "Attempted to check for an address with a null or empty name.");
            return false;
        }
        // Create a temporary Address object for comparison based on name
        // This relies on the Address.equals() method which compares by name
        Address tempAddress = new Address(addressName);
        boolean exists = addresses.contains(tempAddress);
        LOGGER.log(Level.FINE, "Checking if address '{0}' exists: {1}", new Object[]{addressName, exists});
        return exists;
    }

    /**
     * Returns an unmodifiable set of all addresses currently in the archive.
     *
     * @return A Set of Address objects.
     */
    public Set<Address> getAllAddresses() {
        LOGGER.log(Level.FINE, "Retrieving all addresses from the archive. Count: {0}", addresses.size());
        return Collections.unmodifiableSet(addresses);
    }

    /**
     * Returns the number of addresses currently stored in the archive.
     *
     * @return The total count of addresses.
     */
    public int getAddressCount() {
        return addresses.size();
    }
}