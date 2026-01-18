package com.example.addressapp;

import java.util.List;

/**
 * Concrete implementation of IAddressRepository using SMOSServerAdapter.
 */
public class AddressRepository implements IAddressRepository {
    private SMOSServerAdapter smosServerAdapter;

    /**
     * Constructs an AddressRepository with a given SMOSServerAdapter.
     * @param smosServerAdapter The adapter for external data access.
     */
    public AddressRepository(SMOSServerAdapter smosServerAdapter) {
        this.smosServerAdapter = smosServerAdapter;
    }

    /**
     * Finds an address by its ID by querying the SMOS server.
     * This method implements the interaction with SMOSServerAdapter as per EX2.
     * @param addressId The ID of the address to find.
     * @return The Address object if found and connection is successful, null otherwise.
     */
    @Override
    public Address findById(String addressId) {
        System.out.println("AddressRepository: Attempting to find address with ID: " + addressId);

        // Attempt to connect to SMOS server
        if (!smosServerAdapter.connect()) {
            System.err.println("AddressRepository: Failed to connect to SMOS server.");
            // Propagate the error or return null as per sequence diagram's error path
            return null;
        }

        // Fetch data if connected
        String query = "addressId=" + addressId;
        List<Object> data = smosServerAdapter.fetchData(query);

        if (data != null && !data.isEmpty()) {
            // Assuming fetchData returns a list where the first element is the Address
            Object firstElement = data.get(0);
            if (firstElement instanceof Address) {
                System.out.println("AddressRepository: Address found: " + ((Address) firstElement).getId());
                return (Address) firstElement;
            }
        }

        System.out.println("AddressRepository: Address with ID " + addressId + " not found in SMOS server.");
        return null; // Address not found or data format unexpected
    }
}