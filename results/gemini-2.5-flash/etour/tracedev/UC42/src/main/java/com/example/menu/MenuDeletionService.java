package com.example.menu;

import java.util.Objects;

/**
 * Application layer service responsible for orchestrating the deletion of daily menus.
 * It uses the Command pattern for deletion and logs audit events.
 */
public class MenuDeletionService {
    private final DailyMenuRepository dailyMenuRepository;
    private final AuditLogService auditLogService;

    /**
     * REQ-R19, REQ-R24: Represents the outcome of a daily menu deletion operation.
     * Contains a success flag and an optional error message.
     */
    public record DeletionResult(boolean success, String errorMessage) {}

    /**
     * Constructs a new MenuDeletionService.
     *
     * @param dailyMenuRepository The repository to interact with for menu data.
     * @param auditLogService The service to log audit events.
     */
    public MenuDeletionService(DailyMenuRepository dailyMenuRepository, AuditLogService auditLogService) {
        this.dailyMenuRepository = Objects.requireNonNull(dailyMenuRepository, "DailyMenuRepository cannot be null");
        this.auditLogService = Objects.requireNonNull(auditLogService, "AuditLogService cannot be null");
    }

    /**
     * REQ-R10: Deletes a daily menu for a specific day.
     * This method acts as the invoker for the DeleteDailyMenuCommand.
     *
     * @param dayOfWeek The day of the week for which the menu should be deleted.
     * @return A DeletionResult object indicating success/failure and an error message if applicable.
     */
    public DeletionResult deleteDailyMenu(DayOfWeek dayOfWeek) {
        System.out.println("[MenuDeletionService] Initiating deletion for " + dayOfWeek);
        DeleteDailyMenuCommand command = new DeleteDailyMenuCommand(dayOfWeek, dailyMenuRepository); // REQ-R10

        try {
            command.execute(); // REQ-R10
            auditLogService.logDeletion(command); // Log successful deletion // REQ-R10
            System.out.println("[MenuDeletionService] Deletion command executed successfully.");
            return new DeletionResult(true, null); // Return success with no error message
        } catch (NetworkConnectionException e) {
            // REQ-R13: Catch NetworkConnectionException and log the error.
            auditLogService.logError(e, "Daily Menu Deletion");
            System.err.println("[MenuDeletionService] Deletion failed due to network error: " + e.getMessage());
            return new DeletionResult(false, e.getMessage()); // Return failure with network error message
        } catch (Exception e) {
            // Catch any other unexpected exceptions.
            auditLogService.logError(e, "Daily Menu Deletion (Unexpected Error)");
            System.err.println("[MenuDeletionService] Deletion failed due to an unexpected error: " + e.getMessage());
            return new DeletionResult(false, e.getMessage()); // Return failure with unexpected error message
        }
    }
}