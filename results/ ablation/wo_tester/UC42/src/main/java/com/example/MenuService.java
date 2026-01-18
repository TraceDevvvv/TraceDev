package com.example;

/**
 * MenuService class that implements the business logic for menu operations.
 * Handles deletion requests, connection checks, and error handling.
 */
public class MenuService {
    private IMenuRepository menuRepository;
    private ETOURClient etourClient;

    /**
     * Constructor that initializes MenuService with required dependencies.
     * @param menuRepository The repository for menu data access.
     * @param etourClient The client for ETOUR server connection.
     */
    public MenuService(IMenuRepository menuRepository, ETOURClient etourClient) {
        this.menuRepository = menuRepository;
        this.etourClient = etourClient;
    }

    /**
     * Requests deletion of a daily menu for a specific day.
     * Performs connection check and retrieves the menu for confirmation.
     * @param day The day for which deletion is requested.
     * @return A ConfirmationDTO indicating whether confirmation is needed.
     */
    public ConfirmationDTO requestDeletion(String day) {
        System.out.println("Requesting deletion for day: " + day);

        // Check server connection as per sequence diagram.
        String connectionStatus = checkConnection();
        if (!"connection ok".equals(connectionStatus)) {
            return handleConnectionErrorRequest();
        }

        // Find the daily menu to confirm its existence.
        DailyMenu menu = menuRepository.findDailyMenu(day);
        if (menu == null) {
            return new ConfirmationDTO("No menu found for " + day, false);
        }

        // Return confirmation request.
        return new ConfirmationDTO("Confirm deletion of menu for " + day + "?", true);
    }

    /**
     * Deletes the daily menu for a specific day.
     * Performs connection check and calls repository to delete.
     * @param day The day for which the menu should be deleted.
     * @return A ResultDTO indicating success or failure.
     */
    public ResultDTO deleteDailyMenu(String day) {
        System.out.println("Deleting daily menu for day: " + day);

        // Check server connection again as per sequence diagram.
        String connectionStatus = checkConnection();
        if (!"connection ok".equals(connectionStatus)) {
            return handleConnectionError();
        }

        // Delete the menu via the repository.
        boolean success = menuRepository.deleteDailyMenu(day);
        if (success) {
            return new ResultDTO(true, "Menu deleted successfully");
        } else {
            return new ResultDTO(false, "Failed to delete menu");
        }
    }

    /**
     * Handles connection errors with the ETOUR server.
     * Logs the error and returns a failure ResultDTO.
     * @return A ResultDTO indicating connection error.
     */
    public ResultDTO handleConnectionError() {
        etourClient.handleConnectionError();
        System.out.println("Successful exit condition");
        return new ResultDTO(false, "Connection error: Cannot connect to ETOUR server");
    }

    /**
     * Handle connection error for request deletion.
     * @return ConfirmationDTO indicating connection error.
     */
    public ConfirmationDTO handleConnectionErrorRequest() {
        etourClient.handleConnectionError();
        return new ConfirmationDTO("Connection error: Cannot connect to ETOUR server", false);
    }

    /**
     * Checks connection status with ETOUR.
     * Corresponds to messages m11, m12, m30, m31 in sequence diagram.
     * @return "connection ok" or "connection error"
     */
    public String checkConnection() {
        if (etourClient.checkConnection()) {
            return "connection ok";
        } else {
            return "connection error";
        }
    }
}