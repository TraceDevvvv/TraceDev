package com.example.repository;

import com.example.model.Address;

/**
 * Repository interface for Address entities.
 * Based on the UML class diagram.
 */
public interface AddressRepository {
    Address findById(String addressId);
    boolean save(Address address);
}