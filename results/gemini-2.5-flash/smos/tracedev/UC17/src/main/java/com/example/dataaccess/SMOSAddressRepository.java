package com.example.dataaccess;

import com.example.domain.Address;
import com.example.dto.SMOSAddressData;
import com.example.exceptions.ConnectionException;
import com.example.exceptions.DataAccessException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of IAddressRepository that retrieves data from the SMOS system.
 * This class is responsible for interacting with SMOSClient and mapping raw SMOS data
 * to the application's domain Address entities.
 */
public class SMOSAddressRepository implements IAddressRepository {
    private SMOSClient smosClient;

    /**
     * Constructs a SMOSAddressRepository with a given SMOSClient.
     * @param smosClient The SMOSClient instance to use for data retrieval.
     */
    public SMOSAddressRepository(SMOSClient smosClient) {
        this.smosClient = smosClient;
        System.out.println("[SMOSAddressRepository] Initialized with SMOSClient.");
    }

    /**
     * Retrieves all addresses by fetching raw data from SMOS and mapping it to Address entities.
     * @return A list of Address domain entities.
     * @throws DataAccessException If there's a problem connecting to SMOS or mapping data.
     */
    @Override
    public List<Address> findAll() throws DataAccessException {
        System.out.println("[SMOSAddressRepository] Calling SMOSClient to fetch raw addresses.");
        try {
            List<SMOSAddressData> rawAddresses = smosClient.fetchRawAddresses();
            System.out.println("[SMOSAddressRepository] Mapping " + rawAddresses.size() + " raw SMOS data to Address entities.");
            return rawAddresses.stream()
                    .map(this::mapSMOSAddressDataToAddress)
                    .collect(Collectors.toList());
        } catch (ConnectionException e) {
            // Translate ConnectionException to DataAccessException for the application layer
            System.err.println("[SMOSAddressRepository] SMOS connection failed: " + e.getMessage());
            throw new DataAccessException("Failed to retrieve addresses from SMOS due to connection issues.", e);
        } catch (Exception e) {
            // Catch any other unexpected exceptions during data processing/mapping
            System.err.println("[SMOSAddressRepository] Unexpected error during data retrieval/mapping: " + e.getMessage());
            throw new DataAccessException("An unexpected error occurred while accessing SMOS addresses.", e);
        }
    }

    /**
     * Helper method to map SMOSAddressData to an Address domain entity.
     * @param smosData The raw SMOS data.
     * @return A corresponding Address domain entity.
     */
    private Address mapSMOSAddressDataToAddress(SMOSAddressData smosData) {
        // Assume country is always "USA" for simplicity or could be derived from other SMOS data
        String country = "USA";
        // SMOSAddressData has rawId, rawStreet, rawCity, rawZip
        // Address has id, street, city, postalCode, country
        return new Address(smosData.getRawId(), smosData.getRawStreet(), smosData.getRawCity(), smosData.getRawZip(), country);
    }
}