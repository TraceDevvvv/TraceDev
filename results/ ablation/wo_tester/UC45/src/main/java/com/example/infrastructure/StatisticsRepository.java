package com.example.infrastructure;

import com.example.domain.StatisticsData;

/**
 * Repository interface for statistics data access.
 */
public interface StatisticsRepository {
    StatisticsData findByOperatorId(String operatorId);
}