package com.example;

import java.util.List;

/**
 * The application service layer for Address operations.
 * It orchestrates business logic and interacts with the AddressRepository.
 * Handles `ConnectionFailedException` by re-throwing it to the UI layer (REQ-10).\
 */
public class AddressService {
    private AddressRepository addressRepository;

    /**
     * Constructs an AddressService with a given AddressRepository.
     * @param repository The data access object for addresses.
     */
    public AddressService(AddressRepository repository) {
        this.addressRepository = repository;
    }

    /**
     * Attempts to delete an address after checking for associated classes.
     * Implements the core business logic from the sequence diagram.
     *
     * @param addressId The ID of the address to delete.
     * @return true if the address was successfully deleted, false if associated classes were found.
     * @throws ConnectionFailedException if a connection error occurs during repository operations (REQ-10).\
     */
    public boolean deleteAddress(String addressId) throws ConnectionFailedException {
        System.out.println("AddressService: Attempting to delete address: " + addressId);
        try {
            // Step 1: Check for associated classes (business rule)
            boolean hasAssociated = addressRepository.hasAssociatedClasses(addressId);
            if (hasAssociated) {
                System.out.println("AddressService: Cannot delete address " + addressId + ". Associated classes found.");
                return false; // Business rule: cannot delete if associated classes exist
            }

            // Step 2: If no associated classes, proceed with deletion
            boolean deletionSuccessful = addressRepository.deleteAddressById(addressId);
            if (deletionSuccessful) {
                System.out.println("AddressService: Address " + addressId + " successfully deleted.");
            } else {
                System.out.println("AddressService: Address " + addressId + " not found for deletion.");
            }
            return deletionSuccessful;

        } catch (ConnectionFailedException e) {
            // REQ-10: Catch and re-throw connection exceptions
            System.err.println("AddressService: Connection failed during deleteAddress for ID " + addressId + ". Message: " + e.getMessage());
            throw e; // Re-throw to UserInterface
        }
    }

    /**
     * Retrieves all addresses.
     *
     * @return A list of all addresses.
     * @throws ConnectionFailedException if a connection error occurs during repository operations (REQ-10).
     */
    public List<Address> queryAllAddresses() throws ConnectionFailedException {
        System.out.println("AddressService: Retrieving all addresses.");
        try {
            return addressRepository.queryAllAddresses();
        } catch (ConnectionFailedException e) {
            // REQ-10: Catch and re-throw connection exceptions
            System.err.println("AddressService: Connection failed during queryAllAddresses. Message: " + e.getMessage());
            throw e; // Re-throw to UserInterface
        }
    }
}