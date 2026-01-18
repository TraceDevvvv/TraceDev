package com.example.delaysystem;

import java.util.Date;

/**
 * The Use Case Controller for managing delay modifications.
 * This class orchestrates interactions between the ViewModel and the Repository/UserSession.
 */
public class DelayModificationUseCaseController {

    private IDelayRepository delayRepository;
    private UserSession userSession; // Added to satisfy R3, R4

    /**
     * Constructor for DelayModificationUseCaseController.
     *
     * @param delayRepository The repository for delay data access.
     * @param userSession The current user session (added to satisfy R3, R4).
     */
    public DelayModificationUseCaseController(IDelayRepository delayRepository, UserSession userSession) {
        this.delayRepository = delayRepository;
        this.userSession = userSession;
        // Assuming user session status is checked elsewhere if critical for *all* operations.
        // For this sequence, it's a dependency but not explicitly shown being used.
        if (!userSession.checkLoggedInStatus()) {
            System.out.println("[UseCaseController] Warning: User not logged in, but proceeding with controller initialization.");
        }
    }

    /**
     * Fetches scheduling information for a specific date.
     * This method delegates the call to the delay repository.
     *
     * @param date The date for which to fetch scheduling information.
     * @return A DTO containing scheduling information, or null if an error occurs.
     */
    public SchedulingInfoDTO fetchSchedulingInfoByDate(Date date) {
        System.out.println("[UseCaseController] Request to fetch scheduling info for date: " + date);
        // Additional business logic could go here, e.g., permission checks using userSession
        // if (userSession.checkLoggedInStatus() && userSession.getCurrentUserId().equals("admin")) { ... }
        SchedulingInfoDTO result = delayRepository.fetchSchedulingInfoByDate(date);
        if (result == null) {
            System.err.println("[UseCaseController] Failed to retrieve scheduling info for date: " + date);
        } else {
            System.out.println("[UseCaseController] Successfully fetched scheduling info for date: " + date);
        }
        return result;
    }

    /**
     * Updates scheduling delay information.
     * This method delegates the call to the delay repository.
     *
     * @param delayDto The DTO containing the delay data to update.
     * @return true if the update was successful, false otherwise.
     */
    public boolean updateSchedulingDelay(DelayDTO delayDto) {
        System.out.println("[UseCaseController] Request to update scheduling delay: " + delayDto);
        // Additional business logic could go here, e.g., validation or permission checks
        if (delayDto == null) {
            System.err.println("[UseCaseController] Attempted to update with null DelayDTO.");
            return false;
        }

        // Example business rule: A delay cannot be negative
        if (delayDto.durationMinutes < 0) {
            System.err.println("[UseCaseController] Validation failed: Delay duration cannot be negative.");
            return false;
        }

        boolean success = delayRepository.saveDelayData(delayDto);
        if (!success) {
            System.err.println("[UseCaseController] Failed to update scheduling delay for: " + delayDto);
        } else {
            System.out.println("[UseCaseController] Successfully updated scheduling delay: " + delayDto);
        }
        return success;
    }
}