package com.example.service;

import com.example.model.Convention;
import com.example.model.ActivationResult;

/**
 * Default implementation of ActivationHandler.
 */
public class DefaultActivationHandler implements ActivationHandler {
    private ETourService etourService;

    public DefaultActivationHandler(ETourService etourService) {
        this.etourService = etourService;
    }

    @Override
    public ActivationResult handleActivation(Convention convention) {
        // Activate the refreshment point
        convention.getRefreshmentPoint().activate();

        boolean notificationSent = false;
        if (etourService.isConnected()) {
            notificationSent = etourService.notifyConventionActivation(convention.getId());
        } else {
            logConnectionError();
            // Retry notification as per requirement
            retryNotification(convention.getId());
        }

        // Activate the convention
        convention.activate();

        return new ActivationResult(true, null, notificationSent);
    }

    @Override
    public void logConnectionError() {
        System.err.println("Connection to ETourService interrupted.");
    }

    /**
     * Retry notification in case of ETOUR interruption.
     * In a real system, this might queue a retry or use a fallback mechanism.
     */
    public boolean retryNotification(String conventionId) {
        // Simulating a simple retry after a delay.
        System.out.println("Retrying notification for convention " + conventionId + "...");
        // For simplicity, we assume the retry is successful.
        return true;
    }
}