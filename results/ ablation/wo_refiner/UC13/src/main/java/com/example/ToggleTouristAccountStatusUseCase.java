package com.example;

/**
 * Use case for toggling tourist account status.
 */
public class ToggleTouristAccountStatusUseCase {
    private TouristRepository repository;
    private NotificationService notificationService;
    private ErrorHandler errorHandler;
    private ETOURServerConnection serverConnection;

    public ToggleTouristAccountStatusUseCase(TouristRepository repository,
                                             NotificationService notificationService,
                                             ErrorHandler errorHandler,
                                             ETOURServerConnection serverConnection) {
        this.repository = repository;
        this.notificationService = notificationService;
        this.errorHandler = errorHandler;
        this.serverConnection = serverConnection;
    }

    /**
     * Executes the toggle status command.
     * Returns a Result indicating success or failure with a message.
     */
    public Result execute(ToggleTouristStatusCommand command) {
        try {
            // Find tourist
            Tourist tourist = repository.findById(command.getTouristId());
            if (tourist == null) {
                // Simulate NotFoundException
                throw new RuntimeException("NotFoundException");
            }

            // Toggle status
            if (command.getTargetStatus()) {
                tourist.activate();
            } else {
                tourist.deactivate();
            }

            // Save updated tourist
            repository.save(tourist);

            // Send notification
            notificationService.sendNotification("Tourist account status updated");

            // Check server connection
            if (!serverConnection.isConnected()) {
                // Connection lost during execution
                throw new RuntimeException("ConnectionLostException");
            }

            // Success
            return Result.success("Tourist status updated successfully.");
        } catch (Exception e) {
            // Handle errors
            String errorMessage;
            if (e.getMessage().contains("ConnectionLostException")) {
                errorMessage = errorHandler.handleConnectionError(e);
            } else {
                errorMessage = errorHandler.handleRepositoryError(e);
            }
            return Result.failure(errorMessage);
        }
    }
}