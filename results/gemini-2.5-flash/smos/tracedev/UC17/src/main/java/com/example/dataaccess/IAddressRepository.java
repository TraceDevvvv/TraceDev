package com.example.dataaccess;

import com.example.domain.Address;
import com.example.exceptions.DataAccessException;

import java.util.List;

/**
 * Interface for Address data access operations.
 * Defines the contract for retrieving Address domain entities from a data source.
 */
public interface IAddressRepository {
    /**
     * Retrieves all addresses from the underlying data source.
     * @return A list of Address domain entities.
     * @throws DataAccessException If an error occurs during data retrieval.
     */
    List<Address> findAll() throws DataAccessException;
}