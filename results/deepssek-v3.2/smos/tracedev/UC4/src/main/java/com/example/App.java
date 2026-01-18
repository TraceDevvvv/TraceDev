
package com.example;

import com.example.controller.ChangePasswordController;
import com.example.dto.ChangePasswordRequest;
import com.example.dto.ChangePasswordResponse;
import com.example.form.PasswordChangeForm;
import com.example.repository.UserRepository;
import com.example.repository.UserRepositoryImpl;
import com.example.service.PasswordService;
import com.example.service.PasswordServiceImpl;
import com.example.usecase.ChangePasswordUseCase;
import javax.sql.DataSource;
import java.io.PrintWriter;

/**
 * Main application class that demonstrates the flow.
 * Simulates a simple DataSource for demonstration.
 */
public class App {
    public static void main(String[] args) {
        System.out.println("=== Password Change Demo ===\n");

        // Setup (in a real app, use dependency injection)
        DataSource mockDataSource = new MockDataSource();
        UserRepository userRepository = new UserRepositoryImpl(mockDataSource);
        PasswordService passwordService = new PasswordServiceImpl();
        ChangePasswordUseCase useCase = new ChangePasswordUseCase(userRepository, passwordService);
        ChangePasswordController controller = new ChangePasswordController(useCase);

        // 1. Show form
        System.out.println("Password change form shown");

        // 2. Simulate form submission with correct old password
        System.out.println("\n--- Scenario 1: Successful password change ---");
        PasswordChangeForm form = new PasswordChangeForm();
        form.oldPassword = "oldpass123"; // matches initial hash in repository
        form.newPassword = "newpass456"; // meets regex requirement
        form.confirmPassword = "newpass456";
        controller.submitPasswordChangeForm(form);

        // 3. Simulate API call directly
        System.out.println("\n--- Scenario 2: Direct use case call (old password incorrect) ---");
        ChangePasswordRequest request = new ChangePasswordRequest("john_doe", "wrong", "newpass789", "newpass789");
        ChangePasswordResponse response = controller.changePassword(request);
        System.out.println("Response: " + response.getMessage());

        // 4. Simulate user cancellation
        System.out.println("\n--- Scenario 3: User cancels ---");
        System.out.println("Password change cancelled by user");

        // 5. Simulate disconnection
        System.out.println("\n--- Scenario 4: Connection interruption ---");
        System.out.println("Connection interrupted - handling disconnection");

        System.out.println("\n=== Demo complete ===");
    }

    // Mock DataSource for demonstration
    static class MockDataSource implements DataSource {
        @Override
        public java.sql.Connection getConnection() {
            return null;
        }

        @Override
        public java.sql.Connection getConnection(String username, String password) {
            return null;
        }

        @Override
        public PrintWriter getLogWriter() {
            return null;
        }

        @Override
        public void setLogWriter(PrintWriter out) {
        }

        @Override
        public void setLoginTimeout(int seconds) {
        }

        @Override
        public int getLoginTimeout() {
            return 0;
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
    }
}
