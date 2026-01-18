package com.example.addressapp;

/**
 * Interface for Address data access operations.
 */
public interface IAddressRepository {
    /**
     * Finds an address by its ID.
     * @param addressId The ID of the address to find.
     * @return The Address object if found, null otherwise.
     */
    Address findById(String addressId);
}