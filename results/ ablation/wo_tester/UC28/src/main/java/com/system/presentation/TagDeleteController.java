package com.system.presentation;

import com.system.application.DeleteTagUseCase;
import com.system.authentication.AuthenticationService;
import com.system.dtos.ResponseDTO;
import com.system.dtos.TagDTO;
import com.system.validation.TagValidator;
import com.system.ui.TagDeleteForm;
import java.util.List;

/**
 * Presentation layer controller for tag deletion.
 */
public class TagDeleteController {
    private DeleteTagUseCase deleteTagUseCase;
    private AuthenticationService authenticationService;
    private TagValidator tagValidator;
    private TagDeleteForm tagDeleteForm;

    public TagDeleteController(DeleteTagUseCase useCase, AuthenticationService authService,
                               TagValidator validator, TagDeleteForm form) {
        this.deleteTagUseCase = useCase;
        this.authenticationService = authService;
        this.tagValidator = validator;
        this.tagDeleteForm = form;
    }

    public boolean isUserAuthenticated() {
        // For simplicity, we assume a session ID is stored somewhere.
        // Here we just validate that the current user exists.
        return authenticationService.getCurrentUser() != null;
    }

    public List<TagDTO> showTagDeleteForm() {
        // Step 1-3: Access functionality, research tags, and display them.
        List<TagDTO> tags = deleteTagUseCase.getExistingTags();
        tagDeleteForm.displayTags(tags);
        return tags;
    }

    public ResponseDTO handleDeleteRequest(List<String> tagIds) {
        // Step 5-6: Validate and process deletion request.
        if (!validateTagIds(tagIds)) {
            tagDeleteForm.showErrorMessage("No valid tags selected");
            return ResponseDTO.createErrorResponse("No valid tags selected");
        }

        try {
            ResponseDTO response = deleteTagUseCase.deleteTags(tagIds);
            if (response.isSuccess()) {
                notifySuccessfulElimination(response);
            } else {
                displayError(response.getMessage());
            }
            return response;
        } catch (Exception e) {
            displayError(e.getMessage());
            return ResponseDTO.createErrorResponse(e.getMessage());
        }
    }

    private boolean validateTagIds(List<String> tagIds) {
        return tagValidator.validateTagIds(tagIds);
    }

    public void notifySuccessfulElimination(ResponseDTO response) {
        tagDeleteForm.showSuccessMessage("Tags successfully eliminated");
    }

    public void displayError(String message) {
        tagDeleteForm.showErrorMessage(message);
    }
}