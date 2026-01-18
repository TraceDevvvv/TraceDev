package com.example.statistics;

/**
 * Data Access Layer (Repository Pattern) Implementation
 * Provides a concrete implementation for retrieving statistics data, simulating database interaction.
 */
public class StatisticsRepositoryImpl implements IStatisticsRepository {

    // A flag to simulate a connection error for testing the 'alt' path in the sequence diagram.
    private boolean simulateConnectionError = false;

    public void setSimulateConnectionError(boolean simulateConnectionError) {
        this.simulateConnectionError = simulateConnectionError;
    }

    /**
     * Simulates querying a database for personal statistics data.
     *
     * @param operatorId The ID of the operator.
     * @return PersonalStatisticsData if found, or null if not found.
     * @throws DataAccessException if a simulated connection error occurs.
     */
    @Override
    public PersonalStatisticsData findByOperatorId(String operatorId) throws DataAccessException {
        System.out.println("StatisticsRepositoryImpl: findByOperatorId called for operator ID: " + operatorId);

        if (simulateConnectionError) {
            System.err.println("StatisticsRepositoryImpl: Simulating connection error to DB.");
            // Corresponds to sequence diagram message m17: throws DataAccessException
            throw new DataAccessException("Simulated database connection error for operator " + operatorId);
        }

        // Simulate database query and return mock data
        // Corresponds to sequence diagram message m7: queryStatistics(operatorId : String)
        System.out.println("StatisticsRepositoryImpl: Querying Database for operator ID: " + operatorId);
        // This is where you would typically interact with a real ORM or JDBC connection.

        if ("OP001".equals(operatorId)) {
            // Return mock data for a known operator
            System.out.println("StatisticsRepositoryImpl: PersonalStatisticsData found for OP001.");
            return new PersonalStatisticsData("OP001", "RP101", 1250.75, 230);
        } else if ("OP002".equals(operatorId)) {
            System.out.println("StatisticsRepositoryImpl: PersonalStatisticsData found for OP002.");
            return new PersonalStatisticsData("OP002", "RP102", 890.50, 150);
        } else {
            // Simulate no data found for other operators
            System.out.println("StatisticsRepositoryImpl: No PersonalStatisticsData found for " + operatorId + ".");
            return null;
        }
    }
}