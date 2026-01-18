package com.example.school;

/**
 * Controller responsible for handling requests related to viewing class registries.
 * It interacts with the ClassRegistryApplicationService to retrieve and process data.
 */
public class ClassRegistryController {
    private final ClassRegistryApplicationService classRegistryService;

    /**
     * Constructs a ClassRegistryController with its application service dependency.
     * @param service The ClassRegistryApplicationService instance.
     */
    public ClassRegistryController(ClassRegistryApplicationService service) {
        this.classRegistryService = service;
    }

    /**
     * Retrieves the class registry data for a given class ID.
     *
     * @param classId The ID of the class.
     * @return A ClassRegistryDTO containing aggregated class information.
     * @throws SMOSConnectionException if an SMOS connection error occurs during data retrieval (ExC2).
     */
    public ClassRegistryDTO viewClassRegistry(String classId) throws SMOSConnectionException {
        System.out.println("[Controller] Request to view class registry for classId: " + classId);
        // The application service directly throws SMOSConnectionException.
        // The controller will rethrow it if not caught by an alt block in the calling method.
        // For this simplified example, the controller just passes it through as per sequence diagram.
        return classRegistryService.getClassRegistryData(classId);
    }

    /**
     * Handles SMOS connection errors and converts them into an ErrorViewModel.
     * Added to satisfy requirement ExC2.
     * @param exception The SMOSConnectionException that occurred.
     * @return An ErrorViewModel containing a user-friendly error message.
     */
    public ErrorViewModel handleSMOSConnectionError(SMOSConnectionException exception) {
        System.err.println("[Controller] Handling SMOSConnectionException: " + exception.getMessage());
        return new ErrorViewModel("Failed to connect to SMOS server. Please try again later.");
    }
}