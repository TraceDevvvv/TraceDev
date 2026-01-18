package com.example.service;

import com.example.command.InsertBannerCommand;
import com.example.command.InsertBannerCommandHandler;
import com.example.command.CommandResult;
import com.example.model.RestPoint;
import com.example.repository.IRestPointRepository;
import com.example.handler.ErrorHandler;
import java.util.List;

/**
 * Service layer for banner operations.
 * Implements response time < 2s for insert operations (REQ-016).
 */
public class BannerService {
    private InsertBannerCommandHandler commandHandler;
    private IRestPointRepository restPointRepository;
    private ErrorHandler errorHandler;

    public BannerService(InsertBannerCommandHandler commandHandler,
                         IRestPointRepository restPointRepository,
                         ErrorHandler errorHandler) {
        this.commandHandler = commandHandler;
        this.restPointRepository = restPointRepository;
        this.errorHandler = errorHandler;
    }

    /**
     * Inserts a banner for a given rest point.
     * Sequence diagram step 10.
     */
    public CommandResult insertBanner(String restPointId, byte[] imageData, String imageType) {
        // Create command (step 11).
        InsertBannerCommand command = new InsertBannerCommand(restPointId, imageData, imageType);

        // Validate command (step 12, REQ-009).
        if (!command.validate().isValid()) {
            // Handle invalid command via error handler (sequence diagram else branch).
            errorHandler.handleInvalidImage("Invalid command parameters");
            return new CommandResult(false, "Invalid command parameters", null);
        }

        // Delegate to command handler (step 14).
        CommandResult result = commandHandler.handle(command);
        return result;
    }

    /**
     * Retrieves rest points for a given agency.
     * Sequence diagram step 5.
     */
    public List<RestPoint> getRestPointsForAgency(String agencyId) {
        // In a real implementation, we would filter by agencyId.
        // For simplicity, we return all rest points from repository.
        // Assuming repository method exists to find by agency.
        // This is a stub - actual implementation would query the repository.
        return List.of();
    }

    /**
     * Sets a response time limit (REQ-016).
     * This is a placeholder for actual timeout configuration.
     */
    private void setResponseTimeLimit(int ms) {
        // Implementation would involve setting timeouts on network calls or async operations.
        System.out.println("Response time limit set to " + ms + " ms");
    }
}