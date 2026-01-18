package com.editteachingsystem;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * TeachingService class manages teaching operations including:
 * - Editing teaching details
 * - Validating data
 * - Updating the archive
 * - Handling errors according to the use case
 * 
 * This service implements the core business logic for the Edit Teaching use case.
 */
public class TeachingService {
    
    // In-memory archive to store teachings (simulating database/archive)
    private Map<String, Teaching> teachingArchive;
    private SMOSServerConnection smosConnection;
    private AtomicBoolean isOperationInterrupted;
    
    /**
     * Constructor initializes the service with necessary dependencies
     * @param smosConnection connection to SMOS server
     */
    public TeachingService(SMOSServerConnection smosConnection) {
        this.teachingArchive = new ConcurrentHashMap<>();
        this.smosConnection = smosConnection;
        this.isOperationInterrupted = new AtomicBoolean(false);
        initializeSampleData();
    }
    
    /**
     * Initializes the service with sample teaching data for demonstration
     */
    private void initializeSampleData() {
        Teaching teaching1 = new Teaching(
            "TEA-100001", 
            "CS101", 
            "Introduction to Computer Science",
            "INS-001",
            "Dr. John Smith",
            "MWF 9:00-10:30",
            "Room 101, Science Building",
            50,
            45,
            "ACTIVE"
        );
        
        Teaching teaching2 = new Teaching(
            "TEA-100002", 
            "MATH201", 
            "Calculus II",
            "INS-002",
            "Prof. Jane Doe",
            "TTH 11:00-12:30",
            "Room 205, Math Building",
            40,
            38,
            "ACTIVE"
        );
        
        Teaching teaching3 = new Teaching(
            "TEA-100003", 
            "PHY101", 
            "Physics I",
            "INS-003",
            "Dr. Robert Johnson",
            "MWF 14:00-15:30",
            "Lab 3, Physics Building",
            35,
            30,
            "INACTIVE"
        );
        
        teachingArchive.put(teaching1.getTeachingId(), teaching1);
        teachingArchive.put(teaching2.getTeachingId(), teaching2);
        teachingArchive.put(teaching3.getTeachingId(), teaching3);
    }
    
    /**
     * Main method for editing teaching details. Implements the use case sequence:
     * 1. Validates the entered data
     * 2. Updates teaching in archive if valid
     * 3. Handles errors and SMOS server communication
     * 
     * @param teachingId the ID of the teaching to edit
     * @param updatedTeaching the teaching object with updated details
     * @return EditResult containing the result status and any error messages
     */
    public EditResult editTeaching(String teachingId, Teaching updatedTeaching) {
        // Check if operation was interrupted by administrator
        if (isOperationInterrupted.get()) {
            return EditResult.interrupted("Operation was interrupted by administrator");
        }
        
        // Check if teaching exists
        if (!teachingArchive.containsKey(teachingId)) {
            return EditResult.error("Teaching with ID " + teachingId + " not found in archive");
        }
        
        // Check SMOS server connection
        if (!smosConnection.isConnected()) {
            return EditResult.error("Connection to SMOS server interrupted. Cannot edit teaching.");
        }
        
        // Validate the updated teaching data
        List<String> validationErrors = validateTeachingData(updatedTeaching);
        if (!validationErrors.isEmpty()) {
            // Activate error case "Errodati" as specified in use case
            return EditResult.validationError("Data validation errors occurred", validationErrors);
        }
        
        try {
            // Get the original teaching for comparison
            Teaching originalTeaching = teachingArchive.get(teachingId);
            
            // Ensure teaching ID cannot be changed (it's the primary key)
            if (!originalTeaching.getTeachingId().equals(updatedTeaching.getTeachingId())) {
                return EditResult.error("Teaching ID cannot be changed");
            }
            
            // Update the teaching in archive
            boolean updateSuccess = updateTeachingInArchive(teachingId, updatedTeaching);
            
            if (!updateSuccess) {
                return EditResult.error("Failed to update teaching in archive");
            }
            
            // Log the change to SMOS server
            try {
                smosConnection.logChange("Teaching updated: " + teachingId);
            } catch (SMOSConnectionException e) {
                // Log the error but continue since teaching was updated
                System.err.println("Warning: Could not log change to SMOS server: " + e.getMessage());
            }
            
            // Return success result with updated teachings list
            return EditResult.success(getAllTeachings(), 
                "Teaching updated successfully. ID: " + teachingId);
                
        } catch (Exception e) {
            return EditResult.error("Unexpected error occurred while editing teaching: " + e.getMessage());
        }
    }
    
