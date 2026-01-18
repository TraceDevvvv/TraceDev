import application.RegistrationService;
import domain.IRegistrationRequestRepository;
import infrastructure.SqlRegistrationRequestRepository;
import presentation.RegistrationController;
import presentation.dto.RegistrationFormDTO;
import presentation.dto.RegistrationResultDTO;

/**
 * Main application runner to demonstrate the visitor registration use case.
 * Simulates the "Visitor" and "Registration Form UI" interactions.
 */
public class ApplicationRunner {
    public static void main(String[] args) {
        System.out.println("--- Starting Visitor Registration Application ---");

        // 1. Setup the application dependencies (Dependency Injection)
        // Infrastructure Layer
        IRegistrationRequestRepository registrationRequestRepository = new SqlRegistrationRequestRepository();

        // Application Layer
        RegistrationService registrationService = new RegistrationService(registrationRequestRepository);

        // Presentation Layer
        RegistrationController registrationController = new RegistrationController(registrationService);

        // 2. Simulate Visitor's Actions and UI Interactions

        // Entry Condition: Visitor HAS clicked "Register" button
        System.out.println("\nVisitor: Clicks Register button.");

        // UI --> Visitor : displayRegistrationForm()
        registrationController.displayRegistrationForm();

        // Scenario 1: Successful Registration
        System.out.println("\n--- Scenario 1: Successful Registration ---");
        // Visitor -> UI : fillsForm(...)
        RegistrationFormDTO successfulForm = new RegistrationFormDTO(
                "John",
                "Doe",
                "123-456-7890",
                "john.doe@example.com",
                "john.doe",
                "Password123",
                "Password123"
        );
        System.out.println("Visitor: Fills form with valid details.");

        // Visitor -> UI : submitsForm()
        System.out.println("Visitor: Submits form.");
        // UI -> RegistrationController : submitRegistration(registrationFormDTO)
        RegistrationResultDTO result1 = registrationController.submitRegistration(successfulForm);

        System.out.println("\n--- Scenario 1 Result Verification ---");
        System.out.println("Registration Successful: " + result1.getSuccess());
        System.out.println("Message: " + result1.getMessage());

        // Scenario 2: Password Mismatch
        System.out.println("\n--- Scenario 2: Password Mismatch ---");
        RegistrationFormDTO mismatchForm = new RegistrationFormDTO(
                "Jane",
                "Smith",
                "987-654-3210",
                "jane.smith@example.com",
                "jane.smith",
                "Password123",
                "DifferentPassword" // Mismatched password
        );
        System.out.println("Visitor: Fills form with mismatched passwords.");
        System.out.println("Visitor: Submits form.");
        RegistrationResultDTO result2 = registrationController.submitRegistration(mismatchForm);

        System.out.println("\n--- Scenario 2 Result Verification ---");
        System.out.println("Registration Successful: " + result2.getSuccess());
        System.out.println("Message: " + result2.getMessage());
        System.out.println("Errors: " + result2.getErrors());

        // Scenario 3: Invalid Email Format
        System.out.println("\n--- Scenario 3: Invalid Email Format ---");
        RegistrationFormDTO invalidEmailForm = new RegistrationFormDTO(
                "Alice",
                "Johnson",
                "555-111-2222",
                "alice.invalid-email", // Invalid email
                "alice.j",
                "SecurePass1",
                "SecurePass1"
        );
        System.out.println("Visitor: Fills form with an invalid email address.");
        System.out.println("Visitor: Submits form.");
        RegistrationResultDTO result3 = registrationController.submitRegistration(invalidEmailForm);

        System.out.println("\n--- Scenario 3 Result Verification ---");
        System.out.println("Registration Successful: " + result3.getSuccess());
        System.out.println("Message: " + result3.getMessage());
        System.out.println("Errors: " + result3.getErrors());

        // Scenario 4: Duplicate Username
        System.out.println("\n--- Scenario 4: Duplicate Username ---");
        RegistrationFormDTO duplicateUsernameForm = new RegistrationFormDTO(
                "Another",
                "User",
                "555-333-4444",
                "another.user@example.com",
                "john.doe", // Duplicate username
                "NewPass456",
                "NewPass456"
        );
        System.out.println("Visitor: Fills form with an already taken username.");
        System.out.println("Visitor: Submits form.");
        RegistrationResultDTO result4 = registrationController.submitRegistration(duplicateUsernameForm);

        System.out.println("\n--- Scenario 4 Result Verification ---");
        System.out.println("Registration Successful: " + result4.getSuccess());
        System.out.println("Message: " + result4.getMessage());
        System.out.println("Errors: " + result4.getErrors());


        System.out.println("\n--- Application Simulation Ended ---");
    }
}