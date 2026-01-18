package com.example.repository;

import com.example.model.Address;

import java.util.HashMap;
import java.util.Map;

/**
 * Concrete implementation of IAddressRepository using an in-memory HashMap as a data store.
 * This simulates database persistence for demonstration purposes.
 */
public class AddressRepository implements IAddressRepository {
    // In-memory data store for addresses, mapping addressId to Address object
    private final Map<String, Address> dataStore = new HashMap<>();

    public AddressRepository() {
        // Populate with some initial data for demonstration
        Address address1 = new Address("addr001", "Headquarters", "123 Main St, Anytown");
        address1.addTeaching(new com.example.model.Teaching("t101", "Introduction to Java", "Basic Java concepts"));
        address1.addTeaching(new com.example.model.Teaching("t102", "Advanced SQL", "Complex database queries"));
        dataStore.put(address1.getId(), address1);

        Address address2 = new Address("addr002", "Branch Office", "456 Oak Ave, Otherville");
        dataStore.put(address2.getId(), address2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Address findById(String addressId) {
        // Simulate a database lookup. If not found, returns null.
        System.out.println("AddressRepo: Querying DB for Address ID: " + addressId);
        return dataStore.get(addressId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Address address) {
        // Simulate saving/updating an address in the database.
        if (address != null) {
            dataStore.put(address.getId(), address);
            System.out.println("AddressRepo: Saved/Updated Address ID: " + address.getId() + " - " + address.getName());
        }
    }
}