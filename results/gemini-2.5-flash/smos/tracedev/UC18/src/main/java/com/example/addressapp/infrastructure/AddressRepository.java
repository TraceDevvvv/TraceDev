package com.example.addressapp.infrastructure;

import com.example.addressapp.model.Address;

/**
 * Interface for Address data persistence.
 * Defines the contract for saving Address objects, abstracting the underlying storage mechanism.
 */
public interface AddressRepository {

    /**
     * Saves an Address object to the persistence layer.
     * If the address already exists (e.g., has an ID), it might be updated; otherwise, it's created.
     *
     * @param address The Address object to be saved.
     * @throws SMOSConnectionException If there is a problem connecting to the SMOS server (e.g., database).
     */
    void save(Address address) throws SMOSConnectionException;
}