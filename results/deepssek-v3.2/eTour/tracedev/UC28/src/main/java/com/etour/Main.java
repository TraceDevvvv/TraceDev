
package com.etour;

import com.etour.agency.AgencyOperator;
import com.etour.controller.TagController;
import com.etour.db.Database;
import com.etour.dto.DeleteTagsRequest;
import com.etour.dto.DeleteTagsResult;
import com.etour.monitoring.PerformanceMonitor;
import com.etour.tags.DatabaseTagRepository;
import com.etour.tags.Tag;
import com.etour.tags.TagRepository;
import com.etour.usecases.DeleteTagsUseCase;
import java.util.Arrays;
import java.util.List;

/**
 * Main class to demonstrate the flow of the Delete Tags use case.
 * Simulates the sequence diagram steps.
 */
public class Main {
    public static void main(String[] args) {
        // Setup dependencies
        Database database = new Database();
        TagRepository tagRepository = new DatabaseTagRepository(database);
        PerformanceMonitor monitor = new PerformanceMonitor();
        DeleteTagsUseCase useCase = new DeleteTagsUseCase(tagRepository, monitor);
        TagController controller = new TagController(useCase, tagRepository);

        // Simulate Agency Operator
        AgencyOperator operator = new AgencyOperator("user123", "John Doe");

        // Step 1: Access delete tags functionality (UI)
        System.out.println("=== Step 1: Show delete tags UI ===");
        List<Tag> tags = tagRepository.findAll();
        System.out.println("Available tags count: " + tags.size());

        // Step 2: Display tags in selectable form (UI logic, not shown in detail)

        // Step 3: Operator selects tags
        System.out.println("\n=== Step 3: Operator selects tags ===");
        List<String> selectedTagIds = Arrays.asList("tag1", "tag2", "tag3");
        controller.receiveTagSelection(selectedTagIds);

        // Step 4: Send deletion request
        System.out.println("\n=== Step 4: Send deletion request ===");
        DeleteTagsRequest request = new DeleteTagsRequest(operator.getUserId(), selectedTagIds);
        com.etour.usecases.DeleteTagsUseCase.DeleteTagsResult useCaseResult = controller.deleteTags(request);
        DeleteTagsResult result = new DeleteTagsResult(useCaseResult.isSuccess(), useCaseResult.getMessage(), useCaseResult.getDeletedCount());

        // Step 5/6/7: Result notification is already handled in controller.deleteTags()
        System.out.println("\n=== Final Result ===");
        System.out.println("Success: " + result.isSuccess());
        System.out.println("Message: " + result.getMessage());
        System.out.println("Deleted count: " + result.getDeletedCount());
    }
}
