package com.tourmanagement.controller;

import com.tourmanagement.service.TouristService;

public class DeleteTouristController {
    private TouristService touristService;

    public DeleteTouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    public void deleteTourist(String touristId) {
        // The controller delegates the deletion to the service
        // Error handling is done in the view (TouristListView)
        touristService.deleteTourist(touristId);
    }
}