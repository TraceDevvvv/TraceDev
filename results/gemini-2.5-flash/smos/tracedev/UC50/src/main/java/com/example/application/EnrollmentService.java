package com.example.application;

import com.example.domain.Enrollment;
import com.example.domain.RegistrationRequest;
import com.example.domain.Student;
import com.example.domain.User;
import com.example.infrastructure.*;
import com.example.presentation.AdministratorUI; // Import the UI class

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Service class responsible for handling enrollment-related business logic.
 * It orchestrates the creation of Student, User, and Enrollment entities
 * based on accepted registration requests. It also handles error scenarios
 * like connection failures to external systems (simulated by SMOSConnectionException).
 */
public class EnrollmentService {
    private final IStudentRepository studentRepository;
    private final IRegistrationRequestRepository registrationRequestRepository;
    private final IUserRepository userRepository;
    private final IEnrollmentRepository enrollmentRepository;

    /**
     * Constructor for EnrollmentService, injecting necessary repositories.
     * @param studentRepository Repository for Student entities.
     * @param registrationRequestRepository Repository for RegistrationRequest entities.
     * @param userRepository Repository for User entities.
     * @param enrollmentRepository Repository for Enrollment entities.
     */
    public EnrollmentService(IStudentRepository studentRepository,
                             IRegistrationRequestRepository registrationRequestRepository,
                             IUserRepository userRepository,
                             IEnrollmentRepository enrollmentRepository) {
        this.studentRepository = studentRepository;
        this.registrationRequestRepository = registrationRequestRepository;
        this.userRepository = userRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    /**
     * Accepts a student enrollment request. This method orchestrates the entire
     * acceptance process, including marking the request, creating student, user,
     * and enrollment records. It handles potential `SMOSConnectionException`
     * during the update of the registration request.
     *
     * @param requestId The ID of the registration request to accept.
     * @param administratorId The ID of the administrator performing the acceptance.
     * @return The updated list of pending registration requests after the operation.
     *         This return type is inferred from the sequence diagram's flow back to the UI.
     *         However, the class diagram shows a void return for acceptEnrollment.
     *         To align with the sequence diagram's interaction, this method will
     *         internally call UI methods or throw an exception, and the UI will then
     *         call getPendingRegistrationRequests independently.
     *         For simplicity and strictly following the CD, it returns void, and UI
     *         will explicitly call getPendingRegistrationRequests() after this call.
     *         The sequence diagram implies that `enrollmentAcceptedConfirmation()` and
     *         `enrollmentFailed()` are directly called *by* the service on the UI.
     *         This requires `AdministratorUI` to be passed as a dependency or an event system.
     *         For simplicity, I will pass the UI as a dependency in the acceptEnrollment method
     *         to simulate this direct interaction as shown in the sequence diagram.
     *         This implies a change in the EnrollmentService's method signature from CD.
     *         Assumption: The `AdministratorUI` is passed as a callback/dependency
     *         to allow the service to directly notify the UI.
     *         If the class diagram was strictly followed (void return, no UI dependency),
     *         the UI would need to query the service for success/failure after the call.
     *         To match sequence diagram's explicit UI calls, the UI is passed.
     */
    public void acceptEnrollment(String requestId, String administratorId, AdministratorUI ui) {
        System.out.println("\n[Service] Accepting enrollment for request ID: " + requestId + " by admin: " + administratorId);

        // 1. Orchestrate enrollment acceptance
        RegistrationRequest registrationRequest = registrationRequestRepository.findById(requestId);

        if (registrationRequest == null) {
            System.out.println("[Service] Error: Registration request not found for ID: " + requestId);
            ui.enrollmentFailed("Registration request not found.");
            return;
        }

        if (registrationRequest.getStatus() != com.example.domain.RegistrationStatus.PENDING) {
            System.out.println("[Service] Error: Registration request " + requestId + " is not pending. Current status: " + registrationRequest.getStatus());
            ui.enrollmentFailed("Registration request is not pending. Current status: " + registrationRequest.getStatus());
            return;
        }

        // Apply state change to the domain object
        registrationRequest.markAsAccepted();

        try {
            // Attempt to persist the updated registration request
            registrationRequestRepository.update(registrationRequest);

            // 2. System activates the new user & records enrollment
            Date currentDate = new Date();

            // Create Student
            String newStudentId = generateUniqueId("student");
            Student newStudent = Student.create(newStudentId,
                                                registrationRequest.getStudentName(),
                                                registrationRequest.getStudentEmail(),
                                                currentDate);
            studentRepository.save(newStudent);

            // Create User
            String newUserId = generateUniqueId("user");
            User newUser = User.create(newUserId, newStudent.getEmail(), newStudent.getStudentId());
            newUser.activate(); // Activate the user as per sequence diagram
            userRepository.save(newUser);

            // Create Enrollment
            String newEnrollmentId = generateUniqueId("enrollment");
            Enrollment newEnrollment = Enrollment.create(newEnrollmentId,
                                                         newStudent.getStudentId(),
                                                         currentDate,
                                                         administratorId);
            enrollmentRepository.save(newEnrollment);

            System.out.println("[Service] Enrollment process completed successfully for request ID: " + requestId);
            ui.enrollmentAcceptedConfirmation(); // Notify UI of success

        } catch (SMOSConnectionException e) {
            System.err.println("[Service] Failed to accept enrollment due to SMOS connection issue for request ID: " + requestId);
            System.err.println("Error: " + e.getMessage());
            // Rollback the status change in the domain object in case of persistence failure
            // (Note: in a real system, this might involve transaction management)
            // For this example, we assume the in-memory object is still updated, but persistence failed.
            // If we want a full rollback, we'd need to reload the request or have transaction.
            // For now, we simply report failure.
            ui.enrollmentFailed(e.getMessage()); // Notify UI of failure
        }
    }

    /**
     * Retrieves a list of all pending registration requests.
     * @return A list of RegistrationRequest objects that are in PENDING status.
     */
    public List<RegistrationRequest> getPendingRegistrationRequests() {
        System.out.println("[Service] Fetching all pending registration requests.");
        return registrationRequestRepository.findAllPending();
    }

    /**
     * Helper method to generate unique IDs.
     * @param prefix A prefix to make the ID more descriptive (e.g., "student-").
     * @return A unique String ID.
     */
    private String generateUniqueId(String prefix) {
        return prefix + "-" + UUID.randomUUID().toString();
    }
}