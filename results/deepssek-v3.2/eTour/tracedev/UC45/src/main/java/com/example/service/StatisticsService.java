package com.example.service;

import com.example.dto.PersonalStatisticsDTO;

/**
 * Interface for statistics service operations.
 */
public interface StatisticsService {

    /**
     * Retrieves personal statistics for a given operator.
     */
    PersonalStatisticsDTO getPersonalStatistics(String operatorId);
}