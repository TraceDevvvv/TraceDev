package com.example.service;

import com.example.repository.StatisticsRepository;
import com.example.domain.Statistics;
import com.example.dto.StatisticsDTO;

/**
 * Service layer for statistics business logic.
 * Depends on StatisticsRepository.
 */
public class StatisticsService {
    private StatisticsRepository statisticsRepository;

    public StatisticsService(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    /**
     * Retrieves personal statistics for given operator and converts to DTO.
     * Corresponds to sequence diagram message "Create Statistics domain object"
     * which goes from Service to Domain, but this is handled by the repository.
     */
    public StatisticsDTO getPersonalStatistics(String operatorId) {
        Statistics statistics = statisticsRepository.findByOperatorId(operatorId);
        return new StatisticsDTO(statistics);
    }
}