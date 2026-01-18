package com.example.presentation;

import com.example.dtos.RoleDetails;
import com.example.domain.Role;

/**
 * View for displaying the work area after successful login.
 */
public class WorkAreaView {
    private Role userRole;

    public WorkAreaView(Role userRole) {
        this.userRole = userRole;
    }

    /**
     * Displays the work area based on the user's role details.
     *
     * @param roleDetails the role details of the logged-in user
     */
    public void display(RoleDetails roleDetails) {
        System.out.println("Displaying work area for role: " + roleDetails.getRole());
        // In a real application, this would render the appropriate UI
    }
}