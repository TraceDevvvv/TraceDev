package com.example.statistics;

/**
 * Presentation Layer (MVC) Component
 * Acts as an intermediary between the controller and the application layer,
 * handling data transformation and business logic specific to presentation.
 */
public class PersonalStatisticsModel {
    private StatisticsService statisticsService;

    /**
     * Constructor for PersonalStatisticsModel.
     * @param statisticsService The service responsible for fetching raw statistics data.
     */
    public PersonalStatisticsModel(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    /**
     * Retrieves personal statistics for a given operator, processes them,
     * and transforms them into a DTO suitable for display.
     *
     * @param operatorId The ID of the operator.
     * @return PersonalStatisticsDto containing formatted statistics for display.
     * @throws BusinessException if an error occurs during data retrieval or processing.
     */
    public PersonalStatisticsDto getStatisticsForOperator(String operatorId) throws BusinessException {
        System.out.println("PersonalStatisticsModel: Fetching raw statistics for operator ID: " + operatorId);
        PersonalStatisticsData rawStats = statisticsService.fetchOperatorStatistics(operatorId);

        System.out.println("PersonalStatisticsModel: Ensuring accuracy of raw data (REQ_QUALITY_1).");
        // REQ_QUALITY_1: Ensure accuracy before further processing
        PersonalStatisticsData accurateStats = ensureAccuracy(rawStats);

        System.out.println("PersonalStatisticsModel: Mapping raw data to DTO.");
        PersonalStatisticsDto statsDto = mapDataToDto(accurateStats);

        return statsDto;
    }

    /**
     * Placeholder method to ensure data accuracy.
     * As per REQ_QUALITY_1, this method would contain logic to validate or correct data.
     * For this implementation, it simply returns the input data.
     * @param statsData The raw statistics data.
     * @return The data after ensuring its accuracy.
     */
    public PersonalStatisticsData ensureAccuracy(PersonalStatisticsData statsData) {
        // Implement complex business rules to ensure data accuracy here.
        // For example, cross-validate with other systems, apply corrections, etc.
        System.out.println("PersonalStatisticsModel: Data accuracy check performed (dummy implementation).");
        if (statsData == null) {
            // Example: If statsData is null, maybe create an empty one or throw an exception
            System.out.println("PersonalStatisticsModel: No data to ensure accuracy for.");
            return new PersonalStatisticsData("N/A", "N/A", 0.0, 0); // Return empty or throw
        }
        return statsData; // Currently, a no-op placeholder
    }

    /**
     * Maps raw PersonalStatisticsData to PersonalStatisticsDto, including formatting.
     * In a real application, this might involve fetching operator and point names
     * from other serv/repositories based on IDs.
     * @param statsData The raw statistics data.
     * @return A DTO with formatted statistics.
     */
    private PersonalStatisticsDto mapDataToDto(PersonalStatisticsData statsData) {
        if (statsData == null) {
            return new PersonalStatisticsDto("Unknown Operator", "Unknown Point", "N/A", "N/A");
        }

        // Mock fetching operator and point names. In a real app, this would involve other serv.
        String operatorName = "Operator " + statsData.getOperatorId();
        String pointName = "Point " + statsData.getPointId();

        // Format sales and items for display
        String formattedSales = String.format("$%.2f", statsData.getTotalSales());
        String formattedItemsSold = String.valueOf(statsData.getItemsSold());

        return new PersonalStatisticsDto(operatorName, pointName, formattedSales, formattedItemsSold);
    }
}