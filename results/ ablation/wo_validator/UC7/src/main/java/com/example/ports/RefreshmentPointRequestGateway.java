package com.example.ports;

import com.example.domain.ConventionDataDTO;

/**
 * Port interface for loading convention data from external point of rest service.
 */
public interface RefreshmentPointRequestGateway {
    ConventionDataDTO loadRequestData(String conventionId);
}