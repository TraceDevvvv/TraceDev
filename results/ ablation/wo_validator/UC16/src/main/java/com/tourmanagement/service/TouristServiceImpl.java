package com.tourmanagement.service;

import com.tourmanagement.repository.TouristRepository;
import com.tourmanagement.repository.ETOURConnectionException;

public class TouristServiceImpl implements TouristService {
    private TouristRepository touristRepository;
    private NotificationService notificationService;

    public TouristServiceImpl(TouristRepository touristRepository, NotificationService notificationService) {
        this.touristRepository = touristRepository;
        this.notificationService = notificationService;
    }

    @Override
    public void deleteTourist(String touristId) {
        // This method is called by the controller; confirmation is handled in the view
        // The actual deletion logic is performed here after confirmation
        try {
            // Step 1: Attempt to delete the tourist from repository
            touristRepository.delete(touristId);
            // Step 2: Notify about the deletion
            notificationService.sendNotification("Tourist deleted", "Agency Operator");
        } catch (ETOURConnectionException e) {
            // Wrap in a runtime exception to be caught by the controller/view
            throw new RuntimeException("Server connection lost", e);
        }
    }
}