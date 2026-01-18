package com.example.school;

import java.util.List;

/**
 * Controller responsible for handling requests related to listing classes.
 * It interacts with the ClassListApplicationService to retrieve class data.
 * Added to satisfy requirement EC3.
 */
public class ClassListController {
    private final ClassListApplicationService classListService;

    /**
     * Constructs a ClassListController with its application service dependency.
     * Added to satisfy requirement EC3.
     * @param service The ClassListApplicationService instance.
     */
    public ClassListController(ClassListApplicationService service) {
        this.classListService = service;
    }

    /**
     * Retrieves a list of classes for a given professor.
     * Added to satisfy requirement EC3, ExC2.
     *
     * @param professorId The ID of the professor.
     * @return A list of ClassDTOs.
     * @throws SMOSConnectionException if an SMOS connection error occurs during data retrieval.
     */
    public List<ClassDTO> getProfessorClasses(String professorId) throws SMOSConnectionException {
        System.out.println("[ClassListController] Request to get classes for professorId: " + professorId);
        // The application service directly throws SMOSConnectionException.
        // The controller will rethrow it if not caught by an alt block in the calling method.
        // For this simplified example, the controller just passes it through as per sequence diagram.
        return classListService.getProfessorClasses(professorId);
    }

    /**
     * Handles SMOS connection errors and converts them into an ErrorViewModel.
     * Added to satisfy requirement ExC2.
     * @param exception The SMOSConnectionException that occurred.
     * @return An ErrorViewModel containing a user-friendly error message.
     */
    public ErrorViewModel handleSMOSConnectionError(SMOSConnectionException exception) {
        System.err.println("[ClassListController] Handling SMOSConnectionException: " + exception.getMessage());
        return new ErrorViewModel("Failed to connect to SMOS server to load classes. Please try again later.");
    }
}