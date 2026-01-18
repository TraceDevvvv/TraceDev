import actor.Administrator;
import application.DeleteUserUseCase;
import domain.AuthenticationService;
import domain.UserService;
import infrastructure.SMOSClient;
import infrastructure.UserRepository;
import infrastructure.UserRepositoryImpl;
import presentation.DeleteUserController;
import presentation.DeleteUserView;

/**
 * Main class to simulate the entire delete user flow as per the sequence diagram.
 * Creates all objects and runs the scenario.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Starting Delete User Use Case Simulation ===\n");

        // 1. Setup infrastructure
        UserRepository repository = new UserRepositoryImpl();
        SMOSClient smos = new SMOSClient();
        smos.connect();

        // 2. Setup domain serv
        AuthenticationService authService = new AuthenticationService(repository);
        UserService userService = new UserService(repository, smos);

        // 3. Setup application layer
        DeleteUserUseCase useCase = new DeleteUserUseCase(userService, repository, smos);

        // 4. Setup presentation layer
        DeleteUserController controller = new DeleteUserController(null, useCase, authService);
        DeleteUserView view = new DeleteUserView(controller);
        // Inject view into controller (circular dependency for simplicity)
        controller = new DeleteUserController(view, useCase, authService);

        // 5. Create actor
        Administrator admin = new Administrator();

        // --- Simulate the sequence diagram ---

        // Precondition: login (entry condition)
        System.out.println("\n--- Step 1: Administrator logs in ---");
        admin.login("admin123", authService);

        // Precondition: view details (simulated by rendering a user)
        System.out.println("\n--- Step 2: System displays user details (precondition) ---");
        view.renderUserDetails(repository.findById("U001"));

        // Main flow: delete button click - using the sequence diagram message
        System.out.println("\n--- Step 3: Administrator clicks delete button ---");
        admin.clicksDeleteButton(view);
        // Also trigger the actual delete flow
        admin.triggerDeleteUser(view, "U001");

        // Optional logout
        System.out.println("\n--- Step 4: Administrator logs out ---");
        admin.logout(authService);

        System.out.println("\n=== Simulation Complete ===");
    }
}