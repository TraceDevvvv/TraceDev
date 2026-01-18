package application;

/**
 * Application service for managing workflow states and use case execution.
 * Added to satisfy REQ-003.
 */
public class WorkflowService {

    /**
     * Checks if a specific use case has been carried out by a user.
     * This is a mock implementation for demonstration.
     * @param useCaseName The name of the use case (e.g., "DisplayedUnapagella").
     * @param userId The ID of the user.
     * @return true if the use case has been carried out, false otherwise.
     */
    public boolean hasUseCaseBeenCarriedOut(String useCaseName, String userId) {
        // In a real system, this would involve checking a workflow state or history.
        // For this mock, we'll assume "DisplayedUnapagella" is always true for any user.
        System.out.println("DEBUG: WorkflowService: hasUseCaseBeenCarriedOut(\"" + useCaseName + "\", " + userId + ") -> true (mock)");
        return true; // Always true for demonstration purposes, as per SD preconditions.
    }
}