
package com.example;

import com.example.controller.AgencyOperatorController;
import com.example.repository.TouristRepository;
import com.example.repository.TouristRepositoryImpl;
import com.example.service.NotificationService;
import com.example.usecase.DeleteTouristUseCase;
import javax.sql.DataSource;

/**
 * Main class to run the application and demonstrate the sequence.
 */
public class Main {
    public static void main(String[] args) {
        // Setup DataSource (using H2 for demonstration)
        // Note: Using DataSource interface instead of concrete H2 class
        DataSource dataSource = createDataSource();

        // Create repository
        TouristRepository repository = new TouristRepositoryImpl(dataSource);

        // Create NotificationService
        NotificationService notificationService = new NotificationService();

        // Create use case
        DeleteTouristUseCase useCase = new DeleteTouristUseCase(repository, notificationService);

        // Create controller
        AgencyOperatorController controller = new AgencyOperatorController(useCase);

        // Simulate the sequence diagram flow
        System.out.println("=== Sequence Diagram Simulation ===");

        // Entry Condition: Verify login
        boolean loggedIn = controller.verifyLogin();
        System.out.println("Login confirmed: " + loggedIn);

        // Step 1 & 2: Search tourists
        System.out.println("\nStep 1 & 2: Searching tourists...");
        controller.activateSearchTourist();
        var tourists = controller.searchTourist();
        System.out.println("Tourists found: " + tourists.size());

        // Step 3: Assume operator selects a tourist (id "1")
        String selectedId = "1";
        System.out.println("\nStep 3: Tourist selected with ID: " + selectedId);
        controller.selectTourist(selectedId);

        // Step 4: Request deletion
        System.out.println("\nStep 4: Requesting deletion...");
        controller.activateDisposalFeature();
        controller.requestDelete(selectedId);

        // Step 5 & 6: Confirm deletion
        System.out.println("\nStep 5 & 6: Confirming deletion...");
        controller.showConfirmationDialog();
        controller.confirmOperation();
        boolean success = controller.confirmDelete(selectedId);
        System.out.println("Deletion successful: " + success);

        // Step 7 and Quality Requirement are handled inside DeleteTouristUseCase
        controller.showSuccessNotification();
        System.out.println("\nSequence completed.");
    }

    private static DataSource createDataSource() {
        // This is a stub implementation since we can't use H2 directly
        // In a real application, you would use a proper DataSource implementation
        return new DataSource() {
            @Override
            public java.sql.Connection getConnection() {
                return null;
            }

            @Override
            public java.sql.Connection getConnection(String username, String password) {
                return null;
            }

            @Override
            public java.io.PrintWriter getLogWriter() {
                return null;
            }

            @Override
            public void setLogWriter(java.io.PrintWriter out) {
            }

            @Override
            public int getLoginTimeout() {
                return 0;
            }

            @Override
            public void setLoginTimeout(int seconds) {
            }

            @Override
            public java.util.logging.Logger getParentLogger() {
                return null;
            }

            @Override
            public <T> T unwrap(Class<T> iface) {
                return null;
            }

            @Override
            public boolean isWrapperFor(Class<?> iface) {
                return false;
            }
        };
    }
}
