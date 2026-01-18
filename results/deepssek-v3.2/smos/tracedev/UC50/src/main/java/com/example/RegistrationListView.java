package com.example;

import java.util.List;

/**
 * Interface for displaying registration lists.
 */
public interface RegistrationListView {
    /**
     * Displays a list of registrations.
     * @param registrations the list to display
     */
    void displayRegistrations(List<Registration> registrations);
}