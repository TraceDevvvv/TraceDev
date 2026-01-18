package com.example.service;

import com.example.exception.ConnectionException;
import com.example.exception.ServiceException;
import com.example.model.CulturalGood;
import com.example.repository.CulturalGoodRepository;

/**
 * Service layer for cultural good operations.
 * Implements business logic and handles exceptions as per sequence and class diagrams.
 */
public class CulturalGoodService {
    private CulturalGoodRepository culturalGoodRepository;

    public CulturalGoodService(CulturalGoodRepository culturalGoodRepository) {
        this.culturalGoodRepository = culturalGoodRepository;
    }

    /**
     * Retrieves a cultural good by its ID.
     * Corresponds to step 3 in the sequence diagram.
     * @param id the cultural good identifier
     * @return the CulturalGood object
     * @throws ServiceException if a connection error or other issue occurs
     */
    public CulturalGood getCulturalGoodById(String id) throws ServiceException {
        try {
            // Step 4: call repository to find by ID
            return culturalGoodRepository.findById(id);
        } catch (ConnectionException e) {
            // Step in error path: handle connection error and throw ServiceException
            ServiceException serviceException = handleServiceException(e);
            throw serviceException;
        }
    }

    /**
     * Converts a ConnectionException into a ServiceException.
     * As per class diagram and sequence diagram error path.
     * @param exception the ConnectionException to wrap
     * @return a ServiceException with the connection error details
     */
    public ServiceException handleServiceException(ConnectionException exception) {
        // Create a ServiceException with a user-friendly message and the original cause.
        String message = "Service unavailable due to connection error: " + exception.getMessage();
        return new ServiceException(message, exception);
    }
}