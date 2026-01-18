package com.example.application.service;

import com.example.application.dto.TagSearchDTO;
import com.example.application.entity.TagEntity;
import com.example.application.repository.TagRepository;

/**
 * Service for handling tag search insertion.
 */
public class TagSearchService {
    private TagRepository tagRepository;
    private NotificationService notificationService;
    private ErrorHandler errorHandler;
    private AuthenticationService authenticationService;
    private ServerConnector serverConnector;

    public TagSearchService(TagRepository tagRepository,
                            NotificationService notificationService,
                            ErrorHandler errorHandler,
                            AuthenticationService authenticationService,
                            ServerConnector serverConnector) {
        this.tagRepository = tagRepository;
        this.notificationService = notificationService;
        this.errorHandler = errorHandler;
        this.authenticationService = authenticationService;
        this.serverConnector = serverConnector;
    }

    public Result insertTagSearch(TagSearchDTO dto) {
        // Entry condition: Check authentication
        if (!authenticationService.isLoggedIn(dto.getOperatorId())) {
            errorHandler.showLoginError();
            return new Result(false, "User not logged in");
        }

        // Exit condition: Check server connection
        boolean connectionOK = serverConnector.checkConnection();
        if (!connectionOK) {
            // m14: Set error result
            errorHandler.handleGeneralError(new Exception("Connection interrupted"));
            return new Result(false, "Errored: Connection interrupted");
        }

        // Step 7: Validate data
        if (!validateData(dto)) {
            // m18-m19: Show validation error and set error result
            errorHandler.handleValidationError("Invalid data");
            return new Result(false, "Validation failed");
        }

        // Step 8-9: Check if tag already exists
        if (tagRepository.exists(dto.getTag())) {
            // m10: handleExistingTagError
            // m11: Show "ExistingErrorTag" notification
            // m12: Set error result
            errorHandler.handleExistingTagError(dto.getTag());
            return new Result(false, "ExistingErrorTag: Tag already exists");
        }

        // Step 13: Create and save tag entity
        TagEntity tagEntity = createTagEntity(dto);
        tagRepository.save(tagEntity);

        // Step 14: Send notification
        notificationService.sendInclusionNotification(dto.getTag());

        // Step 16: Set success result
        return new Result(true, "Tag successfully added");
    }

    public boolean validateData(TagSearchDTO dto) {
        // Validate that tag is not null or empty
        return dto != null && dto.getTag() != null && !dto.getTag().trim().isEmpty();
    }

    public TagEntity createTagEntity(TagSearchDTO dto) {
        // Create new TagEntity with tag, operatorId and timestamp
        return new TagEntity(dto.getTag(), dto.getOperatorId(), dto.getTimestamp());
    }
}