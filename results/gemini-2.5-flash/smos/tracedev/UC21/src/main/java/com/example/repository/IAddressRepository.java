package com.example.repository;

import com.example.model.Address;

/**
 * Interface for repository operations related to Address entities.
 * Defines standard CRUD operations for Address.
 */
public interface IAddressRepository {
    /**
     * Finds an Address by its unique identifier.
     *
     * @param addressId The ID of the address to find.
     * @return The Address object if found, otherwise null.
     */
    Address findById(String addressId);

    /**
     * Saves or updates an Address entity.
     * If the address with the given ID already exists, it is updated. Otherwise, it is added.
     *
     * @param address The Address object to save.
     */
    void save(Address address);
}