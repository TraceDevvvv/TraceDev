package com.example.statistics;

/**
 * Data Access Layer (Repository Pattern) Interface
 * Defines the contract for retrieving personal statistics data from a data source.
 */
public interface IStatisticsRepository {
    /**
     * Finds personal statistics data for a given operator ID.
     * @param operatorId The ID of the operator.
     * @return PersonalStatisticsData object if found, null otherwise.
     * @throws DataAccessException if there's an issue communicating with the data source.
     */
    PersonalStatisticsData findByOperatorId(String operatorId) throws DataAccessException;
}