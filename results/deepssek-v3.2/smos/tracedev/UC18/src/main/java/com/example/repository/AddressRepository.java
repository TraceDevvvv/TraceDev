package com.example.repository;

import com.example.model.Address;

/**
 * Repository interface for Address persistence.
 */
public interface AddressRepository {
    Address save(Address address);
}