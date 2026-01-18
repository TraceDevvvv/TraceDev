package com.example.pointofrest;

import java.util.Collections;
import java.util.List;

/**
 * Implementation of IPointOfRestRepository that fetches data from the ETOUR service.
 * It uses an EtourServiceAdapter to communicate with ETOUR and maps raw ETOUR data
 * to the internal PointOfRest entity.
 */
public class EtourPointOfRestRepository implements IPointOfRestRepository {

    private EtourServiceAdapter etourAdapter;

    /**
     * Constructor for EtourPointOfRestRepository.
     *
     * @param etourAdapter The adapter to interact with the ETOUR service.
     */
    public EtourPointOfRestRepository(EtourServiceAdapter etourAdapter) {
        this.etourAdapter = etourAdapter;
    }

    /**
     * Finds a PointOfRest entity by its unique identifier by fetching data from ETOUR.
     *
     * @param id The unique identifier of the PointOfRest.
     * @return The found PointOfRest entity.
     * @throws Exception if an error occurs during data retrieval from ETOUR or mapping.
     */
    @Override
    public PointOfRest findById(String id) throws Exception {
        System.out.println("[EtourPointOfRestRepository] Finding PointOfRest by ID: " + id);
        try {
            // 1. Fetch raw data from ETOUR using the renamed method
            RawEtourData rawData = fetchRawDataFromEtour(id);

            // 2. Map raw data to PointOfRest entity
            PointOfRest pointOfRest = mapRawDataToPointOfRest(rawData);
            System.out.println("[EtourPointOfRestRepository] Successfully mapped raw data to PointOfRest: " + pointOfRest.name);
            return pointOfRest;
        } catch (Exception e) {
            System.err.println("[EtourPointOfRestRepository] Error finding PointOfRest by ID " + id + ": " + e.getMessage());
            throw e; // Re-throw the exception to propagate it up the call stack
        }
    }

    /**
     * Fetches raw data for a specific PointOfRest from the ETOUR service.
     * This is a private helper method called by findById.
     *
     * @param id The ID of the PointOfRest.
     * @return RawEtourData retrieved from ETOUR.
     * @throws Exception if the ETOUR service encounters an error.
     */
    private RawEtourData fetchRawDataFromEtour(String id) throws Exception {
        // Calls the EtourServiceAdapter to get raw data, using the renamed method
        return etourAdapter.requestPointOfRest(id);
    }

    /**
     * Maps RawEtourData object to a PointOfRest entity.
     * This is a private helper method to transform external data format to internal domain model.
     *
     * @param rawData The raw data object from ETOUR.
     * @return A PointOfRest entity.
     */
    private PointOfRest mapRawDataToPointOfRest(RawEtourData rawData) {
        // Assume simple one-to-one mapping for demonstration
        PointOfRest pointOfRest = new PointOfRest();
        pointOfRest.id = rawData.etourId;
        pointOfRest.name = rawData.etourName;
        pointOfRest.address = rawData.etourAddress;
        pointOfRest.type = rawData.etourType;
        pointOfRest.description = rawData.etourDescription;
        pointOfRest.contactInfo = rawData.etourContact;
        return pointOfRest;
    }

    /**
     * Finds all available PointOfRest entities.
     * This implementation provides a dummy list as the sequence diagram focuses on findById.
     *
     * @return A list of all PointOfRest entities.
     * @throws Exception if an error occurs during data retrieval.
     */
    @Override
    public List<PointOfRest> findAll() throws Exception {
        System.out.println("[EtourPointOfRestRepository] Finding all PointOfRest entities (dummy data).");
        // Dummy implementation for findAll as per requirements
        return Collections.singletonList(
                new PointOfRest("POR123", "Dummy Cafe", "101 Dummy St", "Cafe", "Dummy description", "dummy@example.com")
        );
    }
}