package com.example.statistics;

/**
 * Application Layer Component
 * Provides a service interface for fetching statistics, mediating between
 * the Presentation/Domain layer and the Data Access Layer.
 */
public class StatisticsService {
    private IStatisticsRepository statisticsRepository;

    /**
     * Constructor for StatisticsService.
     * @param statisticsRepository The repository for accessing raw statistics data.\n
     */
    public StatisticsService(IStatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    /**
     * Fetches raw operator statistics from the data access layer.
     * Handles potential data access exceptions by converting them into business exceptions.
     *
     * @param operatorId The ID of the operator.
     * @return PersonalStatisticsData containing raw statistics.
     * @throws BusinessException if data retrieval fails due to a data access issue.
     */
    public PersonalStatisticsData fetchOperatorStatistics(String operatorId) throws BusinessException {
        System.out.println("StatisticsService: Fetching raw statistics from repository for operator ID: " + operatorId);
        try {
            PersonalStatisticsData data = statisticsRepository.findByOperatorId(operatorId);
            if (data == null) {
                // If repository returns null, it means no data found for this operator.
                throw new BusinessException("No statistics found for operator ID: " + operatorId);
            }
            return data;
        } catch (DataAccessException e) {
            // Corresponds to sequence diagram message m17: throws DataAccessException (received by StatsService)
            // Wrap DataAccessException in a more generic BusinessException for the upper layers.
            System.err.println("StatisticsService: Data access error - " + e.getMessage());
            // Corresponds to sequence diagram message m18: throws BusinessException
            throw new BusinessException("Could not retrieve operator statistics due to a data source error.", e);
        }
    }
}