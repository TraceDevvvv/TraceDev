package com.example.controller;

import com.example.dto.ClassDTO;
import com.example.dto.DelayEntryRequest;
import com.example.dto.DelayEntryResponse;
import com.example.dto.StudentDTO;
import com.example.dto.StudentDelayDTO;
import com.example.service.DelayService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Main controller for delay entry operations.
 * Manages
 */
public class DelayEntryController {
    private ClassDTO selectedClass;
    private Map<String, StudentDTO> studentMap;
    private Map<String, Integer> studentDelayMap;
    private DelayService delayService;
    private DelayEntryView view;

    public DelayEntryController(DelayService delayService) {
        this.delayService = delayService;
        this.studentMap = new HashMap<>();
        this.studentDelayMap = new HashMap<>();
    }

    public void setView(DelayEntryView view) {
        this.view = view;
    }

    public void showClassScreen() {
        // Implementation to show class selection screen
        if (view != null) {
            // Typically would trigger UI navigation
            System.out.println("Showing class selection screen...");
        }
    }

    public List<StudentDTO> getStudentList() {
        if (selectedClass != null) {
            return selectedClass.getStudents();
        }
        return new ArrayList<>();
    }

    public void toggleDelayCheckbox(String studentId) {
        // Toggle selection for a student
        StudentDTO student = studentMap.get(studentId);
        if (student != null) {
            boolean current = student.isPresent();
            student.setPresent(!current);
            if (!current) {
                // If now selected, activate time selection
                activateTimeSelection(studentId);
            } else {
                // If deselected, remove delay time
                studentDelayMap.remove(studentId);
                student.setDelayTime(0);
            }
            // Notify view
            if (view != null) {
                view.displayDelayCheckbox(studentId);
            }
        }
    }

    public void activateTimeSelection(String studentId) {
        // Activate time selection for a student
        if (view != null) {
            view.showTimeSelection(studentId);
        }
    }

    public void setDelayTime(String studentId, int delayTime) {
        // Set delay time for a student
        studentDelayMap.put(studentId, delayTime);
        StudentDTO student = studentMap.get(studentId);
        if (student != null) {
            student.setDelayTime(delayTime);
        }
        // Update view
        if (view != null) {
            view.updateDelayDisplay(studentId, delayTime);
        }
    }

    public boolean confirmDelayEntry() {
        // Confirm delay entry and prepare for sending
        if (view != null) {
            view.showConfirmation();
        }
        
        // Validate that at least one student has delay
        if (studentDelayMap.isEmpty()) {
            return false;
        }
        
        // Prepare request
        List<StudentDelayDTO> studentDelays = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : studentDelayMap.entrySet()) {
            studentDelays.add(new StudentDelayDTO(entry.getKey(), entry.getValue()));
        }
        
        DelayEntryRequest request = new DelayEntryRequest(
            selectedClass.getClassId(),
            "STAFF_ID", // Should come from current user
            studentDelays
        );
        
        // Process through service
        DelayEntryResponse response = delayService.processDelayEntry(request);
        
        if (response.isSuccess()) {
            sendDataToServer();
            return true;
        } else {
            // Show error
            if (view != null) {
                view.showErrorMessage(response.getMessage());
            }
            return false;
        }
    }

    public void sendDataToServer() {
        // This would be called after successful confirmation
        // Implementation would trigger sending to external server
        System.out.println("Sending data to server...");
        // Reset after sending
        if (view != null) {
            view.resetScreen();
        }
    }

    // Method to set selected class (called from ClassController)
    public void setSelectedClass(ClassDTO classDTO) {
        this.selectedClass = classDTO;
        // Initialize student map
        studentMap.clear();
        for (StudentDTO student : classDTO.getStudents()) {
            studentMap.put(student.getStudentId(), student);
        }
    }

    // Method called by view to render student list
    public void renderStudentList() {
        List<StudentDTO> students = getStudentList();
        if (view != null) {
            view.renderStudentList(students);
        }
    }
    
    // Inner interface to replace missing DelayEntryView class
    public interface DelayEntryView {
        void displayDelayCheckbox(String studentId);
        void showTimeSelection(String studentId);
        void updateDelayDisplay(String studentId, int delayTime);
        void showConfirmation();
        void showErrorMessage(String message);
        void resetScreen();
        void renderStudentList(List<StudentDTO> students);
    }
}