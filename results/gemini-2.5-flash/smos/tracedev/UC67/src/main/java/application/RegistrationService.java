package application;

import domain.IRegistrationRequestRepository;
import domain.RegistrationRequest;
import domain.RegistrationStatus;
import presentation.dto.RegistrationFormDTO;
import presentation.dto.RegistrationResultDTO;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Application Layer: Handles the core business logic for visitor registration.
 * Orchestrates validation, data creation, and persistence.
 */
public class RegistrationService {
    // Dependency: IRegistrationRequestRepository for data persistence
    private final IRegistrationRequestRepository registrationRequestRepository;

    /**
     * Constructor for RegistrationService.
     * Injects the IRegistrationRequestRepository dependency.
     *
     * @param registrationRequestRepository The repository for managing registration requests.
     */
    public RegistrationService(IRegistrationRequestRepository registrationRequestRepository) {
        this.registrationRequestRepository = registrationRequestRepository;
    }

    /**
     * Registers a new visitor based on the provided registration form data.
     * Includes validation, password hashing, and persistence.
     * Modified to satisfy recommendation 5.
     *
     * @param dto The RegistrationFormDTO containing the visitor's details.
     * @return A RegistrationResultDTO indicating the outcome of the registration attempt.
     */
    public RegistrationResultDTO registerVisitor(RegistrationFormDTO dto) {
        System.out.println("ApplicationService: Starting registration process for user " + dto.getUsername());

        // Perform validation (corresponding to internal validateForm message in sequence diagram)
        Map<String, String> errors = validateForm(dto);

        if (!errors.isEmpty()) {
            System.out.println("ApplicationService: Validation errors found.");
            // m12: validationFailed(errors)
            return new RegistrationResultDTO(false, "Validation errors occurred.", errors);
        }

        System.out.println("ApplicationService: All DTO validation successful.");

        // Hash password for security (R9, recommendation 3, and m16)
        String hashedPassword = hashPassword(dto.getPassword());
        System.out.println("ApplicationService: Password hashed.");

        // Create a new RegistrationRequest domain object (m17)
        RegistrationRequest registrationRequest = new RegistrationRequest(
                dto.getName(),
                dto.getSurname(),
                dto.getMobilePhone(),
                dto.getEmail(),
                dto.getUsername(),
                hashedPassword
        );
        registrationRequest.setStatus(RegistrationStatus.PENDING); // Initial status

        // Save the registration request using the repository
        try {
            registrationRequestRepository.save(registrationRequest);
            registrationRequest.setStatus(RegistrationStatus.REGISTERED); // Update status upon successful save
            System.out.println("ApplicationService: Registration request saved successfully.");
            // m25: registrationSuccess()
            return new RegistrationResultDTO(true, "Registration successful.");
        } catch (Exception e) {
            System.err.println("ApplicationService: Error saving registration request: " + e.getMessage());
            registrationRequest.setStatus(RegistrationStatus.FAILED); // Set status to failed on error
            errors.put("system", "Failed to save registration request due to an internal error.");
            // m12: validationFailed(errors) (in case of system error)
            return new RegistrationResultDTO(false, "Registration failed due to a system error.", errors);
        }
    }

    /**
     * Performs validation on the registration form DTO.
     * Corresponds to the internal `validateForm` message in the sequence diagram.
     *
     * @param dto The RegistrationFormDTO to validate.
     * @return A map of validation errors, where the key is the field name and the value is the error message.
     */
    private Map<String, String> validateForm(RegistrationFormDTO dto) {
        Map<String, String> errors = new HashMap<>();

        // Alt: password and confirmationPassword mismatch (from sequence diagram)
        // R4, R5 and recommendation 4
        if (!Objects.equals(dto.getPassword(), dto.getConfirmationPassword())) {
            errors.put("confirmationPassword", "Passwords do not match.");
        }

        // Alt: all other DTO validation fails (from sequence diagram)
        // Basic validation examples:
        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            errors.put("name", "Name cannot be empty.");
        }
        if (dto.getSurname() == null || dto.getSurname().trim().isEmpty()) {
            errors.put("surname", "Surname cannot be empty.");
        }
        if (dto.getMobilePhone() == null || dto.getMobilePhone().trim().isEmpty()) {
            errors.put("mobilePhone", "Mobile phone cannot be empty.");
        }
        if (dto.getEmail() == null || !dto.getEmail().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\\\.[A-Za-z]{2,6}$")) {
            errors.put("email", "Invalid email format.");
        }
        if (dto.getUsername() == null || dto.getUsername().trim().isEmpty()) {
            errors.put("username", "Username cannot be empty.");
        }
        // Check if username already exists (a common validation)
        if (registrationRequestRepository.findByUsername(dto.getUsername()) != null) {
            errors.put("username", "Username already taken.");
        }
        if (dto.getPassword() == null || dto.getPassword().length() < 6) {
            errors.put("password", "Password must be at least 6 characters long.");
        }
        return errors;
    }

    /**
     * Hashes the provided password.
     * This is a placeholder for a real hashing algorithm (e.g., BCrypt, PBKDF2).
     * Added to satisfy R9 and recommendation 3.
     * Corresponds to the internal `hash` message (m16) in the sequence diagram.
     *
     * @param password The plain text password to hash.
     * @return A hashed representation of the password.
     */
    private String hashPassword(String password) {
        // In a real application, use a strong, one-way hashing algorithm like BCrypt
        // For demonstration, we'll just append a suffix.
        return password + "_hashed_securely";
    }
}