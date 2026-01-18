package com.example.statistics;

/**
 * Presentation Layer (MVC) Component
 * Manages the flow for displaying personal statistics, interacting with the model and view.
 */
public class PersonalStatisticsController {
    private PersonalStatisticsModel statisticsModel;
    private PersonalStatisticsView statisticsView;

    /**
     * Constructor for PersonalStatisticsController.
     * @param statisticsModel The model responsible for business logic and data retrieval.
     * @param statisticsView The view responsible for rendering the statistics.
     */
    public PersonalStatisticsController(PersonalStatisticsModel statisticsModel, PersonalStatisticsView statisticsView) {
        this.statisticsModel = statisticsModel;
        this.statisticsView = statisticsView;
    }

    /**
     * Fetches and displays personal statistics for a given operator.
     * Delegates data retrieval to the model and display to the view.
     * Handles potential business exceptions during data retrieval.
     *
     * @param operatorId The ID of the operator whose statistics are to be displayed.
     * @return PersonalStatisticsDto if successful, null if an error occurred.
     */
    public PersonalStatisticsDto getStatisticsForDisplay(String operatorId) {
        System.out.println("PersonalStatisticsController: Requesting statistics for operator ID: " + operatorId);
        try {
            // Request data from the model
            PersonalStatisticsDto statsDto = statisticsModel.getStatisticsForOperator(operatorId);

            // Pass data to the view for rendering
            statisticsView.renderStatistics(statsDto);
            System.out.println("PersonalStatisticsController: Statistics displayed successfully.");
            // Corresponds to sequence diagram return message m15: displayStatisticsScreen()
            return statsDto;

        } catch (BusinessException e) {
            // Corresponds to sequence diagram message m18: throws BusinessException (received by StatsCtrl)
            // Handle business exception (e.g., data not found, invalid state)
            System.err.println("PersonalStatisticsController: Business Exception - " + e.getMessage());
            statisticsView.displayErrorMessage("Failed to retrieve statistics: " + e.getMessage());
            // REQ_EXIT_COND_2: Display error message is handled by the view.
            // Corresponds to sequence diagram return message m20: errorMessageDisplayed
            return null;
        } catch (Exception e) {
            // Catch any unexpected exceptions
            System.err.println("PersonalStatisticsController: An unexpected error occurred: " + e.getMessage());
            statisticsView.displayErrorMessage("An unexpected error occurred. Please try again later.");
            // Corresponds to sequence diagram return message m20: errorMessageDisplayed
            return null;
        }
    }
}