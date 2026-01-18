import java.util.HashMap;
import java.util.Map;

/**
 * Main class to demonstrate the runnable system.
 */
public class Main {
    public static void main(String[] args) {
        // Setup components
        ErrorHandler errorHandler = new ErrorHandler();
        AuthenticationService authService = new AuthenticationService();
        // Authenticate user (pre‑condition)
        authService.authenticate(new Credentials("admin", "pass"));

        CacheManager cacheManager = new CacheManager();
        EtourServerAdapter adapter = new EtourServerAdapter("http://etour.example.com", errorHandler);
        RefreshmentPointRepository repository = new RefreshmentPointRepositoryImpl(adapter);
        ViewRefreshmentPointCardService service = new ViewRefreshmentPointCardService(repository, cacheManager, errorHandler);
        RefreshmentPointController controller = new RefreshmentPointController(service, authService, errorHandler);
        SearchRefreshmentPointUseCase searchUseCase = new DummySearchUseCase();
        RefreshmentPointView view = new RefreshmentPointView(controller, searchUseCase);

        System.out.println("=== Scenario: View Refreshment Point Card ===");
        view.selectRefreshmentPoint("123");

        System.out.println("\n=== Scenario: Upload Data (REQ-007) ===");
        Map<String, Object> updateData = new HashMap<>();
        updateData.put("name", "Updated Cafe");
        updateData.put("description", "Renovated");
        updateData.put("location", "Uptown");
        view.triggerUploadData("123", updateData);

        System.out.println("\n=== Scenario: Error Handling (simulated) ===");
        // Force a connection failure by making adapter return empty (already random)
        // We'll just call again and may see error if random failure occurs
        view.selectRefreshmentPoint("999");
    }
}