
package com.etour.controller;

import com.etour.agency.AgencyOperator;
import com.etour.tags.Tag;
import com.etour.tags.TagRepository;
import com.etour.usecases.DeleteTagsUseCase;
import com.etour.dto.DeleteTagsRequest;
import java.util.List;

/**
 * Controller for tag-related operations.
 * Orchestrates UI interactions and use case execution.
 * Implements flow steps 1, 3, and 4.
 */
public class TagController {
    private DeleteTagsUseCase deleteTagsUseCase;
    private TagRepository tagRepository;

    public TagController(DeleteTagsUseCase deleteTagsUseCase, TagRepository tagRepository) {
        this.deleteTagsUseCase = deleteTagsUseCase;
        this.tagRepository = tagRepository;
    }

    /**
     * Step 1: Show delete tags UI – retrieves all tags for display.
     */
    public List<Tag> showTags() {
        return tagRepository.findAll();
    }

    /**
     * Step 3: Receive tag selection from UI.
     * This method would typically be called by the UI when the operator selects tags.
     */
    public void receiveTagSelection(List<String> tagIds) {
        // In a real implementation, this might store the selection or trigger the next step.
        System.out.println("Received tag selection: " + tagIds);
    }

    /**
     * Step 4: Delete tags – creates request and invokes use case.
     */
    public com.etour.usecases.DeleteTagsUseCase.DeleteTagsResult deleteTags(DeleteTagsRequest request) {
        // In a real system, we would fetch the AgencyOperator from a session or service.
        // For this example, create a mock operator using the actorId.
        AgencyOperator operator = new AgencyOperator(request.getActorId(), "Operator Name");

        // Execute use case
        com.etour.usecases.DeleteTagsUseCase.DeleteTagsResult result = deleteTagsUseCase.execute(operator, request.getTagIds());

        // Notify operator based on result
        if (result.isSuccess()) {
            operator.receiveNotification("Successfully deleted " + result.getDeletedCount() + " tag(s).");
        } else {
            operator.receiveNotification("Deletion failed: " + result.getMessage());
        }

        return result;
    }
}
