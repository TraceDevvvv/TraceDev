import com.example.tagsystem.application.AuthenticationService;
import com.example.tagsystem.application.InsertTagService;
import com.example.tagsystem.domain.TagRepository;
import com.example.tagsystem.errorhandling.ErrorHandlingService;
import com.example.tagsystem.infrastructure.DatabaseTagRepository;
import com.example.tagsystem.infrastructure.ETOURSystem;
import com.example.tagsystem.presentation.TagController;

/**
 * Main class to demonstrate the "Insert new tag" use case based on the provided
 * Class and Sequence Diagrams.
 * This class sets up the application context (dependencies) and simulates the
 * interactions of an "Agency Operator".
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("--- Starting Tag Management System Simulation ---");

        // 1. Initialize Infrastructure Layer components
        DatabaseTagRepository databaseTagRepository = new DatabaseTagRepository();
        ETOURSystem etourSystem = new ETOURSystem();
        ErrorHandlingService errorHandlingService = new ErrorHandlingService();

        // 2. Initialize Domain Layer (interfaces are implemented by infrastructure)
        TagRepository tagRepository = databaseTagRepository; // Using the concrete implementation

        // 3. Initialize Application Layer components
        InsertTagService insertTagService = new InsertTagService(tagRepository, errorHandlingService);
        // AuthenticationService is not directly used in the sequence but is part of the system
        AuthenticationService authenticationService = new AuthenticationService();

        // 4. Initialize Presentation Layer components
        TagController tagController = new TagController(insertTagService, etourSystem);

        System.out.println("\n--- Precondition: Agency Operator is logged in ---");
        // Simulate authentication check (though not part of this specific sequence's flow)
        authenticationService.isAuthenticated("dummySessionId123");

        // --- Scenario 1: Successful Tag Insertion ---
        System.out.println("\n--- Scenario 1: Successful Tag Insertion ---");
        tagController.accessAndDisplayTagForm(); // AO -> Controller: accessAndDisplayTagForm()
        tagController.submitTagForm("Travel");   // AO -> Controller: submitTagForm("Travel")

        // --- Scenario 2: Inserting an existing Tag ---
        System.out.println("\n--- Scenario 2: Inserting an existing Tag (should fail) ---");
        tagController.accessAndDisplayTagForm();
        tagController.submitTagForm("java");     // AO -> Controller: submitTagForm("java") (case-insensitive check by repo)

        // --- Scenario 3: Inserting an invalid Tag (empty name) ---
        System.out.println("\n--- Scenario 3: Inserting an Invalid Tag (empty name - should fail) ---");
        tagController.accessAndDisplayTagForm();
        tagController.submitTagForm("");         // AO -> Controller: submitTagForm("")

        // --- Scenario 4: Inserting an invalid Tag (null name) ---
        System.out.println("\n--- Scenario 4: Inserting an Invalid Tag (null name - should fail) ---");
        tagController.accessAndDisplayTagForm();
        tagController.submitTagForm(null);       // AO -> Controller: submitTagForm(null)

        // --- Scenario 5: Another successful Tag Insertion ---
        System.out.println("\n--- Scenario 5: Another Successful Tag Insertion ---");
        tagController.accessAndDisplayTagForm();
        tagController.submitTagForm("Adventure"); // AO -> Controller: submitTagForm("Adventure")

        System.out.println("\n--- Tag Management System Simulation Finished ---");
    }
}