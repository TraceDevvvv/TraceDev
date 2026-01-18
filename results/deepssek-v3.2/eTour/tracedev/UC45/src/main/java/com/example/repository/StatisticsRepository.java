package com.example.repository;

import com.example.entity.StatisticsData;

/**
 * Repository interface for statistics data access.
 */
public interface StatisticsRepository {

    /**
     * Retrieves statistics data for refreshment point associated with operator.
     */
    StatisticsData findByOperatorId(String operatorId);
}