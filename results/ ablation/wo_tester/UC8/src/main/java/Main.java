import adapters.*;
import application.*;
import domain.RefreshmentPoint;
import domain.Status;
import infrastructure.DataSource;
import infrastructure.ETOURServer;
import infrastructure.RefreshmentPointRepositoryImpl;
import interfaces.ConfirmationHandler;
import java.util.Arrays;

/**
 * Main class to run the system.
 */
public class Main {
    public static void main(String[] args) {
        // Setup infrastructure
        DataSource dataSource = new DataSource();
        ETOURServer server = new ETOURServer();
        RefreshmentPointRepositoryImpl repository = new RefreshmentPointRepositoryImpl(dataSource, server);

        // Populate with sample data
        RefreshmentPoint point1 = new RefreshmentPoint("1", "Cafe Central", "123 Main St", Status.ACTIVE, Arrays.asList("WiFi", "Restroom"));
        RefreshmentPoint point2 = new RefreshmentPoint("2", "Snack Bar", "456 Oak Ave", Status.MAINTENANCE, Arrays.asList("Parking"));
        repository.save(point1);
        repository.save(point2);

        // Setup application layer
        RefreshmentPointValidator validator = new RefreshmentPointValidator();
        EditRefreshmentPointUseCaseController useCaseController = new EditRefreshmentPointUseCaseController(repository, validator);
        SearchRefreshmentPointUseCase searchUseCase = new SearchRefreshmentPointUseCase(repository);

        // Setup adapters
        EditPointForm form = new EditPointForm();
        ConfirmationHandler confirmationDialog = new ConfirmationDialog();
        RefreshmentPointController controller = new RefreshmentPointController(useCaseController, searchUseCase, repository, form, confirmationDialog);

        // Simulate the sequence diagram flow
        System.out.println("=== Starting Refreshment Point Edit Flow ===");

        // Step 1: Agency Operator views point list
        System.out.println("1. Agency Operator views point list:");
        controller.displayPointList().forEach(dto -> System.out.println("   - " + dto.getName()));

        // Step 2: Select an active and functional point
        System.out.println("\n2. Selecting point with ID '1':");
        RefreshmentPointDTO selectedDTO = controller.selectPoint("1");
        if (selectedDTO != null) {
            System.out.println("   Point selected: " + selectedDTO.getName());
            controller.showEditForm(selectedDTO);
        } else {
            System.out.println("   Error: Point not editable.");
            return;
        }

        // Step 3: Agency Operator changes data in form
        System.out.println("\n3. Agency Operator changes data in form:");
        selectedDTO.setName("Cafe Central Updated");
        selectedDTO.setAddress("123 Main St, Suite 100");
        form.setFormData(selectedDTO);
        form.updateFormFields();

        // Step 4: Submit form
        System.out.println("\n4. Submitting form:");
        OperationResult result = controller.submitEditForm(selectedDTO);
        System.out.println("   Result: " + result.getMessage() + " (Success: " + result.isSuccess() + ")");

        // Step 5: Display updated list
        System.out.println("\n5. Updated point list:");
        controller.displayPointList().forEach(dto -> System.out.println("   - " + dto.getName()));

        System.out.println("\n=== Flow Completed ===");
    }
}