
package hec.controllers;

import hec.ports.AuthenticationService;
import hec.ports.InsertTagInputPort;
import hec.usecase.InsertTagRequest;
import hec.usecase.InsertTagResponse;
import hec.usecase.InsertTagUseCase;
import hec.dto.InsertTagResponseDTO;

/**
 * Controller for handling tag insertion requests.
 * Implements the input port and orchestrates authentication and use case execution.
 */
public class InsertTagController implements InsertTagInputPort {
    private final InsertTagUseCase insertTagUseCase;
    private final AuthenticationService authenticationService;

    /**
     * Constructor.
     *
     * @param useCase      the use case instance
     * @param authService  the authentication service
     */
    public InsertTagController(InsertTagUseCase useCase, AuthenticationService authService) {
        this.insertTagUseCase = useCase;
        this.authenticationService = authService;
    }

    /**
     * Handles a tag insertion request
     * @param request the insert tag request
     * @return the insert tag response
     */
    @Override
    public InsertTagResponse insertTag(InsertTagRequest request) {
        return insertTagUseCase.execute(request);
    }

    /**
     * Handles the HTTP request for inserting a tag.
     *
     * @param name        the tag name
     * @param description the tag description
     * @return ResponseEntity containing InsertTagResponse
     */
    public Object handleInsertTag(String name, String description) {
        // Check authentication
        String currentUser = authenticationService.getCurrentUser();
        if (!authenticationService.isLoggedIn(currentUser)) {
            // Return error response for not authenticated
            InsertTagResponseDTO errorResponse = new InsertTagResponseDTO(false, null, "Not Authenticated");
            return errorResponse;
        }

        // Build InsertTagRequest from parameters
        InsertTagRequest request = new InsertTagRequest(name, description);

        // Validate request data
        if (!request.isValid()) {
            // Return error response for invalid data
            InsertTagResponseDTO errorResponse = new InsertTagResponseDTO(false, null, "Invalid Data");
            return errorResponse;
        }

        // Execute use case
        InsertTagResponse useCaseResponse = insertTagUseCase.execute(request);

        // Map to DTO for HTTP response
        InsertTagResponseDTO responseDTO = new InsertTagResponseDTO(
            useCaseResponse.isSuccess(),
            useCaseResponse.getTagId(),
            useCaseResponse.getMessage()
        );

        return responseDTO;
    }
}
