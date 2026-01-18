package com.example.monitoring.application;

import com.example.monitoring.domain.Student;
import com.example.monitoring.domain.SchoolYear;
import com.example.monitoring.domain.ThresholdConfiguration;
import com.example.monitoring.infrastructure.DatabaseConnectionException;
import com.example.monitoring.infrastructure.IStudentRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Application Layer: Handles the business logic for student monitoring.
 * Orchestrates operations between the UI, domain, and infrastructure layers.
 */
public class StudentMonitoringService {
    // Dependencies injected through the constructor
    private IStudentRepository studentRepository;
    private ThresholdConfiguration thresholdConfiguration;
    private AuthService authService; // Added to satisfy requirements: User IS logged in, User HAS Administrator role

    /**
     * Constructor for StudentMonitoringService.
     * @param studentRepository The repository for student data access.
     * @param thresholdConfiguration The configuration for absence and notes thresholds.
     * @param authService The authentication/authorization service.
     */
    public StudentMonitoringService(IStudentRepository studentRepository,
                                    ThresholdConfiguration thresholdConfiguration,
                                    AuthService authService) {
        this.studentRepository = studentRepository;
        this.thresholdConfiguration = thresholdConfiguration;
        this.authService = authService;
    }

    /**
     * Retrieves a list of students who exceed the configured absence and notes thresholds.
     * This method orchestrates the full flow as described in the sequence diagram.
     *
     * @return A list of {@link StudentMonitoringDTO} for students exceeding thresholds.
     * @throws DatabaseConnectionException if there's an issue connecting to the database.
     * @throws SecurityException if the user is not authenticated or does not have the 'Administrator' role.
     */
    public List<StudentMonitoringDTO> getStudentsExceedingThresholds() throws DatabaseConnectionException, SecurityException {
        System.out.println("Service: getStudentsExceedingThresholds() called.");

        // 1. Check user authentication and role (as per sequence diagram)
        // Assumption: The role "Administrator" is a constant in the system.
        if (!authService.checkAuthenticationAndRole("Administrator")) {
            // If not authorized, throw a SecurityException
            throw new SecurityException("Access Denied: User is not authorized for this operation.");
        }
        System.out.println("Service: User is authorized as 'Administrator'.");

        // 2. Get thresholds from ThresholdConfiguration
        int absenceThreshold = thresholdConfiguration.getAbsenceThreshold();
        int notesThreshold = thresholdConfiguration.getNotesThreshold();
        System.out.println("Service: Thresholds retrieved (Absence: " + absenceThreshold + ", Notes: " + notesThreshold + ").");

        // 3. Get the current school year
        SchoolYear currentSchoolYear = new SchoolYear(SchoolYear.getCurrentYear()); // Wrap in SchoolYear object
        System.out.println("Service: Current school year retrieved: " + currentSchoolYear.getYear() + ".");

        List<Student> students;
        try {
            // 4. Retrieve students from the repository who exceed thresholds
            System.out.println("Service: Requesting students from repository...");
            students = studentRepository.findStudentsExceedingThresholds(currentSchoolYear, absenceThreshold, notesThreshold);
            System.out.println("Service: " + students.size() + " students retrieved from repository.");
        } catch (DatabaseConnectionException e) {
            // Re-throw the exception to the UI layer for appropriate handling
            System.err.println("Service: Database connection error occurred, re-throwing.");
            throw e;
        }

        // 5. Map the retrieved Student entities to StudentMonitoringDTOs
        System.out.println("Service: Mapping Student entities to StudentMonitoringDTOs...");
        List<StudentMonitoringDTO> monitoringData = mapStudentsToDTOs(students);
        System.out.println("Service: " + monitoringData.size() + " DTOs created.");

        return monitoringData;
    }

    /**
     * Private helper method to transform a list of {@link Student} entities
     * into a list of {@link StudentMonitoringDTO} objects.
     * This method handles the data transformation logic.
     *
     * @param students The list of Student entities to map.
     * @return A list of StudentMonitoringDTOs.
     */
    private List<StudentMonitoringDTO> mapStudentsToDTOs(List<Student> students) {
        // Using Java Streams for concise and efficient mapping
        return students.stream()
                .map(student -> new StudentMonitoringDTO(
                        student.getStudentId(),
                        student.getName(),
                        student.getAbsences(),
                        student.getNotes()))
                .collect(Collectors.toList());
    }
}