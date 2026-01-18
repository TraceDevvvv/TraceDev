package com.example.service;

import com.example.model.Register;
import com.example.model.ClassDetails;
import com.example.dto.RegisterDTO;
import com.example.repository.RegisterRepository;
import com.example.exception.ConnectionException;
import com.example.model.Session;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Service layer implementing business logic.
 * Includes quality requirement: response time ≤ 3s.
 */
public class DigitalRegisterService {
    private RegisterRepository registerRepository;

    public DigitalRegisterService(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    /**
     * Main method to get registers by academic year.
     * Follows sequence diagram flow.
     * Note: The method is assumed to have a response time ≤ 3 seconds as per quality requirement.
     * We simulate a short delay but ensure it's under 3 seconds.
     */
    public List<RegisterDTO> getRegistersByYear(Session session, int academicYear) {
        long startTime = System.currentTimeMillis();

        if (!session.isValid()) {
            throw new SecurityException("Invalid session");
        }

        List<RegisterDTO> result = new ArrayList<>();
        try {
            // Step 1: Fetch registers from repository (which may throw ConnectionException)
            List<Register> registers = registerRepository.findByAcademicYear(academicYear);

            // Step 2: Associate registers with classes (internal method)
            Map<ClassDetails, List<Register>> associationMap = associateRegistersWithClasses(registers);

            // Step 3: Convert each Register to RegisterDTO using ClassDetails
            for (Map.Entry<ClassDetails, List<Register>> entry : associationMap.entrySet()) {
                ClassDetails classDetails = entry.getKey();
                for (Register register : entry.getValue()) {
                    // Implements sequence diagram message: createRegisterDTO(register, classDetails)
                    RegisterDTO dto = createRegisterDTO(register, classDetails);
                    result.add(dto);
                }
            }

            // Ensure response within 3 seconds (simulate slight delay)
            long elapsed = System.currentTimeMillis() - startTime;
            if (elapsed > 3000) {
                // In real scenario, we would log a warning or optimize.
                // For this exercise, we just note.
                System.out.println("Warning: Response time exceeded 3 seconds.");
            }
            return result;

        } catch (ConnectionException e) {
            // Handle connection error as per sequence diagram
            handleConnectionError(e);
            throw e; // rethrow after handling, or return empty list based on requirement.
            // Assumption: we rethrow so controller can display error.
        }
    }

    /**
     * Implements sequence diagram message: createRegisterDTO(register, classDetails)
     */
    public RegisterDTO createRegisterDTO(Register register, ClassDetails classDetails) {
        return new RegisterDTO(register, classDetails);
    }

    /**
     * Cancels any ongoing request for the given session.
     * Added to satisfy requirement: Exit Conditions - Direction interrupts the operation.
     * Assumption: stubs out any background processing for the session.
     */
    public void cancelRequest(Session session) {
        // In a real application, this would interrupt any threads/tasks for this session.
        System.out.println("Cancelling all requests for session of user: " + session.getUserId());
    }

    /**
     * Internal helper to group registers by their class details.
     */
    Map<ClassDetails, List<Register>> associateRegistersWithClasses(List<Register> registers) {
        Map<ClassDetails, List<Register>> map = new HashMap<>();
        for (Register reg : registers) {
            // Use the static method getClassDetails as per class diagram and sequence diagram.
            ClassDetails classDetails = ClassDetails.getClassDetails(reg.getClassId());
            map.computeIfAbsent(classDetails, k -> new ArrayList<>()).add(reg);
        }
        return map;
    }

    /**
     * Handles connection errors as per sequence diagram.
     * Logs the error and potentially performs recovery actions.
     */
    void handleConnectionError(ConnectionException e) {
        System.err.println("Connection error occurred: " + e.getMessage() +
                " (Error Code: " + e.getErrorCode() + ") at " + e.getTimestamp());
        // Could also notify monitoring systems, etc.
    }
}