package com.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Manages data access operations for Address and Order entities.
 * Simulates a database by using in-memory lists.
 * This class includes logic to simulate connection failures for testing purposes (REQ-10).
 */
public class AddressRepository {
    // Using CopyOnWriteArrayList for thread-safety if multiple threads were to access it,
    // though for this simulation, ArrayList would suffice.
    private List<Address> addresses = new CopyOnWriteArrayList<>();
    private List<Order> orders = new CopyOnWriteArrayList<>();

    // A flag to simulate connection failures for testing (REQ-10)
    private boolean simulateConnectionFailure = false;

    public AddressRepository() {
        // Initialize with some sample data
        addresses.add(new Address("A001", "123 Main St", "Anytown", "12345"));
        addresses.add(new Address("A002", "456 Oak Ave", "Otherville", "67890"));
        addresses.add(new Address("A003", "789 Pine Ln", "Smallburg", "10112"));

        orders.add(new Order("O001", "A001", new Date())); // Order for A001
        orders.add(new Order("O002", "A001", new Date())); // Another order for A001
        orders.add(new Order("O003", "A002", new Date())); // Order for A002
    }

    /**
     * Sets the flag to simulate a connection failure.
     * @param simulate true to simulate failure, false otherwise.
     */
    public void setSimulateConnectionFailure(boolean simulate) {
        this.simulateConnectionFailure = simulate;
    }

    /**
     * Checks if there are any associated classes (like Orders) for a given address ID.
     * Satisfies the business rule in the sequence diagram.
     *
     * @param addressId The ID of the address to check.
     * @return true if associated classes are found, false otherwise.
     * @throws ConnectionFailedException if a database connection error occurs (REQ-10).
     */
    public boolean hasAssociatedClasses(String addressId) throws ConnectionFailedException {
        System.out.println("Repository: Checking for associated classes for addressId: " + addressId);
        if (simulateConnectionFailure) {
            System.err.println("Repository: Simulating DB connection failure for hasAssociatedClasses.");
            throw new ConnectionFailedException("Failed to connect to DB during hasAssociatedClasses check.");
        }

        // Simulate querying the database for associated entities (e.g., Orders)
        for (Order order : orders) {
            if (order.getAddressId().equals(addressId)) {
                System.out.println("Repository: Found associated order: " + order.getId() + " for addressId: " + addressId);
                return true;
            }
        }
        System.out.println("Repository: No associated classes found for addressId: " + addressId);
        return false;
    }

    /**
     * Deletes an address by its ID from the simulated database.
     *
     * @param addressId The ID of the address to delete.
     * @return true if the address was successfully deleted, false if not found.
     * @throws ConnectionFailedException if a database connection error occurs (REQ-10).
     */
    public boolean deleteAddressById(String addressId) throws ConnectionFailedException {
        System.out.println("Repository: Attempting to delete address with ID: " + addressId);
        if (simulateConnectionFailure) {
            System.err.println("Repository: Simulating DB connection failure for delete operation.");
            throw new ConnectionFailedException("Failed to connect to DB during delete operation.");
        }

        Iterator<Address> iterator = addresses.iterator();
        while (iterator.hasNext()) {
            Address address = iterator.next();
            if (address.getId().equals(addressId)) {
                addresses.remove(address); // remove method of CopyOnWriteArrayList
                System.out.println("Repository: Successfully deleted address with ID: " + addressId);
                return true;
            }
        }
        System.out.println("Repository: Address with ID: " + addressId + " not found for deletion.");
        return false;
    }

    /**
     * Retrieves all addresses from the simulated database.
     *
     * @return A list of all addresses.
     * @throws ConnectionFailedException if a database connection error occurs (REQ-10).
     */
    public List<Address> queryAllAddresses() throws ConnectionFailedException {
        System.out.println("Repository: Retrieving all addresses.");
        if (simulateConnectionFailure) {
            System.err.println("Repository: Simulating DB connection failure for queryAllAddresses.");
            throw new ConnectionFailedException("Failed to connect to DB during queryAllAddresses.");
        }
        // Return a new ArrayList to ensure immutability of the internal list
        return new ArrayList<>(addresses);
    }
}