    /**
     * Updates the teaching in the archive (simulating database update)
     * @param teachingId the teaching ID
     * @param updatedTeaching the updated teaching object
     * @return true if successful, false otherwise
     */
    private boolean updateTeachingInArchive(String teachingId, Teaching updatedTeaching) {
        try {
            // Simulate database/archive update
            Teaching teachingCopy = updatedTeaching.copy();
            teachingArchive.put(teachingId, teachingCopy);
            
            // Simulate potential archive update failure
            Random random = new Random();
            if (random.nextDouble() < 0.05) { // 5% chance of simulated failure
                throw new RuntimeException("Simulated archive update failure");
            }
            
            return true;
        } catch (Exception e) {
            System.err.println("Error updating teaching in archive: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Validates teaching data according to business rules
     * @param teaching the teaching to validate
     * @return list of validation error messages (empty if valid)
     */
    public List<String> validateTeachingData(Teaching teaching) {
        List<String> errors = new ArrayList<>();
        
        // Validate teaching ID
        if (!Teaching.validateTeachingId(teaching.getTeachingId())) {
            errors.add("Invalid Teaching ID format. Expected: TEA-XXXXXX");
        }
        
        // Validate course code
        if (!Teaching.validateCourseCode(teaching.getCourseCode())) {
            errors.add("Invalid Course Code format. Expected: ABC123");
        }
        
        // Validate course name
        if (teaching.getCourseName() == null || teaching.getCourseName().trim().isEmpty()) {
            errors.add("Course name cannot be empty");
        } else if (teaching.getCourseName().length() > 100) {
            errors.add("Course name must be less than 100 characters");
        }
        
        // Validate instructor ID
        if (teaching.getInstructorId() == null || teaching.getInstructorId().trim().isEmpty()) {
            errors.add("Instructor ID cannot be empty");
        } else if (teaching.getInstructorId().length() > 20) {
            errors.add("Instructor ID must be less than 20 characters");
        }
        
        // Validate instructor name
        if (teaching.getInstructorName() == null || teaching.getInstructorName().trim().isEmpty()) {
            errors.add("Instructor name cannot be empty");
        } else if (teaching.getInstructorName().length() > 50) {
            errors.add("Instructor name must be less than 50 characters");
        }
        
        // Validate schedule
        if (!Teaching.validateSchedule(teaching.getSchedule())) {
            errors.add("Invalid schedule format or schedule is empty");
        }
        
        // Validate location
        if (teaching.getLocation() == null || teaching.getLocation().trim().isEmpty()) {
            errors.add("Location cannot be empty");
        } else if (teaching.getLocation().length() > 50) {
            errors.add("Location must be less than 50 characters");
        }
        
        // Validate max students
        if (teaching.getMaxStudents() <= 0) {
            errors.add("Max students must be greater than 0");
        } else if (teaching.getMaxStudents() > 500) {
            errors.add("Max students cannot exceed 500");
        }
        
        // Validate current enrollment
        if (teaching.getCurrentEnrollment() < 0) {
            errors.add("Current enrollment cannot be negative");
        }
        if (teaching.getCurrentEnrollment() > teaching.getMaxStudents()) {
            errors.add("Current enrollment cannot exceed max students");
        }
        
        // Validate status
        String status = teaching.getStatus();
        if (!"ACTIVE".equals(status) && !"INACTIVE".equals(status) && !"CANCELLED".equals(status)) {
            errors.add("Status must be one of: ACTIVE, INACTIVE, CANCELLED");
        }
        
        return errors;
    }
    
    /**
     * Gets a teaching by ID
     * @param teachingId the teaching ID
     * @return the Teaching object or null if not found
     */
    public Teaching getTeachingById(String teachingId) {
        return teachingArchive.get(teachingId);
    }
    
    /**
     * Gets all teachings in the system
     * @return list of all teachings
     */
    public List<Teaching> getAllTeachings() {
        return new ArrayList<>(teachingArchive.values());
    }
    
    /**
     * Gets teachings filtered by status
     * @param status the status to filter by
     * @return list of teachings with the specified status
     */
    public List<Teaching> getTeachingsByStatus(String status) {
        List<Teaching> filtered = new ArrayList<>();
        for (Teaching teaching : teachingArchive.values()) {
            if (teaching.getStatus().equals(status)) {
                filtered.add(teaching);
            }
        }
        return filtered;
    }
    
    /**
     * Simulates administrator interrupting the operation
     */
    public void interruptOperation() {
        isOperationInterrupted.set(true);
        System.out.println("Administrator interrupted the operation.");
        
        // Reset after a short delay
        new Thread(() -> {
            try {
                Thread.sleep(5000); // Reset after 5 seconds
                isOperationInterrupted.set(false);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
    
    /**
     * Gets the SMOS server connection status
     * @return true if connected, false otherwise
     */
    public boolean isSMOSConnected() {
        return smosConnection.isConnected();
    }
    
    /**
     * Refreshes SMOS server connection
     * @return true if refresh successful, false otherwise
     */
    public boolean refreshSMOSConnection() {
        try {
            smosConnection.reconnect();
            return smosConnection.isConnected();
        } catch (SMOSConnectionException e) {
            return false;
        }
    }
    
    /**
     * Inner class to represent the result of an edit operation
     * Follows the use case postconditions and events sequence
     */
    public static class EditResult {
        private boolean success;
        private String message;
        private List<String> validationErrors;
        private List<Teaching> updatedTeachingsList;
        private boolean interrupted;
        
        private EditResult(boolean success, String message, List<String> validationErrors, 
                          List<Teaching> updatedTeachingsList, boolean interrupted) {
            this.success = success;
            this.message = message;
            this.validationErrors = validationErrors;
            this.updatedTeachingsList = updatedTeachingsList;
            this.interrupted = interrupted;
        }
        
        public static EditResult success(List<Teaching> updatedTeachingsList, String message) {
            return new EditResult(true, message, Collections.emptyList(), updatedTeachingsList, false);
        }
        
        public static EditResult error(String errorMessage) {
            return new EditResult(false, errorMessage, Collections.emptyList(), Collections.emptyList(), false);
        }
        
        public static EditResult validationError(String errorMessage, List<String> validationErrors) {
            return new EditResult(false, errorMessage, validationErrors, Collections.emptyList(), false);
        }
        
        public static EditResult interrupted(String message) {
            return new EditResult(false, message, Collections.emptyList(), Collections.emptyList(), true);
        }
        
        public boolean isSuccess() {
            return success;
        }
        
        public String getMessage() {
            return message;
        }
        
        public List<String> getValidationErrors() {
            return Collections.unmodifiableList(validationErrors);
        }
        
        public List<Teaching> getUpdatedTeachingsList() {
            return Collections.unmodifiableList(updatedTeachingsList);
        }
        
        public boolean isInterrupted() {
            return interrupted;
        }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Edit Result: ");
            sb.append(success ? "SUCCESS" : "FAILED");
            sb.append("\nMessage: ").append(message);
            
            if (!validationErrors.isEmpty()) {
                sb.append("\nValidation Errors:");
                for (String error : validationErrors) {
                    sb.append("\n  - ").append(error);
                }
            }
            
            if (interrupted) {
                sb.append("\nOperation was interrupted by administrator.");
            }
            
            return sb.toString();
        }
    }
}