package com.example.repository;

import com.example.domain.Statistics;
import com.example.datasource.ServerDataSource;

/**
 * Implementation of StatisticsRepository.
 * Uses ServerDataSource to fetch data.
 */
public class StatisticsRepositoryImpl implements StatisticsRepository {
    private ServerDataSource dataSource;

    public StatisticsRepositoryImpl(ServerDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Statistics findByOperatorId(String operatorId) {
        try {
            return dataSource.fetchStatisticsData(operatorId);
        } catch (RuntimeException e) {
            // Simulating DataAccessException as per sequence diagram
            throw new RuntimeException("DataAccessException: " + e.getMessage(), e);
        }
    }
}