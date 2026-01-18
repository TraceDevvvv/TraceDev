
package com.example;

import com.example.ui.ChangePasswordUI;
import com.example.controller.ModifyPasswordController;
import com.example.service.PasswordService;
import com.example.repository.AgencyRepository;
import com.example.repository.AgencyRepositoryImpl;
import com.example.component.SessionManager;
import com.example.component.CircuitBreaker;
import com.example.dto.ModifyPasswordRequest;
import com.example.dto.ModifyPasswordResponse;
import javax.sql.DataSource;

/**
 * Main application class to demonstrate the system.
 * Creates all necessary components and runs the password change flow.
 */
public class MainApplication {
    public static void main(String[] args) {
        // Initialize components
        PasswordService passwordService = new PasswordService();
        SessionManager sessionManager = new SessionManager();
        CircuitBreaker circuitBreaker = new CircuitBreaker();
        
        // Create a mock DataSource (in real app, this would be configured)
        DataSource dataSource = createMockDataSource();
        AgencyRepository agencyRepository = new AgencyRepositoryImpl(dataSource);
        
        ModifyPasswordController controller = new ModifyPasswordController(
            passwordService, agencyRepository, sessionManager, circuitBreaker
        );
        
        ChangePasswordUI ui = new ChangePasswordUI(controller);
        
        System.out.println("=== Starting Password Change Demo ===");
        
        // Demo 1: Successful password change
        System.out.println("\n--- Demo 1: Successful Password Change ---");
        ui.onChangePasswordButtonClicked();
        
        // Demo 2: Failed password change (passwords don't match)
        System.out.println("\n--- Demo 2: Passwords Don't Match ---");
        ModifyPasswordRequest badRequest = new ModifyPasswordRequest(
            "agency123", "oldPassword123", "newPassword456", "differentPassword"
        );
        ModifyPasswordResponse badResponse = ui.submit(badRequest);
        ui.displayErrorMessage(badResponse.getMessage());
        
        // Demo 3: Simulate connection failure
        System.out.println("\n--- Demo 3: Simulating Connection Failure ---");
        // This would require modifying the repository to always throw exception
        // For this demo, we'll just show the error handling path
        System.out.println("Error handling for connection failures is implemented via CircuitBreaker");
        
        System.out.println("\n=== Demo Complete ===");
    }
    
    private static DataSource createMockDataSource() {
        // Return null DataSource for demo purposes since Spring is not available
        // In a real application, you would properly configure a DataSource
        return null;
    }
}
