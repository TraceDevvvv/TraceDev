package com.agency;

import com.agency.presentation.ChangePasswordForm;
import com.agency.application.ChangePasswordController;
import com.agency.domain.BcryptPasswordStrategy;
import com.agency.domain.IPasswordStrategy;
import com.agency.infrastructure.AgencyRepositoryImpl;
import com.agency.infrastructure.IAgencyRepository;
import com.agency.infrastructure.PasswordValidator;

/**
 * Main class to simulate the Change Password use case.
 * Creates all objects and runs the scenario as per the sequence diagram.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Change Password Use Case Simulation ===");

        // Instantiate infrastructure components
        IPasswordStrategy strategy = new BcryptPasswordStrategy();
        PasswordValidator validator = new PasswordValidator();
        IAgencyRepository repository = new AgencyRepositoryImpl();

        // Instantiate presentation and controller
        ChangePasswordController controller = new ChangePasswordController(null, strategy, validator, repository);
        ChangePasswordForm form = new ChangePasswordForm(controller);
        controller = new ChangePasswordController(form, strategy, validator, repository); // re‑create with form

        // Step 1: Agency Operator presses "Change Password" button
        // Simulate user entering passwords (old: "oldPass123", new: "NewPass456", confirm: "NewPass456")
        form.setPasswordFields("oldPass123", "NewPass456", "NewPass456");

        // Step 2 & 3: Form displays and user submits
        form.displayForm();
        form.onPasswordChangeRequest();

        // Note: The above will trigger the entire flow as per sequence diagram.
        // The outcome depends on the simulated connection error in AgencyRepositoryImpl.
        // You can run multiple times to see both success and connection error scenarios.
    }
}