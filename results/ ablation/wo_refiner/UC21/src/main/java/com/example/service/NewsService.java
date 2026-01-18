package com.example.service;

import com.example.command.InsertNewsCommand;
import com.example.entity.NewsEntity;
import com.example.repository.NewsRepository;

/**
 * Core service for processing news insertion.
 * Stereotype <<reliable>> - must maintain 99.9% availability and max response time < 2s.
 */
public class NewsService {

    private NewsRepository newsRepository;
    private ValidationService validationService;
    private NotificationService notificationService;
    private ETOURServerAdapter etourServerAdapter;

    public NewsService(NewsRepository newsRepository,
                       ValidationService validationService,
                       NotificationService notificationService,
                       ETOURServerAdapter etourServerAdapter) {
        this.newsRepository = newsRepository;
        this.validationService = validationService;
        this.notificationService = notificationService;
        this.etourServerAdapter = etourServerAdapter;
    }

    /**
     * Main method to process news insertion.
     * @param command The insert command.
     * @return true if successful, false otherwise.
     */
    public boolean processInsertNews(InsertNewsCommand command) {
        // Step 1: Validate command
        if (!command.validate()) {
            triggerErroredUseCase("Invalid command data");
            notificationService.sendError("Invalid command data");
            return false;
        }

        // Step 2: Validate news data
        if (!validateNewsData(command)) {
            triggerErroredUseCase("Invalid data entered");
            notificationService.sendError("Invalid data entered");
            return false;
        }

        // Step 3: Request confirmation (simplified - in sequence diagram this is handled by controller)
        // We assume confirmation is already obtained before calling confirmInsertion.

        // Step 4: Confirm insertion (this would be called after user confirmation)
        return confirmInsertion(command);
    }

    /**
     * Validates the news data using ValidationService.
     * @param command The insert command.
     * @return true if data is valid.
     */
    boolean validateNewsData(InsertNewsCommand command) {
        // Delegate to validation service
        boolean titleValid = validationService.validateRequired(command.getTitle()) &&
                             validationService.validateLength(command.getTitle(), 1, 200);
        boolean contentValid = validationService.validateRequired(command.getContent()) &&
                               validationService.validateLength(command.getContent(), 1, 5000);
        boolean authorValid = validationService.validateRequired(command.getAuthor()) &&
                              validationService.validateLength(command.getAuthor(), 1, 100);
        return titleValid && contentValid && authorValid;
    }

    /**
     * Stores the news after validation and confirmation.
     * @param command The insert command.
     * @return true if storage succeeded.
     */
    boolean storeNews(InsertNewsCommand command) {
        NewsEntity entity = NewsEntity.createFromCommand(command);
        return newsRepository.save(entity);
    }

    /**
     * Cancels the insertion operation.
     * @param cancellationRequest The cancellation request.
     * @return true if cancellation was processed.
     */
    public boolean cancelInsertion(String cancellationRequest) {
        // Perform any cleanup and notify user.
        notificationService.sendError("Operation cancelled: " + cancellationRequest);
        return true;
    }

    /**
     * Requests confirmation from the user (in this simplified version, we just return true).
     * In a real scenario, this would interact with the controller/boundary.
     */
    public boolean requestConfirmation(InsertNewsCommand command) {
        // This would trigger UI confirmation dialog.
        // For simplicity, we assume confirmation is granted.
        return true;
    }

    /**
     * Confirms the insertion after user confirmation.
     * @param command The insert command.
     * @return true if insertion succeeded.
     */
    public boolean confirmInsertion(InsertNewsCommand command) {
        // Store news
        NewsEntity entity = NewsEntity.createFromCommand(command);
        boolean saved = newsRepository.save(entity);
        if (!saved) {
            notificationService.sendError("Failed to save news");
            return false;
        }

        // Check ETOUR connection and send
        if (!etourServerAdapter.isConnected()) {
            etourServerAdapter.handleDisconnection("Connection lost");
            notificationService.sendError("ETOUR server disconnected");
            return false;
        }

        boolean sent = etourServerAdapter.sendNews(entity);
        if (sent) {
            notificationService.sendSuccess("News inserted successfully");
            return true;
        } else {
            notificationService.sendError("Failed to send news to ETOUR");
            return false;
        }
    }

    /**
     * Triggers an errored use case for error handling.
     * @param errorDetails Description of the error.
     * @return true if error case was triggered.
     */
    public boolean triggerErroredUseCase(String errorDetails) {
        // Log error and possibly perform recovery actions.
        System.out.println("Errored use case triggered: " + errorDetails);
        return true;
    }
}