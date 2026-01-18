import controller.TagSearchController;
import domain.TagRepository;
import application.*;
import infrastructure.*;
import presentation.TagFormDTO;
import presentation.InsertTagResultDTO;

/**
 * Main class to simulate the sequence diagram flow.
 * This is a runnable example; in reality, dependencies would be injected via Spring or similar.
 */
public class Main {
    public static void main(String[] args) {
        // Setup infrastructure
        ConnectionManager connectionManager = new ConnectionManager("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        TagRepository tagRepository = new TagRepositoryImpl(connectionManager);
        
        // Setup application layer
        TagService tagService = new TagService(tagRepository);
        ExistingErrorTagUseCase existingErrorTagUseCase = new ExistingErrorTagUseCase();
        ErroredUseCase erroredUseCase = new ErroredUseCase();
        
        // Setup controller
        TagSearchController controller = new TagSearchController(tagService, existingErrorTagUseCase, erroredUseCase);
        
        // Simulate Agency Operator submitting a tag
        TagFormDTO tagData = new TagFormDTO();
        tagData.setName("Java");
        tagData.setDescription("Programming language");
        
        System.out.println("Submitting tag: " + tagData.getName());
        InsertTagResultDTO result = controller.insertTagSearch(tagData);
        System.out.println("Result: " + result.isSuccess() + " - " + result.getMessage());
        
        // Try to submit same tag again to trigger duplicate error flow
        System.out.println("\nSubmitting duplicate tag: " + tagData.getName());
        InsertTagResultDTO duplicateResult = controller.insertTagSearch(tagData);
        System.out.println("Result: " + duplicateResult.isSuccess() + " - " + duplicateResult.getMessage());
    }
}