package hec.usecase;

import hec.core.ConnectionException;
import hec.core.Tag;
import hec.ports.AuthenticationService;
import hec.ports.TagRepository;

/**
 * Use case for inserting a new Tag.
 * Orchestrates validation, duplication check, persistence, and response building.
 */
public class InsertTagUseCase {
    private final TagRepository tagRepository;
    private final AuthenticationService authenticationService;

    /**
     * Constructor with dependencies.
     *
     * @param repository        the Tag repository
     * @param authService       the authentication service
     */
    public InsertTagUseCase(TagRepository repository, AuthenticationService authService) {
        this.tagRepository = repository;
        this.authenticationService = authService;
    }

    /**
     * Executes the insert tag use case.
     *
     * @param insertTagRequest the request containing tag data
     * @return the response indicating success or failure
     */
    public InsertTagResponse execute(InsertTagRequest insertTagRequest) {
        // Step 1: Validate input data.
        if (!insertTagRequest.isValid()) {
            // Trigger error handling for invalid data.
            new ErroredUseCase().handleInvalidData("Invalid data");
            return new InsertTagResponse(false, null, "Invalid Data");
        }

        // Step 2: Check for duplicate tag.
        try {
            boolean exists = tagRepository.existsByName(insertTagRequest.getName());
            if (exists) {
                // Trigger duplicate tag handling.
                new ExistingErrorTagUseCase().handleDuplicateTag(insertTagRequest.getName());
                return new InsertTagResponse(false, null, "Tag already exists");
            }
        } catch (ConnectionException e) {
            // Handle connection error.
            handleConnectionError(e);
            return new InsertTagResponse(false, null, "Connection Error");
        }

        // Step 3: Create new Tag.
        Tag newTag = new Tag(insertTagRequest.getName(), insertTagRequest.getDescription());

        // Step 4: Persist the Tag.
        try {
            Tag savedTag = tagRepository.save(newTag);
            // Build success response.
            return buildSuccessResponse(savedTag);
        } catch (ConnectionException e) {
            // Handle connection error during save.
            handleConnectionError(e);
            return new InsertTagResponse(false, null, "Connection Error");
        }
    }

    /**
     * Handles connection error.
     *
     * @param e the connection exception
     */
    private void handleConnectionError(ConnectionException e) {
        // Simulate handling connection error
        System.err.println("Connection error handled: " + e.getMessage());
    }

    /**
     * Builds a success response.
     *
     * @param savedTag the saved tag
     * @return the success response
     */
    private InsertTagResponse buildSuccessResponse(Tag savedTag) {
        return new InsertTagResponse(true, savedTag.getId(), "Tag inserted successfully");
    }
}