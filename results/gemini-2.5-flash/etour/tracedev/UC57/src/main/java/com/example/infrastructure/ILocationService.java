package com.example.infrastructure;

import com.example.domain.Location;
import com.example.exceptions.ETOURServiceException;

import java.util.Map;

/**
 * Interface for an infrastructure service that provides user location.
 * Modified to satisfy requirement R11, indicating it can throw ETOURServiceException.
 */
public interface ILocationService {
    /**
     * Retrieves the user's current location based on the provided context.
     * @param touristEventContext A map containing context information, e.g., session ID, device ID.
     * @return The user's {@link Location}.
     * @throws ETOURServiceException if there is an issue connecting to the ETOUR external system (R11).
     */
    Location requestLocationData(Map<String, String> touristEventContext) throws ETOURServiceException;
}