package com.example.ui;

import com.example.application.UserDetailsDTO;
import com.example.application.ViewUserDetailsController;

/**
 * UI Boundary for displaying detailed user information.
 * Implements the steps from the sequence diagram.
 */
public class UserDetailsView {
    private ViewUserDetailsController viewUserDetailsController;

    public UserDetailsView(ViewUserDetailsController viewUserDetailsController) {
        this.viewUserDetailsController = viewUserDetailsController;
    }

    /**
     * Displays detailed user information.
     * Corresponds to step 1 in sequence diagram.
     * @param dto the UserDetailsDTO containing user details
     */
    public void displayUserDetails(UserDetailsDTO dto) {
        System.out.println("=== User Details ===");
        // Following steps 2-6 from sequence diagram
        displayName(dto.getName());
        displaySurname(dto.getSurname());
        displayEmail(dto.getEmail());
        displayCell(dto.getCell());
        displayLogin(dto.getLogin());
        // Step 7 (password display) intentionally omitted for security reasons.
        displayPassword(dto.getPassword());
    }

    public void displayName(String name) {
        System.out.println("Name: " + name);
    }

    public void displaySurname(String surname) {
        System.out.println("Surname: " + surname);
    }

    public void displayEmail(String email) {
        System.out.println("E-mail: " + email);
    }

    public void displayCell(String cell) {
        System.out.println("Cell: " + cell);
    }

    public void displayLogin(String login) {
        System.out.println("Login: " + login);
    }

    public void displayPassword(String password) {
        System.out.println("Password: " + password);
    }
}