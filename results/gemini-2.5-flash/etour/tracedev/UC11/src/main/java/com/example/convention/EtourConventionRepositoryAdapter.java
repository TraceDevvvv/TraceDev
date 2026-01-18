package com.example.convention;

import java.util.List;

/**
 * Adapter implementation of {@link ConventionRepository} that interacts with the {@link EtourSystem}.
 * This class is responsible for handling REQ7: ETOUR connection interruption.
 */
public class EtourConventionRepositoryAdapter implements ConventionRepository {
    private final EtourSystem etourSystem;

    /**
     * Constructs an EtourConventionRepositoryAdapter with a given EtourSystem.
     *
     * @param etourSystem The external ETOUR system to adapt.
     */
    public EtourConventionRepositoryAdapter(EtourSystem etourSystem) {
        this.etourSystem = etourSystem;
    }

    /**
     * Retrieves convention data from the ETOUR system for a specific Point of Rest ID.
     * Implements the contract defined by {@link ConventionRepository}.
     *
     * @param pointOfRestId The ID of the Point of Rest.
     * @return A list of {@link Convention} objects.
     * @throws EtourConnectionException if the ETOUR system connection fails, addressing REQ7.
     */
    @Override
    public List<Convention> findByPointOfRestId(String pointOfRestId) {
        System.out.println("Repository: Requesting convention data from ETOUR system for pointOfRestId: " + pointOfRestId);
        try {
            // This interaction fulfills the sequence diagram step: Repository -> ETOUR: requestConventionData
            List<Convention> conventions = etourSystem.requestConventionData(pointOfRestId);
            System.out.println("Repository: Received " + conventions.size() + " conventions from ETOUR.");
            return conventions;
        } catch (EtourConnectionException e) {
            // Handles REQ7: ETOUR connection interruption.
            // This fulfills the sequence diagram step: Repository --x Service: ConnectionFailedException
            System.err.println("Repository: ETOUR connection interrupted. " + e.getMessage());
            throw new EtourConnectionException("Failed to retrieve conventions due to ETOUR system unavailability for " + pointOfRestId, e);
        }
    }
}