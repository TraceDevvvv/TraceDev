package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete repository for Address entities.
 */
public class AddressRepository implements Repository<Address> {
    private DataSource dataSource;

    // Constructor
    public AddressRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Retrieves all addresses from the data source.
     */
    @Override
    public List<Address> findAll() {
        // Simulating query to the database/archive
        // In a real application this would use the DataSource to query the DB
        // For demonstration, we return a hardcoded list
        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address(1, "123 Main St", "Springfield", "IL", "62704"));
        addresses.add(new Address(2, "456 Oak Ave", "Shelbyville", "IL", "62565"));
        return addresses;
    }
}