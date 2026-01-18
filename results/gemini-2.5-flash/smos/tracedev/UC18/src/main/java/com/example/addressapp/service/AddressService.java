package com.example.addressapp.service;

import com.example.addressapp.model.Address;
import com.example.addressapp.infrastructure.AddressRepository;
import com.example.addressapp.infrastructure.SMOSConnectionException;

/**
 * Service layer for Address-related operations.
 * This class orchestrates business logic related to addresses,
 * using the AddressRepository for persistence.
 */
public class AddressService {
    private final AddressRepository addressRepository; // Dependency on AddressRepository

    /**
     * Constructs an AddressService with a given AddressRepository.
     * @param repository The repository responsible for address persistence.
     */
    public AddressService(AddressRepository repository) {
        this.addressRepository = repository;
    }

    /**
     * Creates a new Address with the given name and persists it.
     *
     * @param name The name for the new address.
     * @return The newly created and persisted Address object.
     * @throws SMOSConnectionException If there's an error during the persistence operation.
     */
    public Address createAddress(String name) throws SMOSConnectionException {
        System.out.println("[Service] Creating address with name: " + name);
        Address newAddress = new Address(name); // Create new Address object
        addressRepository.save(newAddress);     // Delegate saving to the repository
        System.out.println("[Service] Address created and saved: " + newAddress);
        return newAddress;
    }
}