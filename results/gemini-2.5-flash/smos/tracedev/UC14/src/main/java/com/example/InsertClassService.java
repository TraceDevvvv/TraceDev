package com.example;

import java.util.List;

// Application Service for inserting a new Class.
public class InsertClassService {
    private IClassRepository classRepository;
    private ClassValidator classValidator;
    private ErrorNotificationService errorNotificationService;

    /**
     * Constructor for InsertClassService, injecting its dependencies.
     *
     * @param classRepository The repository for Class objects.
     * @param classValidator The validator for ClassCreationDTO.
     * @param errorNotificationService The service for error notifications.
     */
    public InsertClassService(IClassRepository classRepository,
                              ClassValidator classValidator,
                              ErrorNotificationService errorNotificationService) {
        this.classRepository = classRepository;
        this.classValidator = classValidator;
        this.errorNotificationService = errorNotificationService;
        System.out.println("InsertClassService initialized.");
    }

    /**
     * Creates a new Class based on the provided DTO.
     * This method orchestrates validation, domain object creation, and persistence,
     * including error handling and notifications (R15, R12).
     *
     * @param dto The ClassCreationDTO containing the data for the new class.
     * @return A list of error messages if validation fails, or an empty list on success.
     */
    public List<String> createClass(ClassCreationDTO dto) {
        System.out.println("InsertClassService: Attempting to create class from DTO...");

        // Step 5: Validate the DTO
        List<String> validationErrors = classValidator.validate(dto);

        if (!validationErrors.isEmpty()) {
            // Step 7: Data is Not Valid
            System.out.println("InsertClassService: Validation failed. Notifying and activating Errodati.");
            errorNotificationService.activateErrodatiUseCase(); // R12
            return validationErrors; // Return errors to the controller
        } else {
            // Step 6: Data is Valid
            System.out.println("InsertClassService: Validation successful. Converting DTO to domain object.");
            Class domainClass = dto.toDomainClass();

            try {
                // Step 6: Persist the class via repository, which also interacts with SMOSGateway (R15)
                System.out.println("InsertClassService: Saving domain Class via repository.");
                classRepository.save(domainClass);
                System.out.println("InsertClassService: Class created and saved successfully: " + domainClass.getName());
                return validationErrors; // Empty list indicating success
            } catch (SMOSConnectionException e) {
                // Handle SMOS server connection interruption (R15)
                System.err.println("InsertClassService: SMOS Connection Exception caught: " + e.getMessage());
                errorNotificationService.notifyAdminOfError("SMOS server connection lost: " + e.getMessage()); // R15
                validationErrors.add("Operation failed: " + e.getMessage()); // Add error for controller to display
                return validationErrors;
            }
        }
    }
}