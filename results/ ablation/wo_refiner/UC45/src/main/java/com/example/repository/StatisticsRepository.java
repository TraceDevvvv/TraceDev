package com.example.repository;

import com.example.domain.Statistics;

/**
 * Repository interface for statistics data access.
 */
public interface StatisticsRepository {
    Statistics findByOperatorId(String operatorId);
}