package com.example.app;

import com.example.app.application.AuthenticationService;
import com.example.app.application.CoutentiViewingService;
import com.example.app.application.UserCreationService;
import com.example.app.dataaccess.InMemoryUserRepository;
import com.example.app.domain.PasswordHasher;
import com.example.app.domain.UserValidator;
import com.example.app.dtos.UserCreationRequestDTO;
import com.example.app.infrastructure.ConnectionMonitor;
import com.example.app.infrastructure.ConnectionStatus;
import com.example.app.presentation.UserFormController;
import com.example.app.presentation.UserFormView;
import com.example.app.presentation.UserListView;

/**
 * Main class to simulate the application flow based on the Sequence Diagram.
 * This class acts as the bootstrap and simulates the 'Admin' actor's interactions.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("--- Starting Application Simulation ---");

        // 1. Initialize Infrastructure Components
        ConnectionMonitor connectionMonitor = new ConnectionMonitor();

        // 2. Initialize Data Access Layer Components
        InMemoryUserRepository userRepository = new InMemoryUserRepository();

        // 3. Initialize Domain Layer Components
        UserValidator userValidator = new UserValidator();
        PasswordHasher passwordHasher = new PasswordHasher();

        // 4. Initialize Application Layer Components
        UserCreationService userCreationService = new UserCreationService(userRepository, userValidator, passwordHasher);
        CoutentiViewingService coutentiViewingService = new CoutentiViewingService(); // R4
        AuthenticationService authenticationService = new AuthenticationService(); // R3

        // 5. Initialize Presentation Layer Components
        UserFormView userFormView = new UserFormView();
        UserFormController userFormController = new UserFormController(userFormView, userCreationService, authenticationService, connectionMonitor);
        UserListView userListView = new UserListView(coutentiViewingService); // R4 dependency
        userListView.setUserFormController(userFormController); // R6 dependency injection
        userFormController.setUserListView(userListView); // R6 dependency injection

        System.out.println("\n--- Simulation Scenario 1: Successful User Creation ---");

        // m1: UseCase "viewing theCoutenti" IS executed.
        System.out.println("[Admin Note] UseCase \"viewing theCoutenti\" IS executed. (m1)");
        // Admin -> CVS : viewCoutenti() (R4)
        userListView.viewCoutenti(); // UserListView uses CoutentiViewingService

        // m3: Administrator IS logged in. System IS viewing the list of users.
        System.out.println("\n[Admin Note] Administrator IS logged in. System IS viewing the list of users. (m3)");

        // Admin -> ULS : handleNewUserButtonClick() (R6)
        userListView.handleNewUserButtonClick();

        // UFC -> UEF : displayEmptyForm()
        // (Handled internally by handleNewUserClick calling view.displayEmptyForm())

        // Admin fillsFormData and clicksSaveButton (UEF -> UFC : getUserInput() & UEF --> UFC : userCreationRequest)
        UserCreationRequestDTO validRequest = new UserCreationRequestDTO(
                "John", "Doe", "john.doe@example.com", "1234567890",
                "johndoe", "SecurePass123", "SecurePass123"
        );
        userFormView.clicksSaveButton(validRequest); // Simulate Admin input and save click

        // UCS -> UV : validateUserCreation(request)
        // UCS -> PH : hashPassword(request.password)
        // UCS -> User **: new User(...)
        // UCS -> URepo : save(user)
        // (All handled by userFormController.handleSaveButtonClick calling userCreationService.createUser)

        // m26: A new user IS created. -> covered by userFormView.displaySuccessMessage() which prints "[Admin] User creation confirmed."


        System.out.println("\n--- Simulation Scenario 2: Failed User Creation (Validation Errors) ---");
        // m5: Administrator IS logged in. System IS viewing the list of users.
        System.out.println("[Admin Note] Administrator IS logged in. System IS viewing the list of users. (m5)");
        // Simulate Admin input with invalid data (e.g., mismatched passwords)
        UserCreationRequestDTO invalidRequest = new UserCreationRequestDTO(
                "Jane", "Smith", "jane@example.com", "0987654321",
                "janesmith", "WeakPass", "MismatchedPass" // Mismatched passwords and weak password
        );
        userListView.handleNewUserButtonClick(); // Open form again
        userFormView.clicksSaveButton(invalidRequest); // Simulate Admin input and save click

        // m31: Data error IS notified. -> covered by userFormView.displayValidationErrors()


        System.out.println("\n--- Simulation Scenario 3: Failed User Creation (Duplicate Login) ---");
        // Simulate Admin input with a login that already exists
        UserCreationRequestDTO duplicateLoginRequest = new UserCreationRequestDTO(
                "Another", "User", "another@example.com", "1112223333",
                "johndoe", "AnotherPass123", "AnotherPass123" // Duplicate login: johndoe
        );
        userListView.handleNewUserButtonClick(); // Open form again
        userFormView.clicksSaveButton(duplicateLoginRequest); // Simulate Admin input and save click
        // m31: Data error IS notified (duplicate login). -> covered by userFormView.displayValidationErrors()


        System.out.println("\n--- Simulation Scenario 4: Failed User Creation (Connection Interrupted) ---");
        ConnectionMonitor.setConnectionStatus(ConnectionStatus.INTERRUPTED); // Simulate connection loss

        UserCreationRequestDTO connectionLostRequest = new UserCreationRequestDTO(
                "Connect", "Test", "connect@example.com", "4445556666",
                "connectuser", "ConnectionPass123", "ConnectionPass123"
        );
        userListView.handleNewUserButtonClick(); // Open form again
        userFormView.clicksSaveButton(connectionLostRequest); // Simulate Admin input and save click

        // m33: Session terminated due to connection loss. -> covered by userFormView.displayConnectionLostNotification()

        // m32: CM -> Admin: notifyConnectionLost()
        // ConnectionMonitor.handleSystemDisconnect is called to explicitly trace this message from CM.
        // It implies the system has detected a disconnect and is reporting it.
        connectionMonitor.handleSystemDisconnect(authenticationService.getCurrentAdminLogin());


        // Reset connection status for further potential operations
        ConnectionMonitor.setConnectionStatus(ConnectionStatus.STABLE);

        System.out.println("\n--- Current Users in Repository ---");
        userListView.displayUserList(userRepository.findAll());

        System.out.println("\n--- Simulation Ended ---");
    }
}