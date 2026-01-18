/**
 * Simulates a service layer for managing student registration requests.
 * It holds a collection of registrations and provides methods to interact with them,
 * such as retrieving pending requests and rejecting specific ones.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public class RegistrationService {
    // Database simulation: A map to store all registration requests by their ID
    private Map<String, StudentRegistration> allRegistrations;
    // Constructor initializes the "database"
    public RegistrationService() {
        this.allRegistrations = new HashMap<>();
    }
    /**
     * Adds a new student registration request to the system.
     * This method could be used by students to register or for initial data setup.
     * @param registration The StudentRegistration object to add.
     */
    public void addRegistration(StudentRegistration registration) {
        allRegistrations.put(registration.getId(), registration);
        System.out.println("New registration added: " + registration.getStudentName() + " (" + registration.getId() + ")");
    }
    /**
     * Retrieves a list of all pending student registration requests.
     * This simulates the "View Registration Requests" use case.
     * @return A List of StudentRegistration objects with "PENDING" status.
     */
    public List<StudentRegistration> getPendingRequests() {
        // Filters the map to return only those registrations that are currently PENDING
        return allRegistrations.values().stream()
                .filter(reg -> "PENDING".equals(reg.getStatus()))
                .collect(Collectors.toList());
    }
    /**
     * Rejects a specific student registration request by its ID.
     * This simulates the core functionality of the "RejectEnrollmentStudent" use case.
     * It eliminates the request from the system by removing it from the data store,
     * adhering to the use case requirement "1. Eliminates the registration request to the system."
     * @param registrationId The unique ID of the registration to reject.
     * @return true if the registration was found, was pending, and successfully eliminated, false otherwise.
     */
    public boolean rejectRegistration(String registrationId) {
        StudentRegistration registration = allRegistrations.get(registrationId);
        if (registration != null && "PENDING".equals(registration.getStatus())) {
            // Strictly adhere to "Eliminates" by removing the entry from the map
            allRegistrations.remove(registrationId);
            System.out.println("Registration " + registration.getId() + " - " + registration.getStudentName() + " has been REJECTED and ELIMINATED from the system.");
            return true; // Successfully rejected and eliminated
        } else if (registration != null) {
            // Registration found but not pending (e.g., already rejected or approved)
            System.out.println("Registration " + registration.getId() + " is not pending. Current status: " + registration.getStatus() + ". Cannot eliminate.");
        } else {
            // Registration not found
            System.out.println("Error: Registration with ID " + registrationId + " not found. Cannot eliminate.");
        }
        return false; // Failed to reject/eliminate
    }
}