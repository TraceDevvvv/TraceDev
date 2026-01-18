package com.example.localization.module;

import com.example.localization.model.Location;

/**
 * Interface for a GPS module, representing the actual hardware or sensor.
 */
public interface IGpsModule {
    /**
     * Queries the GPS sensor to get the current location.
     * This operation is synchronous from the perspective of the caller of this method,
     * but its execution might be part of a larger asynchronous flow.
     *
     * @return A Location object containing the current GPS data.
     * @throws GpsSensorException if the GPS sensor fails to provide data or
     *                            the position is not detectable.
     */
    Location queryGpsSensor() throws GpsSensorException;
}