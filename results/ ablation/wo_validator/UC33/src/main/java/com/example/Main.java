
package com.example;

import com.example.infrastructure.connection.ServerConnection;
import com.example.infrastructure.persistence.AccountRepositoryImpl;
import com.example.infrastructure.notification.NotificationServiceImpl;
import com.example.infrastructure.error.ErrorHandlerImpl;
import com.example.application.usecases.RegistrationInteractor;
import com.example.application.usecases.ErroredUseCase;
import com.example.interfaceadapters.controllers.RegistrationController;
import com.example.interfaceadapters.ui.RegistrationForm;
import com.example.application.dto.RegistrationRequest;

/**
 * Main class to simulate the registration flow as per sequence diagram.
 * This is a runnable simulation of the use case.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Registration Flow Simulation ===\n");

        // 1. Setup infrastructure components.
        ServerConnection connection = new ServerConnection();
        connection.connect();

        AccountRepositoryImpl repository = new AccountRepositoryImpl(connection);
        NotificationServiceImpl notifier = new NotificationServiceImpl();
        ErrorHandlerImpl errorHandler = new ErrorHandlerImpl();

        // 2. Create use case interactor with error use case.
        ErroredUseCase erroredUseCase = new ErroredUseCase(errorHandler);
        RegistrationInteractor interactor = new RegistrationInteractor(repository, notifier, erroredUseCase);

        // 3. Create controller.
        RegistrationController controller = new RegistrationController(interactor, errorHandler);

        // 4. Simulate the sequence diagram steps.

        // Guest User enables logging.
        controller.enableLogging();

        // Guest User requests registration form.
        RegistrationForm form = controller.showRegistrationForm();

        // Guest User fills out the form.
        form.fillOutForm();

        // Guest User submits the form.
        form.submitForm();

        // Form collects data.
        RegistrationRequest request = form.collectData();

        // Guest User confirms.
        boolean confirmed = form.showConfirmation(request);
    }
}
