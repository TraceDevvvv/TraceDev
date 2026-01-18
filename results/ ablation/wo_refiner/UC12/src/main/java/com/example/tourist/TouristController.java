package com.example.tourist;

/**
 * Controller that orchestrates the flow between view and service layers.
 * Traceability: R5 - Controller orchestrates flow.
 */
public class TouristController {
    private TouristService touristService;

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    /**
     * Displays tourist card by ID.
     * @param touristId the tourist ID
     * @return TouristDTO with tourist details
     */
    public TouristDTO displayTouristCard(String touristId) {
        return touristService.getTouristDetails(touristId);
    }
}