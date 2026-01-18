package com.example.model;

import com.example.dto.LoginDTO;
import java.util.List;

/**
 * Entity representing an administrator.
 */
public class Administrator {
    private String userId;
    private String name;
    private String role;

    public Administrator() {}

    public Administrator(String userId, String name, String role) {
        this.userId = userId;
        this.name = name;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean login(LoginDTO credentials) {
        // Simplified login logic
        return credentials != null &&
               credentials.getUsername() != null && !credentials.getUsername().isEmpty() &&
               credentials.getPassword() != null && !credentials.getPassword().isEmpty();
    }

    public void logout() {
        // Perform logout operations
        System.out.println("Administrator logged out.");
    }

    public void selectAcademicYear(String year) {
        // Set the selected academic year
        System.out.println("Selected academic year: " + year);
    }

    public void selectClass(String classId) {
        // Set the selected class
        System.out.println("Selected class: " + classId);
    }

    public void selectStudent(String studentId) {
        // Set the selected student
        System.out.println("Selected student: " + studentId);
    }

    public void selectMonths(List<String> months) {
        // Set the selected months
        System.out.println("Selected months: " + months);
    }

    public void requestReport() {
        // Trigger report request
        System.out.println("Report requested.");
    }
}